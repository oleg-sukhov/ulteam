package ua.vn.os.ulteam.model.dao;

import ua.vn.os.ulteam.model.entity.Season;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface SeasonDao extends Crud<Season> {
    List<Season> getAllSeasons();
}
