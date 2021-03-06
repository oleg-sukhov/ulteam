package ua.vn.os.ulteam.service.logic.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.vn.os.ulteam.model.entity.Team;
import ua.vn.os.ulteam.model.entity.Tournament;
import ua.vn.os.ulteam.service.config.ServiceConfig;
import ua.vn.os.ulteam.service.dto.TeamDto;
import ua.vn.os.ulteam.service.logic.TeamService;
import ua.vn.os.ulteam.service.logic.TournamentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author oleg.sukhov
 */
@Service
@Import(ServiceConfig.class)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TeamServiceImpl implements TeamService {
    private static Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

    @Resource
    private TournamentService tournamentService;

    @Override
    public List<TeamDto> getTournamentTeams(long tournamentId) {
        if(tournamentId == 0) {
            String errorMessage = "Incorrect method parameter - tournamentId cannot be 0";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        Tournament tournament = tournamentService.getTournament(tournamentId);
        return convertToTeamDtoList(tournament.getTeams());
    }

    @Override
    public List<TeamDto> getTournamentTeams(String seasonName, String tournamentName) {
        if(seasonName == null || tournamentName == null) {
            String errorMessage = "Incorrect method parameter - seasonName or tournamentName cannot be null";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        Tournament tournament = tournamentService.getTournament(seasonName, tournamentName);
        return convertToTeamDtoList(tournament.getTeams());
    }

    private List<TeamDto> convertToTeamDtoList(List<Team> teams) {
        return teams.stream().map(this::convertToTeamDto).collect(Collectors.toList());
    }

    private TeamDto convertToTeamDto(Team team) {
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .town(team.getTown())
                .logoUrl(team.getLogoPath())
                .build();
    }
}
