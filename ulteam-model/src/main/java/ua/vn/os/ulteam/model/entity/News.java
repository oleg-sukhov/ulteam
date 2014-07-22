package ua.vn.os.ulteam.model.entity;

import java.sql.Timestamp;

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
}