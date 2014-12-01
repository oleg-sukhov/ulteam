package ua.vn.os.ulteam.web.controller;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.vn.os.ulteam.service.dto.*;
import ua.vn.os.ulteam.service.logic.*;

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

    @Resource private TourService tourService;
    @Resource private SeasonService seasonService;
    @Resource private TournamentService tournamentService;
    @Resource private TeamService teamService;

    @RequestMapping(value = "/games", method = GET)
    public ModelAndView getAllGames() {
        ModelAndView modelAndView = new ModelAndView("games");

        List<SeasonDto> seasons = seasonService.getAllSeasons();
        List<TournamentDto> tournaments = loadTournamentsInSelectedSeason(seasons);
        List<TourDto> tours = loadToursInSelectedTournament(tournaments);
        List<TeamDto> teams = loadTeamInSelectedTournament(tournaments);
        List<GameDto> games = loadGamesInSelectedTour(tours);

        modelAndView.addObject(SEASON_DTO_KEY, seasons);
        modelAndView.addObject(TOURNAMENT_DTO_KEY, tournaments);
        modelAndView.addObject(TOUR_DTO_KEY, tours);
        modelAndView.addObject(TEAM_DTO_KEY, teams);
        modelAndView.addObject(GAME_DTO_KEY, games);

        return modelAndView;
    }

    @RequestMapping(value = "/rest/ulteam/games/{season}", produces = APPLICATION_JSON_VALUE, method = GET)
    @ResponseBody
    public Map<String, Object> getGamesInSeason(@PathVariable("season") String season) {
        Map<String, Object> response = new HashMap<>();

        List<TournamentDto> tournaments = tournamentService.getTournamentsInSeasonDto(season);
        List<TourDto> tours = loadToursInSelectedTournament(tournaments);
        List<TeamDto> teams = loadTeamInSelectedTournament(tournaments);
        List<GameDto> games = loadGamesInSelectedTour(tours);

        response.put(TOURNAMENT_DTO_KEY, tournaments);
        response.put(TOUR_DTO_KEY, tours);
        response.put(TEAM_DTO_KEY, teams);
        response.put(GAME_DTO_KEY, games);

        return response;
    }

    @RequestMapping(value = "/rest/ulteam/games/{season}/{tournament}",
            produces = APPLICATION_JSON_VALUE,
            method = GET)
    @ResponseBody
    public Map<String, Object> getGamesByTournament(@PathVariable("season") String season,
                                                    @PathVariable("tournament") String tournament) {
        Map<String, Object> response = new HashMap<>();
        List<TourDto> tours = tournamentService.getTournamentTours(season, tournament);
        List<TeamDto> teams = teamService.getTournamentTeams(season, tournament);
        List<GameDto> games = loadGamesInSelectedTour(tours);

        response.put(TOUR_DTO_KEY, tours);
        response.put(TEAM_DTO_KEY, teams);
        response.put(GAME_DTO_KEY, games);

        return response;
    }

    private List<TournamentDto> loadTournamentsInSelectedSeason(List<SeasonDto> seasons) {
        if(CollectionUtils.isEmpty(seasons)) {
            return Arrays.asList();
        }

        return tournamentService.getTournamentsInSeasonDto(seasons.get(0).getName());
    }

    private List<TourDto> loadToursInSelectedTournament(List<TournamentDto> tournaments) {
        if(CollectionUtils.isEmpty(tournaments)) {
            return Arrays.asList();
        }

        return tournamentService.getTournamentTours(tournaments.get(0).getId());
    }

    private List<TeamDto> loadTeamInSelectedTournament(List<TournamentDto> tournaments) {
        if(CollectionUtils.isEmpty(tournaments)) {
            return Arrays.asList();
        }

        return teamService.getTournamentTeams(tournaments.get(0).getId());
    }

    private List<GameDto> loadGamesInSelectedTour(List<TourDto> tours) {
        if(CollectionUtils.isEmpty(tours)) {
            return Arrays.asList();
        }

        return tourService.getTourGames(tours.get(0).getId());
    }
}
