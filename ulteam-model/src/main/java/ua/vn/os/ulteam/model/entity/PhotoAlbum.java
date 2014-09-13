package ua.vn.os.ulteam.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @Author os.
 */
@Entity
public class PhotoAlbum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    //TODO: thing about serialization java 8 LocalDataTime
    //@Column(name="creation_date", nullable = false)
    private LocalDateTime creationDateTime;

    @Column(name="fs_location_path", nullable = false)
    private String fileSystemLocationPath;

    @Column(nullable = false)
    private String author;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "photoAlbum")
    private Set<Photo> photos;

    public PhotoAlbum() {
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

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoAlbum)) return false;

        PhotoAlbum that = (PhotoAlbum) o;

        if (!author.equals(that.author)) return false;
        //TODO: thing about serialization java 8 LocalDataTime
        //if (!creationDateTime.equals(that.creationDateTime)) return false;
        if (!description.equals(that.description)) return false;
        if (!fileSystemLocationPath.equals(that.fileSystemLocationPath)) return false;
        if (!id.equals(that.id)) return false;
        if (!photos.equals(that.photos)) return false;
        if (!title.equals(that.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        //TODO: thing about serialization java 8 LocalDataTime
        //result = 31 * result + creationDateTime.hashCode();
        result = 31 * result + fileSystemLocationPath.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + photos.hashCode();
        return result;
    }
}
