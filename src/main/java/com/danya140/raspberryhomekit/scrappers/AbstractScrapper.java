package com.danya140.raspberryhomekit.scrappers;

import com.danya140.raspberryhomekit.Utils.XmlHelper;
import com.danya140.raspberryhomekit.models.SeriesNode;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class AbstractScrapper implements IScrapper {

    /**
     * Название файла с конфигом
     */
    private static final String CONFIG_FILE_NAME = "data.xml";

    /**
     * Название файла с куками
     */
    private static final String COOKIES_FILE_NAME = "cookies";

    /**
     * Куки
     */
    protected Map<String, String> cookies;

    /**
     * Получение конфигов по каждому сериалу
     *
     * @return список с конфигами
     */
    protected List<SeriesNode> obtainConfig() {
        return new XmlHelper(CONFIG_FILE_NAME).getAllSeriesFromConfig();
    }

    /**
     * Сохранение файла куков
     *
     * @param cookies куки,которые необходимо сохранить
     */
    protected void saveCookies(Map<String, String> cookies) {
        this.cookies = cookies;
        Gson gson = new Gson();
        String json = gson.toJson(cookies);

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(COOKIES_FILE_NAME, false));
            pw.write(json);
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Получение куков из файла
     *
     * @return куки из файла
     */
    protected Map<String, String> loadCookies() {
        try {
            Scanner scanner = new Scanner(new File(COOKIES_FILE_NAME));
            String text = scanner.nextLine();
            Gson gson = new Gson();
            Map<String, String> cookies = gson.fromJson(text, HashMap.class);
            this.cookies = cookies;
            return cookies;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
