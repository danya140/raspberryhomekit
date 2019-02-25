package com.danya140.raspberryhomekit.models;

public class RetreLink {

    private String quality;

    private String link;

    public RetreLink() {
    }

    public RetreLink(String quality, String link) {
        this.quality = quality;
        this.link = link;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
