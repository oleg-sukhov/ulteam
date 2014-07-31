package ua.vn.os.ulteam.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.vn.os.ulteam.service.logic.NewsService;

/**
 * @author os
 */
@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/allNews", method = RequestMethod.GET)
    public String getAllNews() {
        return "allNews";
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
