package com.danya140.raspberryhomekit.scrappers;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

public class LostfilmScrapper extends AbstractScrapper implements IScrapper {

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

            //TODO save cookies


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

    }

    @Override
    public void fetchData() {

    }

    @Override
    public void fetchTorrent() {

    }
}
