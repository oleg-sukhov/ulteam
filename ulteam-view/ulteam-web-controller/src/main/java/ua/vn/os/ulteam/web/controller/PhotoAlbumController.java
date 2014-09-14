package ua.vn.os.ulteam.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.vn.os.ulteam.service.dto.PhotoAlbumDto;
import ua.vn.os.ulteam.service.logic.PhotoAlbumService;

import java.util.List;

/**
 * @Author os
 */
@Controller
public class PhotoAlbumController {
    public static final int DEFAULT_NUMBER_PHOTO_ALBUMS_IN_PAGE = 7;

    @Autowired
    private PhotoAlbumService photoAlbumService;

    @RequestMapping(value = "/photoAlbums", method = RequestMethod.GET, params = {"activePage"})
    public ModelAndView getAllNews(final Long activePage) {
        int startPage = activePage.intValue() * DEFAULT_NUMBER_PHOTO_ALBUMS_IN_PAGE;
        ModelAndView modelAndView = new ModelAndView("photoAlbums");
        modelAndView.addObject("photoAlbumDtoList", photoAlbumService.getAllPhotoAlbumsDto(startPage, DEFAULT_NUMBER_PHOTO_ALBUMS_IN_PAGE));
        modelAndView.addObject("photoAlbumsPagesCount", photoAlbumService.getPhotoAlbumsCount() / DEFAULT_NUMBER_PHOTO_ALBUMS_IN_PAGE);
        modelAndView.addObject("photoAlbumsCount", photoAlbumService.getPhotoAlbumsCount());
        modelAndView.addObject("activePage", activePage);
        return modelAndView;
    }
}
