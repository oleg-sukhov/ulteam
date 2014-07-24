package ua.vn.os.ulteam.model.dao;

import ua.vn.os.ulteam.model.entity.News;

import java.util.List;

/**
 * Created by os on 16.07.14.
 */
public interface NewsDao {

    public long createNews(News news);
    public News getNewsById(long id);
    public List<News> getAllNews();
}
