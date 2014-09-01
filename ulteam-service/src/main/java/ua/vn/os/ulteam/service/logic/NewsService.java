package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.service.dto.NewsDto;
import ua.vn.os.ulteam.service.exceptions.NotFoundException;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface NewsService {
    public NewsDto getNewsById(long id) throws NotFoundException;
    public List<NewsDto> getAllNews();
    public List<NewsDto> getAllNews(int startPage, int numberOfNews);
    public long createNews(News news);
    public long getNewsCount();

}
