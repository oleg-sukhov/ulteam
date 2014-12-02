package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.service.dto.TourDto;
import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface TourService {
    List<TourDto> getTournamentTours(String seasonName, String tournamentName);
    List<TourDto> getTournamentTours(long tournamentId);
}
