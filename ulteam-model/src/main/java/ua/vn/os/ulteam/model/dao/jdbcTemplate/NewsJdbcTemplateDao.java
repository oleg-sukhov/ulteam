package ua.vn.os.ulteam.model.dao.jdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ua.vn.os.ulteam.model.config.DaoConfig;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.dao.jdbcTemplate.mapper.NewsMapper;
import ua.vn.os.ulteam.model.entity.News;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by os on 22.07.14.
 */
@Import(DaoConfig.class)
public class NewsJdbcTemplateDao implements NewsDao {

    private static String CREATE_NEWS_SQL = "INSERT INTO news (title, modification_time, views, news_content, picture) VALUES (?, ?, ?, ?, ?)";
    private static String GET_NEWS_BY_ID_SQL = "SELECT * FROM news WHERE news.id = ?";
    private static String GET_ALL_NEWS_SQL = "SELECT * FROM news";

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
                ps.setString(1, news.getTitle());
                ps.setTimestamp(2, news.getModificationDate());
                ps.setLong(3, news.getViews());
                ps.setString(4, news.getNewsContent());
                ps.setBytes(5, news.getPicture());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public News getNewsById(long id) {
        return getJdbcTemplate().queryForObject(GET_NEWS_BY_ID_SQL, new Object[]{id}, new NewsMapper());
    }

    @Override
    public List<News> getAllNews() {
        return getJdbcTemplate().query(GET_ALL_NEWS_SQL, new NewsMapper());
    }
}
