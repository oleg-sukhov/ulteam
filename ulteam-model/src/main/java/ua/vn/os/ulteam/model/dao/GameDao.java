package ua.vn.os.ulteam.model.dao;

import ua.vn.os.ulteam.model.entity.Game;

import java.util.List;

/**
 * @author os
 */
public interface GameDao extends Crud<Game> {
    public List<Game> getAllGames();
}
