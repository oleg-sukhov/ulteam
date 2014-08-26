package ua.vn.os.ulteam.model.entity.factory;

import com.google.common.primitives.Longs;
import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.model.util.DateUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author os
 */
public class NewsFactory {

    private static Random rnd = new Random();

    public static News getDefaultNews() {
        News news = new News();
        news.setTitle("News1_title_test");
        news.setShortDescription("ShortDescription_");
        news.setModificationDate(DateUtils.getRandomDate());
        news.setNewsContent("This_test_content_for_first_news");
        news.setViews(4);
        news.setPicture(new byte[]{21, 7, 24, 18, 91, 84, 123, 94});
        return news;
    }

    public static List<News> getRandomListOfNews(int numberOfNews) {
        List<News> listOfNews = new ArrayList<News>();
        for (int i = 0; i < numberOfNews; i++) {
            News news = new News();
            news.setTitle("Title_" + System.nanoTime());
            news.setShortDescription("ShortDescription_" + System.currentTimeMillis());
            news.setNewsContent("News_content_test_" + System.nanoTime());
            news.setViews(rnd.nextInt(10000000));
            news.setPicture(Longs.toByteArray(System.nanoTime()));
            news.setModificationDate(DateUtils.getRandomDate());
            listOfNews.add(news);
        }
        return listOfNews;
    }
}
