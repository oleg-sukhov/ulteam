package ua.vn.os.ulteam.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.model.util.DateUtils;
import ua.vn.os.ulteam.service.logic.NewsService;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
        News news = new News("First test news", LocalDateTime.now(ZoneId.systemDefault()), 5,"Test title" ,new byte[]{32,32,32});
        newsService.createNews(news);
        List<News> newsList = newsService.getAllNews();
        return "allNews";
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
