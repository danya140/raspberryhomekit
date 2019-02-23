package com.danya140.raspberryhomekit.scrappers;

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
    void fetchData();

    /**
     * Получение торентов
     */
    void fetchTorrent();


}
