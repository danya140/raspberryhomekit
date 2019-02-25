package com.danya140.raspberryhomekit.scrappers;

import com.danya140.raspberryhomekit.models.SeriesNode;
import org.jsoup.nodes.Element;

public interface IScrapper {

    /**
     * Аутентификация на сайте.
     * <b>Обязательно должен сохранять куки после успешной аутентификации</b>
     *
     * @param email    фактически логин
     * @param password пароль
     */
    void login(String email, String password);

    /**
     * Проверка валидности куков и логина
     *
     * @return
     */
    boolean verifyLogin();

    /**
     * Основная логика получения списка для загрузок
     */
    void scrapp();

    /**
     * Получение данных о сериале
     */
    void fetchData(SeriesNode node);

    /**
     * Получение торентов
     */
    void fetchTorrent(Element element, SeriesNode node, int season);


}
