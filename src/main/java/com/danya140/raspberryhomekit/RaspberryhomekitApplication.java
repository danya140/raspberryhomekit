package com.danya140.raspberryhomekit;

import com.danya140.raspberryhomekit.scrappers.LostfilmScrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RaspberryhomekitApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaspberryhomekitApplication.class, args);

        LostfilmScrapper scrapper = new LostfilmScrapper();
        scrapper.login("***REMOVED***","***REMOVED***");
	}

}

