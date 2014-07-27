package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.service.exceptions.NotFoundException;

import java.util.List;

/**
 * @author os
 */
public interface NewsService {
    public News getNewsById(long id) throws NotFoundException;
    public List<News> getAllNews();

}
