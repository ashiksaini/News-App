package com.example.news;

import java.net.URL;

public class News {
    /**
     * @param mNewsImageUrl - Url to the news image.
     * @param mNewsUrl      - Url to the news.
     * @param mTitle        - Title of the news.
     * @param mSource       - Source of the news.
     * @param mTime         - News Time
     */

    private final String mNewsImageUrl;
    private final String mNewsUrl;
    private final String mTitle;
    private final String mSource;
    private final String mTime;

    /**
     * Constructor to get set the data to the private varible.
     */
    public News(String mNewsImageUrl, String mNewsUrl, String mTitle, String mSource, String mTime) {
        this.mNewsImageUrl = mNewsImageUrl;
        this.mNewsUrl = mNewsUrl;
        this.mTitle = mTitle;
        this.mSource = mSource;
        this.mTime = mTime;
    }

    /**
     * Getter method to return the data.
     */
    public String getmNewsImageUrl() {
        return mNewsImageUrl;
    }

    public String getmNewsUrl() {
        return mNewsUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSource() {
        return mSource;
    }

    public String getmTime() {
        return mTime;
    }
}
