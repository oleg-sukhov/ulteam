package ua.vn.os.ulteam.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @Author os.
 */
@javax.persistence.Entity
public class PhotoAlbum extends Entity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    //TODO: thing about serialization java 8 LocalDataTime
    //@Column(name="creation_date", nullable = false)
    @Transient
    private LocalDateTime creationDateTime;

    @Column(name="fs_location_path", nullable = false)
    private String fileSystemLocationPath;

    @Column(nullable = false)
    private String author;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "photoAlbum")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<Photo> photos;

    public PhotoAlbum() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getFileSystemLocationPath() {
        return fileSystemLocationPath;
    }

    public void setFileSystemLocationPath(String fileSystemLocationPath) {
        this.fileSystemLocationPath = fileSystemLocationPath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoAlbum)) return false;
        if (!super.equals(o)) return false;

        PhotoAlbum that = (PhotoAlbum) o;

        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (creationDateTime != null ? !creationDateTime.equals(that.creationDateTime) : that.creationDateTime != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (fileSystemLocationPath != null ? !fileSystemLocationPath.equals(that.fileSystemLocationPath) : that.fileSystemLocationPath != null)
            return false;
        if (photos != null ? !photos.equals(that.photos) : that.photos != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (creationDateTime != null ? creationDateTime.hashCode() : 0);
        result = 31 * result + (fileSystemLocationPath != null ? fileSystemLocationPath.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (photos != null ? photos.hashCode() : 0);
        return result;
    }
}
