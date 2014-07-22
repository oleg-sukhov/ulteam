package ua.vn.os.ulteam.model.dao.jdbcTemplate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.vn.os.ulteam.model.entity.News;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by os on 23.07.14.
 */
public class NewsMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        News news = new News();

        return null;
    }
}
