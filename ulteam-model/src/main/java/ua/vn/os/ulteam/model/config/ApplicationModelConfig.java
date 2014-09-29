package ua.vn.os.ulteam.model.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author os
 */
@Configuration()
@Import({DaoConfig.class})
public class ApplicationModelConfig {

}
