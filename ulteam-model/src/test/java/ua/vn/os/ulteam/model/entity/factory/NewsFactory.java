package ua.vn.os.ulteam.model.entity.factory;

import com.google.common.primitives.Longs;
import ua.vn.os.ulteam.model.entity.News;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by os on 24.07.14.
 */
public class NewsFactory {

    private static final String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";
    private static DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public static News getDefaultNews() throws ParseException {
        News news = new News();
        Date modificationDate1 = dateFormat.parse("2014-07-12 12:00:00");
        news.setTitle("News1_title_test");
        news.setModificationDate(new Timestamp(modificationDate1.getTime()));
        news.setNewsContent("This_test_content_for_first_news");
        news.setViews(4);
        news.setPicture(new byte[]{21, 7, 24, 18, 91, 84, 123, 94});
        return news;
    }

    public static List<News> getRandomListOfNews(int numberOfNews) {
        List<News> listOfNews = new ArrayList<News>();
        Random rnd = new Random();
        try {
            for (int i = 0; i < numberOfNews; i++) {
                News news = new News();
                news.setTitle("Title_" + System.nanoTime());
                news.setNewsContent("News_content_test_" + System.nanoTime());
                news.setViews(rnd.nextInt(10000000));
                news.setPicture(Longs.toByteArray(System.nanoTime()));
                Date modificationDate = dateFormat.parse("2014-07-12 12:00:0" + rnd.nextInt(9));;
                news.setModificationDate(new Timestamp(modificationDate.getTime()));
                listOfNews.add(news);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listOfNews;
    }

}
