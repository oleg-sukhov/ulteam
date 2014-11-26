package ua.vn.os.ulteam.web.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.vn.os.ulteam.service.dto.*;
import ua.vn.os.ulteam.service.logic.GameService;
import ua.vn.os.ulteam.service.logic.SeasonService;
import ua.vn.os.ulteam.service.logic.TeamService;
import ua.vn.os.ulteam.service.logic.TournamentService;

import javax.annotation.Resource;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author os
 */
@RestController
public class GameController {

    public static final String GAME_DTO_KEY = "gameDtoList";
    public static final String SEASON_DTO_KEY = "seasonDtoList";
    public static final String TOURNAMENT_DTO_KEY = "tournamentDtoList";
    public static final String TOUR_DTO_KEY = "tourDtoList";
    public static final String TEAM_DTO_KEY = "teamDtoList";

    @Resource
    private GameService gameService;

    @Resource
    private SeasonService seasonService;

    @Resource
    private TournamentService tournamentService;

    @Resource
    private TeamService teamService;

    @RequestMapping(value = "/games", method = GET)
    public ModelAndView getAllGames() {
        ModelAndView modelAndView = new ModelAndView("games");

        // load all seasonGamesCOntrol
        List<SeasonDto> seasonDtoList = seasonService.getAllSeasonDtoList();

        // load all tournaments in current season
        String currentSeasonName = seasonDtoList.get(0).getName();
        List<TournamentDto> tournamentDtoList = tournamentService.getTournamentsInSeasonDto(currentSeasonName);

        // load all tours in current tournament
        long currentTournamentId = tournamentDtoList.get(0).getId();

        modelAndView.addObject(SEASON_DTO_KEY, seasonDtoList);
        modelAndView.addObject(TOURNAMENT_DTO_KEY, tournamentDtoList);
        modelAndView.addObject(TOUR_DTO_KEY, tournamentService.getTournamentTours(currentTournamentId));
        modelAndView.addObject(TEAM_DTO_KEY, teamService.getTournamentTeams(currentTournamentId));
        modelAndView.addObject(GAME_DTO_KEY, gameService.getAllGameDtoList());
        return modelAndView;
    }

    @RequestMapping(value = "/rest/ulteam/games/{season}", produces = APPLICATION_JSON_VALUE, method = GET)
    @ResponseBody
    public Map<String, Object> getGamesBySeason(@PathVariable("season") String season) {
        Map<String, Object> response = new HashMap<>();
        List<TournamentDto> tournamentDtoList = tournamentService.getTournamentsInSeasonDto(season);
        long currentTournamentId = tournamentDtoList.get(0).getId();
        List<TourDto> tournamentTours = tournamentService.getTournamentTours(currentTournamentId);
        List<TeamDto> tournamentTeams = teamService.getTournamentTeams(currentTournamentId);

        response.put(TOURNAMENT_DTO_KEY, tournamentDtoList);
        response.put(TOUR_DTO_KEY, tournamentTours);
        response.put(TEAM_DTO_KEY, tournamentTeams);

        return response;
    }

    @RequestMapping(value = "/rest/ulteam/games/{season}/{tournament}",
            produces = APPLICATION_JSON_VALUE,
            method = GET)
    @ResponseBody
    public Map<String, Object> getGamesByTournament(@PathVariable("season") String season,
                                                    @PathVariable("tournament") String tournament) {
        Map<String, Object> response = new HashMap<>();
        List<TourDto> tournamentTours = tournamentService.getTournamentTours(season, tournament);
        List<TeamDto> tournamentTeams = teamService.getTournamentTeams(season, tournament);

        response.put(TOUR_DTO_KEY, tournamentTours);
        response.put(TEAM_DTO_KEY, tournamentTeams);

        return response;
    }
}
