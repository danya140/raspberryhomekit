package com.danya140.raspberryhomekit;

import com.danya140.raspberryhomekit.Utils.XmlHelper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RaspberryhomekitApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(RaspberryhomekitApplication.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);

        XmlHelper helper = new XmlHelper("data.xml");
        helper.getAllSeriesFromConfig();


    }

}

