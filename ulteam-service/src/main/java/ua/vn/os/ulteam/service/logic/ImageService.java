package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.model.entity.Photo;

import java.util.Set;

/**
 * @Author os
 */
public interface ImageService {
    public String convertImageForView(byte[] image);
    public Set<String> loadPhotosFromFileSystemAndConvertToBase64(String albumPath, Set<Photo> photos);
    public String loadPhotoFromFileSystemAndConvertToBase64(String albumPath, String photoName);
}
