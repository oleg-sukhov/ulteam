package ua.vn.os.ulteam.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.vn.os.ulteam.model.config.DaoConfig;
import ua.vn.os.ulteam.service.logic.ImageService;
import ua.vn.os.ulteam.service.logic.NewsService;
import ua.vn.os.ulteam.service.logic.PhotoAlbumService;
import ua.vn.os.ulteam.service.logic.impl.ImageServiceImpl;
import ua.vn.os.ulteam.service.logic.impl.NewsTransactionalServiceImpl;
import ua.vn.os.ulteam.service.logic.impl.PhotoAlbumTransactionalServiceImpl;

/**
 * @author os
 */
@Configuration
@ComponentScan(basePackages = "ua.vn.os.ulteam.service.logic")
@Import(DaoConfig.class)
public class ServiceConfig {

}
