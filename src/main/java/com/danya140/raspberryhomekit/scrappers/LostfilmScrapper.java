package com.danya140.raspberryhomekit.scrappers;

import com.danya140.raspberryhomekit.models.SeriesNode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

public class LostfilmScrapper extends AbstractScrapper {

    @Override
    public void login(String email, String password) {
        try {
/*

            Connection.Response res1 = Jsoup.connect("http://www.lostfilm.tv").execute();

            Map cookies = res1.cookies();
*/

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
                    /*.cookies(cookies)*/
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
        List<SeriesNode> seriesConfigs = obtainConfig();

    }

    @Override
    public void fetchData() {

    }

    @Override
    public void fetchTorrent() {

    }
}
