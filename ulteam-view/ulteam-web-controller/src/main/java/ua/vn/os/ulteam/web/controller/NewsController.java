package ua.vn.os.ulteam.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.model.util.DateUtils;
import ua.vn.os.ulteam.service.dto.NewsDto;
import ua.vn.os.ulteam.service.logic.NewsService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @author os
 */
@Controller
public class NewsController {
    public static final int DEFAULT_NUMBER_OF_NEWS_IN_PAGE = 10;
    private Logger logger = LoggerFactory.getLogger(NewsController.class);


    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/allNews", method = RequestMethod.GET, params = {"activePage"})
    public ModelAndView getAllNews(final Long activePage) {
        ModelAndView modelAndView = new ModelAndView("allNews");
        int startPage = activePage.intValue() * DEFAULT_NUMBER_OF_NEWS_IN_PAGE;
        List<NewsDto> newsDtoList = newsService.getAllNews(startPage, DEFAULT_NUMBER_OF_NEWS_IN_PAGE);
        modelAndView.addObject("newsDtoList", newsDtoList);
        modelAndView.addObject("newsCount", newsService.getNewsCount() / 10);
        modelAndView.addObject("activePage", activePage);
        return modelAndView;
    }

    @RequestMapping(value = "/getNewsById", method = RequestMethod.GET, params = {"id"})
    public ModelAndView getNewsById(final Long id) {
        ModelAndView modelAndView = new ModelAndView("news");
        modelAndView.addObject("newsDto", newsService.getNewsById(id));
        return modelAndView;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
