package ua.vn.os.ulteam.service.dto;


public class PhotoAlbumDto {

    private long id;
    private String title;
    private String description;
    private String creationDateTime;
    private String author;
    private int numberOfPhotos;
    private PhotoDto avatar;

    public PhotoAlbumDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public PhotoDto getAvatar() {
        return avatar;
    }

    public void setAvatar(PhotoDto avatar) {
        this.avatar = avatar;
    }

    public int getNumberOfPhotos() {
        return numberOfPhotos;
    }

    public void setNumberOfPhotos(int numberOfPhotos) {
        this.numberOfPhotos = numberOfPhotos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoAlbumDto)) return false;

        PhotoAlbumDto that = (PhotoAlbumDto) o;

        if (!author.equals(that.author)) return false;
        //TODO: thing about serialization java 8 LocalDataTime
        //if (!creationDateTime.equals(that.creationDateTime)) return false;
        if (!description.equals(that.description)) return false;
        if (!(id == that.id)) return false;
        if (!title.equals(that.title)) return false;
        if (!avatar.equals(that.avatar)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        //TODO: thing about serialization java 8 LocalDataTime
        //result = 31 * result + creationDateTime.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + avatar.hashCode();
        result = 31 * result + numberOfPhotos;
        return result;
    }
}
