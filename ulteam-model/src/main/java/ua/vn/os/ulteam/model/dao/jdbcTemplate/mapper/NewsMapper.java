package ua.vn.os.ulteam.model.dao.jdbcTemplate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.vn.os.ulteam.model.entity.News;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by os on 23.07.14.
 */
public class NewsMapper implements RowMapper<News> {
    @Override
    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        News news = new News();
        news.setId(rs.getLong("id"));
        news.setTitle(rs.getString("title"));
        news.setModificationDate(rs.getTimestamp("modification_time"));
        news.setViews(rs.getInt("views"));
        news.setNewsContent(rs.getString("news_content"));
        news.setPicture(rs.getBytes("picture"));
        return news;
    }
}
