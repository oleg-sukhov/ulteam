package ua.vn.os.ulteam.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.model.util.DateUtils;
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
    private Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/allNews", method = RequestMethod.GET)
    public String getAllNews(Model model) {
        BufferedImage img = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            img = ImageIO.read(new File("/home/os/temp/test1.jpg"));
            ImageIO.write(img, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        News news = new News("Тестовий заголовок новини!!!","<p>Серед нових заходів по стабілізації ситуації на валютному міжбанку буде перегляд вимог до <strong>відкритої</strong> валютної позиції банків та віднесення до них індексованих до долара цінних паперів.", LocalDateTime.now(ZoneId.systemDefault()), 5000,"Test title" ,new byte[]{32,32,32});
        news.setPicture(baos.toByteArray());
        newsService.createNews(news);
        model.addAttribute("newsDtoList", newsService.getAllNews());
        return "allNews";
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
