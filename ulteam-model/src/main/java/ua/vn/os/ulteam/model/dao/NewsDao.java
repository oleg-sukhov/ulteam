package ua.vn.os.ulteam.model.dao;

import ua.vn.os.ulteam.model.entity.News;

import java.util.List;

/**
 * @author os
 */
public interface NewsDao {
    public static final int PAGE_START_INDEX = 0;

    public static String CREATE_NEWS_SQL = "INSERT INTO news (title, modification_time, views, news_content, picture) VALUES (?, ?, ?, ?, ?)";
    public static String UPDATE_NEWS_SQL = "UPDATE news SET title = ?, modification_time = ?, views = ?, news_content = ?, picture = ? WHERE id = ?";
    public static String GET_NEWS_BY_ID_SQL = "SELECT * FROM news WHERE news.id = ?";
    public static String GET_ALL_NEWS_SQL = "SELECT * FROM news";
    public static String GET_NEWS_COUNT_SQL = "SELECT COUNT(*) FROM news";
    public static String DELETE_NEWS_BY_ID_SQL = "DELETE FROM news WHERE id = ?";

    public long createNews(News news);
    public News getNewsById(long id);
    public List<News> getAllNews();
    public List<News> getAllNews(int startPage, int numberOfNews);
    public void updateNews(News news);
    public void deleteNewsById(long id);
    public long getNewsCount();
}
