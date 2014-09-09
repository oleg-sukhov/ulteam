package ua.vn.os.ulteam.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author os
 */
@Controller
public class PhotoController {

    @RequestMapping(value = "/photoAlbums", method = RequestMethod.GET, params = {"activePage"})
    public ModelAndView getAllNews(final Long activePage) {
        ModelAndView modelAndView = new ModelAndView("photoAlbums");
        //int startPage = activePage.intValue() * DEFAULT_NUMBER_OF_NEWS_IN_PAGE;
        return modelAndView;
    }
}
