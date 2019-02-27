package com.danya140.raspberryhomekit.scrappers;

import com.danya140.raspberryhomekit.Utils.ConfigHelper;
import com.danya140.raspberryhomekit.models.Episode;
import com.danya140.raspberryhomekit.models.SeriesNode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

/**
 * Лостфильм скраппер
 * <p>
 * TODO записать константы в отдельный класс/классы
 */
public class LostfilmScrapper extends AbstractScrapper {

    /**
     * Список эпизодов для закачки
     */
    private List<Episode> episodesForDownload = new ArrayList<>();


    @Override
    public void login(String email, String password) {
        try {

            Connection.Response response = Jsoup.connect("http://www.lostfilm.tv/ajaxik.php")
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .data("act", "users")
                    .data("type", "login")
                    .data("mail", email)
                    .data("pass", password)
                    .data("rem", "1")
                    .data("need_captcha", "")
                    .data("captcha", "")
                    .method(Connection.Method.POST)
                    .execute();

            //TODO check cookies
            saveCookies(response.cookies());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean verifyLogin() {
        return false;
    }

    @Override
    public void scrapp() {
        if (isLoginNeeded()) {
            ConfigHelper configHelper = ConfigHelper.getInstance();
            login(configHelper.getLogin(), configHelper.getPassword());
        }

        loadCookies();
        for (SeriesNode node : obtainConfig()) {
            fetchData(node);
        }

    }

    @Override
    public void fetchData(SeriesNode node) {
        try {
            Document document = Jsoup.connect(node.getLink() + "/seasons").cookies(cookies).execute().parse();

            Elements seasonsList = document.getElementsByClass("serie-block");
            Collections.reverse(seasonsList);
            for (Element season : seasonsList) {
                char seasonNumber = season.getElementsByTag("h2").text().charAt(0);
                if (Character.getNumericValue(seasonNumber) >= node.getCurrentSeason()) {
                    fetchTorrent(season, node, Character.getNumericValue(seasonNumber));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void fetchTorrent(Element element, SeriesNode node, int season) {
        Elements episodeList = element.getElementsByClass("zeta");
        Collections.reverse(episodeList);

        for (Element episodeElement : episodeList) {
            String episodeOnclick = episodeElement.child(0).attributes().get("onclick").split("\'")[1];
            String episodeNumber = episodeOnclick.substring(episodeOnclick.length() - 2, episodeOnclick.length());
            if (Integer.valueOf(episodeNumber) > node.getCurrentEpisode() || node.getCurrentSeason() < season) {
                Episode episode = new Episode();
                episode.setSeriesEpisode(Integer.parseInt(episodeNumber));
                episode.setSeriesSeason(season);
                episode.setSeriesLink(parseRetre(episodeOnclick));
                episode.setSeriesName(node.getName());

                episodesForDownload.add(episode);
            }
        }
    }

    /**
     * Парс страницы ретре
     *
     * @param linkNumber id ссылки
     * @return ссылка на лучший по качеству эпизод
     */
    private String parseRetre(String linkNumber) {
        try {
            Document redirectPage = Jsoup.connect("http://www.lostfilm.tv/v_search.php?a=" + linkNumber)
                    .cookies(cookies)
                    .execute()
                    .parse();
            String retreUrl = redirectPage.getElementsByTag("a").get(0).attributes().get("href");

            Document retrePage = Jsoup.connect(retreUrl).cookies(cookies).execute().parse();

            Map<String,String> links = new HashMap<>();
            for (Element link : retrePage.getElementsByClass("inner-box--item")) {
                String quality = link.child(0).text();
                String torrentLink = link.child(1).child(0).attributes().get("href");

                links.put(quality, torrentLink);
            }
            return chooseBest(links);


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Выбор лучшего качества
     *
     * @param links мапа качество - ссылка
     * @return ссылка на лучший по качеству
     */
    private String chooseBest(Map<String,String> links) {
        if (links.keySet().contains("1080")){
            return links.get("1080");
        } else if (links.keySet().contains("MP4")){
            return links.get("MP4");
        } else {
            return links.get("SD");
        }
    }

    public List<Episode> getEpisodesForDownload() {
        return episodesForDownload;
    }
}
