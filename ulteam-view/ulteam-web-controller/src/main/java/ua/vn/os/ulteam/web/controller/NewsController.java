package ua.vn.os.ulteam.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.service.logic.NewsService;

import java.util.List;

/**
 * @author os
 */
@Controller
public class NewsController {
    private Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/allNews", method = RequestMethod.GET)
    public String getAllNews() {
        List<News> news = newsService.getAllNews();
        return "allNews";
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
