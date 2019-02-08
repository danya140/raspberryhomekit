package com.danya140.raspberryhomekit.scrappers;

public interface IScrapper {

    void login(String email, String password);

    boolean verifyLogin();

    void scrapp();

    void fetchData();

    void fetchTorrent();


}
