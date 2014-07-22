package ua.vn.os.ulteam.model.dao.jdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ua.vn.os.ulteam.model.config.DaoConfig;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.entity.News;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by os on 22.07.14.
 */
@Import(DaoConfig.class)
public class NewsJdbcTemplateDao implements NewsDao {

    private static String CREATE_NEWS_SQL = "INSERT INTO news VALUES(?, ?, ?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public NewsJdbcTemplateDao() {
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long createNews(final News news) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(CREATE_NEWS_SQL);
                ps.setLong(1, 2);
                ps.setString(2, news.getTitle());
                ps.setTimestamp(3, news.getModificationDate());
                ps.setLong(4, news.getViews());
                ps.setString(5, news.getNewsContent());
                ps.setBytes(6, news.getPicture());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().longValue();

    }
}
