package ua.vn.os.ulteam.service.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.service.config.ServiceConfig;
import ua.vn.os.ulteam.service.logic.NewsService;

/**
 * @author os
 */
@Import(ServiceConfig.class)
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

}
