package ua.vn.os.ulteam.model.dao;

import ua.vn.os.ulteam.model.entity.Tour;

/**
 * @author oleg.sukhov
 */
public interface TourDao extends Crud<Tour> {
    Tour getTour(String seasonName, String tournamentName, String tourName);
}
