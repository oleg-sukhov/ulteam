package ua.vn.os.ulteam.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

/** This class represent news with relevant information
 *
 * @author oleg.sukhov
 */
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Column(name = "modification_date", nullable = false)
    private LocalDateTime modificationDate;

    @Column
    private long views;

    @Column(name = "news_content")
    private String newsContent;

    @Column
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (views != news.views) return false;
        if (!id.equals(news.id)) return false;
        if (!modificationDate.equals(news.modificationDate)) return false;
        if (!newsContent.equals(news.newsContent)) return false;
        if (!Arrays.equals(picture, news.picture)) return false;
        if (!shortDescription.equals(news.shortDescription)) return false;
        if (!title.equals(news.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + shortDescription.hashCode();
        result = 31 * result + modificationDate.hashCode();
        result = 31 * result + (int) (views ^ (views >>> 32));
        result = 31 * result + newsContent.hashCode();
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }
}