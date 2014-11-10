package ua.vn.os.ulteam.model.dao;

import ua.vn.os.ulteam.model.entity.Season;
import ua.vn.os.ulteam.model.entity.Tournament;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface TournamentDao {
    List<Tournament> getAllTournaments();
    List<Tournament> getTournamentsInSeason(Season season);
    List<String> getToursInTournament(Tournament tournament);
}
