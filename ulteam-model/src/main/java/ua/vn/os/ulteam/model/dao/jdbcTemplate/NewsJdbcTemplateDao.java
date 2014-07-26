package ua.vn.os.ulteam.model.dao.jdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ua.vn.os.ulteam.model.config.DaoConfig;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.dao.jdbcTemplate.mapper.NewsMapper;
import ua.vn.os.ulteam.model.entity.News;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by os on 22.07.14.
 */
@Import(DaoConfig.class)
public class NewsJdbcTemplateDao implements NewsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public NewsJdbcTemplateDao() {
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

    @Override
    public void updateNews(final News news) {
        getJdbcTemplate().update(UPDATE_NEWS_SQL, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, news.getTitle());
                ps.setTimestamp(2, news.getModificationDate());
                ps.setLong(3, news.getViews());
                ps.setString(4, news.getNewsContent());
                ps.setBytes(5, news.getPicture());
                ps.setLong(6, news.getId());
            }
        });
    }

    @Override
    public void deleteNewsById(final long id) {
        getJdbcTemplate().update(DELETE_NEWS_BY_ID_SQL, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, id);
            }
        });
    }

    @Override
    public long getNewsCount() {
        return getJdbcTemplate().queryForObject(GET_NEWS_COUNT_SQL, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong("COUNT(*)");
            }
        });
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
