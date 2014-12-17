package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.service.dto.GameDto;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface GameService {
    List<GameDto> getTourGames(long tourId);
    List<GameDto> getTourGames(String seasonName, String tournamentName, String tourName);
    List<GameDto> getTournamentGames(String seasonName, String tournamentName);
}
