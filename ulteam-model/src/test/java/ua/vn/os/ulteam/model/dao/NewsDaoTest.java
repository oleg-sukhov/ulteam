package ua.vn.os.ulteam.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import ua.vn.os.ulteam.model.config.DaoConfig;

/**
 * Created by os on 16.07.14.
 */
@ContextConfiguration(classes = {DaoConfig.class})
public class NewsDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeClass
    public void setupDatabase() {


    }
}
