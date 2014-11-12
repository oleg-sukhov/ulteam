package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.service.dto.TeamDto;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface TeamService {
    List<TeamDto> getTournamentTeams(String seasonName, String tournamentName);
}
