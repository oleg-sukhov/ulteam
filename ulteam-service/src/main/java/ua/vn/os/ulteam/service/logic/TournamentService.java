package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.model.entity.Season;
import ua.vn.os.ulteam.model.entity.Tournament;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface TournamentService {
    List<Tournament> getTournamentsInSeason(Season season);
}
