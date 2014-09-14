package ua.vn.os.ulteam.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.vn.os.ulteam.model.config.DaoConfig;
import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.model.entity.factory.NewsFactory;
import ua.vn.os.ulteam.model.util.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static ua.vn.os.ulteam.model.entity.factory.NewsFactory.*;

/**
 * @author os
 */

@ContextConfiguration(classes = {DaoConfig.class})
public class NewsDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @BeforeClass
    public void init() {

    }

    @BeforeMethod
    public void cleanTable() {
        hibernateTemplate.bulkUpdate("delete from News");
    }

    @Test
    public void testCreateNews() throws ParseException {
        News news = getDefaultNews();
        long id  = newsDao.saveOrUpdate(news);
        Assert.assertTrue(id != 0, "Create news must return id - not 0");
    }

    @Test
    public void testGetNewsById() throws ParseException {
        News news = getDefaultNews();
        long id  = newsDao.saveOrUpdate(news);
        news.setId(id);
        News loadedNews = newsDao.get(id);
        assertNews(news, loadedNews);
    }

    @Test
    public void testGetAllNews() {
        Map<Long, News> allNews = new HashMap<>();

        for(News news : getRandomListOfNews(10)) {
            long id = newsDao.saveOrUpdate(news);
            news.setId(id);
            allNews.put(id, news);
        }

        List<News> allLoadedNews = newsDao.getAllNews();

        for(News loadedNews : allLoadedNews) {
            assertNews(loadedNews, allNews.get(loadedNews.getId()));
        }
    }

    @Test
    public void testUpdateNews() {
        News news = getDefaultNews();
        long newsId = newsDao.saveOrUpdate(news);

        News loadedNews = newsDao.get(newsId);
        assertNotNull(loadedNews);
        loadedNews.setViews(777);
        loadedNews.setTitle("Updated news");
        loadedNews.setModificationDate(DateUtils.getRandomDate());
        newsDao.update(loadedNews);
        News updatedNews = newsDao.get(loadedNews.getId());

        assertNotNull(updatedNews);
        assertNews(loadedNews, updatedNews);
    }

    @Test
    public void testGetNewsCount() {
        assertEquals(0, newsDao.getCount());

        for(News news : getRandomListOfNews(5)) {
            newsDao.saveOrUpdate(news);
        }

        assertEquals(5, newsDao.getCount());

        for(News news : getRandomListOfNews(3)) {
            newsDao.saveOrUpdate(news);
        }

        assertEquals(8, newsDao.getCount());
    }

    @Test
    public void testDeleteNewsById() {
        long id = newsDao.saveOrUpdate(getDefaultNews());
        assertEquals(1, newsDao.getCount());
        newsDao.delete(id);
        newsDao.delete(id);
        assertEquals(0, newsDao.getCount());
    }

    private void assertNews(News first, News second) {
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getTitle(), second.getTitle());
        assertEquals(first.getViews(), second.getViews());
        assertEquals(first.getModificationDate(), second.getModificationDate());
        assertEquals(first.getNewsContent(), second.getNewsContent());
        assertEquals(first.getPicture(), second.getPicture());
    }
}
