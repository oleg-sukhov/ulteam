package ua.vn.os.ulteam.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.vn.os.ulteam.model.config.DaoConfig;
import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.model.entity.factory.NewsFactory;

import java.text.ParseException;
import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by os on 16.07.14.
 */
@ContextConfiguration(classes = {DaoConfig.class})
public class NewsDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeClass
    public void init() {

    }

    @Test
    public void testCreateNews() throws ParseException {
        News news = NewsFactory.getDefaultNews();
        long id  = newsDao.createNews(news);
        Assert.assertTrue(id != 0, "Create news must return id - not 0");
    }

    @Test
    public void testGetNewsById() throws ParseException {
        News news = NewsFactory.getDefaultNews();
        long id  = newsDao.createNews(news);
        news.setId(id);
        News loadedNews = newsDao.getNewsById(id);
        assertNews(news, loadedNews);
    }

    @Test
    public void testGetAlNews() {
        Map<Long, News> allNews = new HashMap<>();

        for(News news : NewsFactory.getRandomListOfNews(10)) {
            long id = newsDao.createNews(news);
            news.setId(id);
            allNews.put(id, news);
        }

        List<News> allLoadedNews = newsDao.getAllNews();

        for(News loadedNews : allLoadedNews) {
            assertNews(loadedNews, allNews.get(loadedNews.getId()));
        }
        System.out.println();
    }

    private void assertNews(News first, News second) {
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getTitle(), second.getTitle());
        assertEquals(first.getViews(), second.getViews());
        assertEquals(first.getModificationDate(), second.getModificationDate());
        assertEquals(first.getNewsContent(), second.getNewsContent());
        assertEquals(first.getPicture(), second.getPicture());
    }


    @AfterMethod
    public void clearAll() {
        jdbcTemplate.execute("DELETE FROM news");
    }
}
