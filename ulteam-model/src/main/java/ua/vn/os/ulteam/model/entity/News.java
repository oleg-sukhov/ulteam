package ua.vn.os.ulteam.model.entity;

import java.sql.Timestamp;
import java.util.Arrays;

/** This class represent news with relevant information
 *
 * Created by os on 16.07.14.
 */
public class News extends Entity {

    private String title;
    private Timestamp modificationDate;
    private long views;
    private String newsContent;
    private byte[] picture = new byte[0];

    public News() {
    }

    public News(String title) {
        this.title = title;
    }

    public News(String title, Timestamp modificationDate, long views, String newsContent, byte[] picture) {
        this.title = title;
        this.modificationDate = modificationDate;
        this.views = views;
        this.newsContent = newsContent;
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Timestamp modificationDate) {
        this.modificationDate = modificationDate;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (views != news.views) return false;
        if (modificationDate != null ? !modificationDate.equals(news.modificationDate) : news.modificationDate != null)
            return false;
        if (newsContent != null ? !newsContent.equals(news.newsContent) : news.newsContent != null) return false;
        if (!Arrays.equals(picture, news.picture)) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        result = 31 * result + (int) (views ^ (views >>> 32));
        result = 31 * result + (newsContent != null ? newsContent.hashCode() : 0);
        result = 31 * result + (picture != null ? Arrays.hashCode(picture) : 0);
        return result;
    }
}