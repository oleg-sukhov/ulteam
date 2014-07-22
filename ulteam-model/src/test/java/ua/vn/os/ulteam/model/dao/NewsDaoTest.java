package ua.vn.os.ulteam.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.vn.os.ulteam.model.config.DaoConfig;
import ua.vn.os.ulteam.model.entity.News;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by os on 16.07.14.
 */
@ContextConfiguration(classes = {DaoConfig.class})
public class NewsDaoTest extends AbstractTestNGSpringContextTests {

    private final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    @Autowired
    private NewsDao newsDao;

    private DateFormat dateFormat;

    @BeforeClass
    public void init() {

        dateFormat = new SimpleDateFormat(DATE_FORMAT);
    }

    @Test
    public void testCreateNews() throws ParseException {
        News news = new News();
        Date modificationDate1 = dateFormat.parse("2014/07/12 23:00:00");
        news.setTitle("News1_title_test");
        news.setModificationDate(new Timestamp(modificationDate1.getTime()));
        news.setNewsContent("This_test_content_for_first_news");
        news.setViews(4);
        news.setPicture(new byte[] {21, 7, 24, 18, 91, 84, 123, 94});
        long id  = newsDao.createNews(news);
        Assert.assertTrue(id != 0, "Create news must return id - not 0");
    }
}
