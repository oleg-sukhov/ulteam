package ua.vn.os.ulteam.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import ua.vn.os.ulteam.model.aspect.LogAspect;

/**
 * Created by os
 */
@Configuration()
@Import({DaoConfig.class})
public class ApplicationModelConfig {

}
