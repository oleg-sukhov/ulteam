package ua.vn.os.ulteam.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

/** This class represent news with relevant information
 *
 * @author oleg.sukhov
 */
@javax.persistence.Entity
public class News extends Entity {

    @Column(nullable = false)
    private String title;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Column(name = "modification_date", nullable = false)
    private LocalDateTime modificationDate;

    @Column(nullable = false)
    private long views;

    @Column(name = "news_content")
    private String newsContent;

    @Column(nullable = false)
    private byte[] picture = new byte[0];

    public News() {
    }

    public News(String title) {
        this.title = title;
    }

    public News(String title, String shortDescription, LocalDateTime modificationDate, long views, String newsContent, byte[] picture) {
        this.title = title;
        this.shortDescription = shortDescription;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
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
        if (!(o instanceof News)) return false;
        if (!super.equals(o)) return false;

        News news = (News) o;

        if (views != news.views) return false;
        if (modificationDate != null ? !modificationDate.equals(news.modificationDate) : news.modificationDate != null)
            return false;
        if (newsContent != null ? !newsContent.equals(news.newsContent) : news.newsContent != null) return false;
        if (!Arrays.equals(picture, news.picture)) return false;
        if (shortDescription != null ? !shortDescription.equals(news.shortDescription) : news.shortDescription != null)
            return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        result = 31 * result + (int) (views ^ (views >>> 32));
        result = 31 * result + (newsContent != null ? newsContent.hashCode() : 0);
        result = 31 * result + (picture != null ? Arrays.hashCode(picture) : 0);
        return result;
    }
}