package ua.vn.os.ulteam.service.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.service.config.ServiceConfig;
import ua.vn.os.ulteam.service.exceptions.NotFoundException;
import ua.vn.os.ulteam.service.logic.NewsService;

import java.util.List;

/**
 * @author os
 */
@Import(ServiceConfig.class)
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public News getNewsById(long id) throws NotFoundException {
        News news = this.getNewsDao().getNewsById(id);

        if(news == null) {
            throw new NotFoundException("News with id = " + id + "not found in database!!!");
        }

        return news;
    }

    @Override
    public List<News> getAllNews() {
        return null;
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }
}
