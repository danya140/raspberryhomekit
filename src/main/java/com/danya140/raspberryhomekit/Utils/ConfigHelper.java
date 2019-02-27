package com.danya140.raspberryhomekit.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Сервис по работе с конфигами
 */
public class ConfigHelper {

    public static final String CONFIG_FILE = "configuration.config";

    private static ConfigHelper instance;

    private String login;

    private String password;

    private ConfigHelper() {
        init();
    }

    public static ConfigHelper getInstance() {
        if (instance == null) {
            instance = new ConfigHelper();
        }
        return instance;
    }

    /**
     * Инициализация конфига
     */
    private void init() {
        try {
            Properties properties = new Properties();

            InputStream inputStream = new FileInputStream(new File(CONFIG_FILE));
            properties.load(inputStream);
            Map tmp = properties;
            Map<String, String> props = new HashMap<String, String>(tmp);

            login = props.get("login");
            password = props.get("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    --------------------------GETTERS SETTERS-----------------------------------
     */

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
