package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.model.entity.PhotoAlbum;
import ua.vn.os.ulteam.service.dto.PhotoAlbumDto;

import java.util.List;

/**
 * @Author os
 */
public interface PhotoAlbumService {
    public List<PhotoAlbumDto> getAllPhotoAlbumsDto();
}
