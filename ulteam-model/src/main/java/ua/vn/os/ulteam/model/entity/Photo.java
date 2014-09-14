package ua.vn.os.ulteam.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author os
 */
@javax.persistence.Entity
public class Photo extends Entity {

    @Column(name = "photo_name", nullable = false)
    private String name;

    @Column(name = "photo_description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    private PhotoAlbum photoAlbum;

    public Photo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhotoAlbum getPhotoAlbum() {
        return photoAlbum;
    }

    public void setPhotoAlbum(PhotoAlbum photoAlbum) {
        this.photoAlbum = photoAlbum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;

        Photo photo = (Photo) o;

        if (description != null ? !description.equals(photo.description) : photo.description != null) return false;
        if (name != null ? !name.equals(photo.name) : photo.name != null) return false;
        if (photoAlbum != null ? !photoAlbum.equals(photo.photoAlbum) : photo.photoAlbum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photoAlbum != null ? photoAlbum.hashCode() : 0);
        return result;
    }
}
