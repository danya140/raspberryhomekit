package com.danya140.raspberryhomekit.models;

public class Episode {

    /**
     * Название сериала
     */
    private String seriesName;

    /**
     * Ссылка на торрент файл
     */
    private String seriesLink;

    /**
     * Путь к файлу вместе с именем и расширением
     */
    private String fileUri;

    /**
     * Номер сезона
     */
    private int seriesSeason;

    /**
     * Номер серии
     */
    private int seriesEpisode;


    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesLink() {
        return seriesLink;
    }

    public void setSeriesLink(String seriesLink) {
        this.seriesLink = seriesLink;
    }

    public int getSeriesSeason() {
        return seriesSeason;
    }

    public void setSeriesSeason(int seriesSeason) {
        this.seriesSeason = seriesSeason;
    }

    public int getSeriesEpisode() {
        return seriesEpisode;
    }

    public void setSeriesEpisode(int seriesEpisode) {
        this.seriesEpisode = seriesEpisode;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }
}
