package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.service.dto.NewsDto;
import ua.vn.os.ulteam.service.exceptions.NotFoundException;

import java.util.List;

/**
 * @author os
 */
public interface NewsService {
    public NewsDto getNewsById(long id) throws NotFoundException;
    public List<NewsDto> getAllNews();
    public long createNews(News news);

}
