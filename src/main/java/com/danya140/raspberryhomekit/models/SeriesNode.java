package com.danya140.raspberryhomekit.models;

public class SeriesNode {
    private String name;

    /**
     * Ссылка на сериал. TODO подумать над парсом с сайта, а не ручной записи в конфиг
     */
    private String link;

    /**
     * Текущий сезон. Все сезоны после считаются новыми
     */
    private int currentSeason;

    /**
     * Текущий эпизод. Все эпизоды после считаются новыми
     */
    private int currentEpisode;

    public SeriesNode() {
    }

    public SeriesNode(String name, String link, int currentSeason, int currentEpisode) {
        this.name = name;
        this.link = link;
        this.currentSeason = currentSeason;
        this.currentEpisode = currentEpisode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(int currentSeason) {
        this.currentSeason = currentSeason;
    }

    public int getCurrentEpisode() {
        return currentEpisode;
    }

    public void setCurrentEpisode(int currentEpisode) {
        this.currentEpisode = currentEpisode;
    }

}
