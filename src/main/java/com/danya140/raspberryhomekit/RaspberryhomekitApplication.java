package com.danya140.raspberryhomekit;

import com.danya140.raspberryhomekit.Utils.DownloadManager;
import com.danya140.raspberryhomekit.Utils.TorrentHelper;
import com.danya140.raspberryhomekit.Utils.XmlHelper;
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

        LostfilmScrapper scrapper = new LostfilmScrapper();
        scrapper.scrapp();

        DownloadManager dm = new DownloadManager();
        dm.download(scrapper.getEpisodesForDownload().get(0));

        TorrentHelper helper = new TorrentHelper();

        helper.startDownloadAsync(scrapper.getEpisodesForDownload().get(0));

        dm.cleanUp(scrapper.getEpisodesForDownload().get(0));


    }

}

