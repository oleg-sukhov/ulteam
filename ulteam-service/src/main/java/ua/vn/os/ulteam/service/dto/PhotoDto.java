package ua.vn.os.ulteam.service.dto;

import java.util.Arrays;

/**
 * @Author os
 */
public class PhotoDto {

    private String id;
    private String name;
    private String description;
    private String base64Data;

    public PhotoDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getData() {
        return base64Data;
    }

    public void setData(String base64Data) {
        this.base64Data = base64Data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoDto)) return false;

        PhotoDto photoDto = (PhotoDto) o;

        if (!base64Data.equals(photoDto.base64Data)) return false;
        if (description != null ? !description.equals(photoDto.description) : photoDto.description != null)
            return false;
        if (!id.equals(photoDto.id)) return false;
        if (!name.equals(photoDto.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + base64Data.hashCode();
        return result;
    }
}
