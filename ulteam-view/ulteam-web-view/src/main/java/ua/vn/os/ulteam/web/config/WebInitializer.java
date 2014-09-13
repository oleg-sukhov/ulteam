package ua.vn.os.ulteam.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ua.vn.os.ulteam.model.config.RepositoryConfig;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * @author os
 */
@Import(ControllerConfig.class)
public class WebInitializer implements WebApplicationInitializer {

    @Autowired
    RepositoryConfig repositoryConfig;


    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ControllerConfig.class);
        ctx.setServletContext(servletContext);
        servletContext.addListener(new ContextLoaderListener(ctx));

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

        FilterRegistration.Dynamic hibernateFilter = servletContext.addFilter("hibernateFilter",new OpenSessionInViewFilter());
        hibernateFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        hibernateFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.FORWARD), true, "/*");

    }
}
