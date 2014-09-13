package ua.vn.os.ulteam.service.logic.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.service.config.ServiceConfig;
import ua.vn.os.ulteam.service.dto.NewsDto;
import ua.vn.os.ulteam.service.exceptions.NotFoundException;
import ua.vn.os.ulteam.service.logic.ImageService;
import ua.vn.os.ulteam.service.logic.NewsService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author os
 */
@Service
@Import(ServiceConfig.class)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NewsTransactionalServiceImpl implements NewsService {

    private static Logger logger = LoggerFactory.getLogger(NewsTransactionalServiceImpl.class);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private ImageService imageService;

    @Override
    public NewsDto getNewsById(long id) throws NotFoundException {
        News news = newsDao.getNewsById(id);

        if(news == null) {
            throw new NotFoundException("News with id = " + id + "not found in database!!!");
        }

        return convertToNewsDto(news);
    }

    @Override
    public List<NewsDto> getAllNews() {
        List<News> news = newsDao.getAllNews();
        return getNewsDtoList(news);
    }

    @Override
    public List<NewsDto> getAllNews(int startPage, int numberOfNews) {
        List<News> news = newsDao.getAllNews(startPage, numberOfNews);
        return getNewsDtoList(news);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public long createNews(News news) {
        //TODO: Add validation
        long newsId = newsDao.createNews(news);
        logger.info("News with id = " + newsId + " was successful created.");
        return newsId;
    }

    @Override
    public long getNewsCount() {
        return newsDao.getNewsCount();
    }

    private List<NewsDto> getNewsDtoList(List<News> newsList) {
        List<NewsDto> newsDtoList = new ArrayList<>();
        for(News news : newsList) {
            newsDtoList.add(convertToNewsDto(news));
        }

        return newsDtoList;
    }

    private NewsDto convertToNewsDto(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(String.valueOf(news.getId()));
        newsDto.setTitle(news.getTitle());
        newsDto.setShortDescription(news.getShortDescription());
        newsDto.setModificationDate(news.getModificationDate().format(dateTimeFormatter));
        newsDto.setViews(String.valueOf(news.getViews()));
        newsDto.setNewsContent(news.getNewsContent());
        newsDto.setPicture(imageService.convertImageForView(news.getPicture()));
        return newsDto;
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }
}
