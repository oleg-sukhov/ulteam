package ua.vn.os.ulteam.service.logic.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ua.vn.os.ulteam.model.dao.hibernate.PhotoAlbumDao;
import ua.vn.os.ulteam.model.entity.Photo;
import ua.vn.os.ulteam.model.entity.PhotoAlbum;
import ua.vn.os.ulteam.service.config.ServiceConfig;
import ua.vn.os.ulteam.service.dto.PhotoAlbumDto;
import ua.vn.os.ulteam.service.dto.PhotoDto;
import ua.vn.os.ulteam.service.logic.ImageService;
import ua.vn.os.ulteam.service.logic.PhotoAlbumService;

import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author os
 */
@Service
@Import(ServiceConfig.class)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PhotoAlbumTransactionalServiceImpl implements PhotoAlbumService {

    private static Logger logger = LoggerFactory.getLogger(PhotoAlbumTransactionalServiceImpl.class);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Autowired
    private PhotoAlbumDao photoAlbumDao;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ImageService imageService;

    @Override
    public List<PhotoAlbumDto> getAllPhotoAlbumsDto() {
        List<PhotoAlbum> photoAlbums = photoAlbumDao.getAllPhotoAlbums();

        if(CollectionUtils.isEmpty(photoAlbums)) {
            logger.error(messageSource.getMessage("ua.vn.os.ulteam.service.photo.album.not.found", new Object[]{}, Locale.getDefault()));
        }

        return convertToPhotoAlbumsDtos(photoAlbums);
    }

    private List<PhotoAlbumDto> convertToPhotoAlbumsDtos(List<PhotoAlbum> photoAlbums) {
        List<PhotoAlbumDto> photoAlbumDtoList = new ArrayList<>();

        for(PhotoAlbum photoAlbum : photoAlbums) {
            PhotoAlbumDto photoAlbumDto = new PhotoAlbumDto();
            photoAlbumDto.setId(photoAlbum.getId().toString());
            photoAlbumDto.setTitle(photoAlbum.getTitle());
            photoAlbumDto.setDescription(photoAlbum.getDescription());
            photoAlbumDto.setAuthor(photoAlbum.getAuthor());
            //TODO: thing about serialization java 8 LocalDataTime
            //photoAlbumDto.setCreationDateTime(photoAlbum.getCreationDateTime().format(dateTimeFormatter));
            photoAlbumDto.setPhotos(convertToPhotosDtos(photoAlbum.getFileSystemLocationPath(), photoAlbum.getPhotos()));

            photoAlbumDtoList.add(photoAlbumDto);
        }

        return photoAlbumDtoList;

    }

    private Set<PhotoDto> convertToPhotosDtos(String albumPath, Set<Photo> photos) {
        Set<PhotoDto> photoDtoSet = new HashSet<>();

        for (Photo photo: photos) {
            PhotoDto photoDto = new PhotoDto();
            photoDto.setId(photo.getId().toString());
            photoDto.setName(photo.getName());
            photoDto.setDescription(photo.getDescription());
            photoDto.setBase64Data(imageService.loadPhotoFromFileSystemAndConvertToBase64(albumPath, photo.getName()));

            photoDtoSet.add(photoDto);
        }

        return photoDtoSet;
    }


}
