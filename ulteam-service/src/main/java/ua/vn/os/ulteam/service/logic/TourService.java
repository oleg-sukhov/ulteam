package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.service.dto.GameDto;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface TourService {
    List<GameDto> getTourGames(long tourId);
}
