package com.danya140.raspberryhomekit;

import com.danya140.raspberryhomekit.GUI.TrayMenu;
import com.danya140.raspberryhomekit.Utils.ConfigHelper;
import com.danya140.raspberryhomekit.Utils.DownloadManager;
import com.danya140.raspberryhomekit.Utils.TorrentHelper;
import com.danya140.raspberryhomekit.Utils.XmlHelper;
import com.danya140.raspberryhomekit.models.Episode;
import com.danya140.raspberryhomekit.scrappers.LostfilmScrapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RaspberryhomekitApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(RaspberryhomekitApplication.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);

        ConfigHelper configHelper = ConfigHelper.getInstance();


        TrayMenu trayMenu = TrayMenu.getInstance();
        trayMenu.showNotification("Приложение запущено", "");



        //TODO сделать логин

        LostfilmScrapper scrapper = new LostfilmScrapper();
        DownloadManager dm = new DownloadManager();
        TorrentHelper helper = TorrentHelper.getInstance();

        scrapper.scrapp();
        for (Episode episode : scrapper.getEpisodesForDownload()){
            dm.download(episode);
        }

        for (Episode episode : scrapper.getEpisodesForDownload()){
            helper.startDownloadAsync(episode);
        }


    }

}

