package ua.vn.os.ulteam.model.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.sql.DataSource;

import static org.testng.Assert.assertNotNull;

/**
 * Created by os on 11.07.14.
 */
public class DataSourceConfigTest {

    private ApplicationContext applicationContext;

    @BeforeTest
    public void before() {
        applicationContext = new AnnotationConfigApplicationContext(DataSourceConfig.class);
    }

    @Test
    public void testDataSourceBean() {
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        assertNotNull(dataSource);
    }
}
