package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.service.dto.PhotoAlbumDto;
import ua.vn.os.ulteam.service.dto.PhotoDto;

import java.util.List;

/**
 * @Author os
 */
public interface PhotoAlbumService {
    public List<PhotoAlbumDto> getAllPhotoAlbumsDtos(int startPage, int numberOfPhotoAlbumsInPage);
    public List<PhotoDto> getAllPhotosInAlbum(long photoAlbumId);
    public long getPhotoAlbumsCount();
}
