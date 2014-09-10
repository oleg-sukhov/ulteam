package ua.vn.os.ulteam.service.dto;

import java.util.Set;

public class PhotoAlbumDto {

    private String id;
    private String title;
    private String description;
    private String creationDateTime;
    private String author;
    private Set<PhotoDto> photos;

    public PhotoAlbumDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<PhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<PhotoDto> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoAlbumDto)) return false;

        PhotoAlbumDto that = (PhotoAlbumDto) o;

        if (!author.equals(that.author)) return false;
        if (!creationDateTime.equals(that.creationDateTime)) return false;
        if (!description.equals(that.description)) return false;
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
        result = 31 * result + creationDateTime.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + photos.hashCode();
        return result;
    }
}
