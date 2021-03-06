package ua.vn.os.ulteam.model.dao;

import ua.vn.os.ulteam.model.entity.Season;
import ua.vn.os.ulteam.model.entity.Tournament;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface TournamentDao extends Crud<Tournament> {
    List<Tournament> getTournamentsInSeason(Season season);
    Tournament getTournament(String seasonName, String tournamentName);
}
