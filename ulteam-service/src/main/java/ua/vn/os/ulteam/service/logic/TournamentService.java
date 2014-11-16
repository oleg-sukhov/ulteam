package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.model.entity.Season;
import ua.vn.os.ulteam.model.entity.Tournament;
import ua.vn.os.ulteam.service.dto.TourDto;
import ua.vn.os.ulteam.service.dto.TournamentDto;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface TournamentService {
    List<TournamentDto> getTournamentsInSeason(String seasonName);
    List<Tournament> getTournamentsInSeason(Season season);
    Tournament getTournament(String seasonName, String tournamentName);
    List<TourDto> getTournamentTours(String seasonName, String tournamentName);
}