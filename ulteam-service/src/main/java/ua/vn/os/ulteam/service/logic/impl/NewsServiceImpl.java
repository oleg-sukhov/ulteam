package ua.vn.os.ulteam.service.logic.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NewsServiceImpl implements NewsService {

    private static Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsDao newsDao;

    @Override
    public News getNewsById(long id) throws NotFoundException {
        News news = newsDao.getNewsById(id);

        if(news == null) {
            throw new NotFoundException("News with id = " + id + "not found in database!!!");
        }

        return news;
    }

    @Override
    public List<News> getAllNews() {
        List<News> news = newsDao.getAllNews();
        return news;
    }

    @Override
    public long createNews(News news) {
        //TODO: Add validation
        long newsId = newsDao.createNews(news);
        logger.info("News with id = " + newsId + " was successful created.");
        return newsId;
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }
}
