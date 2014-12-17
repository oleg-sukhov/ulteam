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
 * @author oleg.sukhov
 */
@RestController
public class GameController {

    public static final String GAME_DTO_KEY = "gameDtoList";
    public static final String SEASON_DTO_KEY = "seasonDtoList";
    public static final String TOURNAMENT_DTO_KEY = "tournamentDtoList";
    public static final String TOUR_DTO_KEY = "tourDtoList";
    public static final String ALL_TOURS_KEY = "ALL-TOURS";

    @Resource private TourService tourService;
    @Resource private SeasonService seasonService;
    @Resource private TournamentService tournamentService;
    @Resource private TeamService teamService;
    @Resource private GameService gameService;

    @RequestMapping(value = "/games", method = GET)
    public ModelAndView getAllGames() {
        ModelAndView modelAndView = new ModelAndView("games");

        List<SeasonDto> seasons = seasonService.getAllSeasons();
        List<TournamentDto> tournaments = loadTournaments(seasons.get(0));
        List<TourDto> tours = loadTours(tournaments.get(0));
        List<GameDto> games = loadGames(seasons.get(0).getName(), tournaments.get(0).getName(), ALL_TOURS_KEY);

        modelAndView.addObject(SEASON_DTO_KEY, seasons);
        modelAndView.addObject(TOURNAMENT_DTO_KEY, tournaments);
        modelAndView.addObject(TOUR_DTO_KEY, tours);
        modelAndView.addObject(GAME_DTO_KEY, games);

        return modelAndView;
    }

    @RequestMapping(value = "/rest/ulteam/games/{season}", produces = APPLICATION_JSON_VALUE, method = GET)
    @ResponseBody
    public Map<String, Object> getGamesDataBySeason(@PathVariable("season") String season) {
        Map<String, Object> response = new HashMap<>();

        List<TournamentDto> tournaments = tournamentService.getTournamentsInSeasonDto(season);
        List<TourDto> tours = loadTours(tournaments.get(0));
        List<GameDto> games = loadGames(season, tournaments.get(0).getName(), ALL_TOURS_KEY);

        response.put(TOURNAMENT_DTO_KEY, tournaments);
        response.put(TOUR_DTO_KEY, tours);
        response.put(GAME_DTO_KEY, games);

        return response;
    }

    @RequestMapping(value = "/rest/ulteam/games/{season}/{tournament}",
            produces = APPLICATION_JSON_VALUE,
            method = GET)
    @ResponseBody
    public Map<String, Object> getGamesDataByTournament(@PathVariable("season") String season,
                                                        @PathVariable("tournament") String tournament) {
        Map<String, Object> response = new HashMap<>();
        List<TourDto> tours = tourService.getTournamentTours(season, tournament);
        List<GameDto> games = loadGames(season, tournament, ALL_TOURS_KEY);

        response.put(TOUR_DTO_KEY, tours);
        response.put(GAME_DTO_KEY, games);

        return response;
    }

    @RequestMapping(value = "/rest/ulteam/games/{season}/{tournament}/{tour}",
            produces = APPLICATION_JSON_VALUE,
            method = GET)
    @ResponseBody
    public Map<String, Object> getGamesDataByTour(@PathVariable("season") String season,
                                                  @PathVariable("tournament") String tournament,
                                                  @PathVariable("tour") String tour) {
        Map<String, Object> response = new HashMap<>();

        List<GameDto> games = loadGames(season, tournament, tour);

        response.put(GAME_DTO_KEY, games);

        return response;
    }

    private List<TournamentDto> loadTournaments(SeasonDto seasonDto) {
        if(seasonDto == null) {
            return Arrays.asList();
        }

        return tournamentService.getTournamentsInSeasonDto(seasonDto.getName());
    }

    private List<TourDto> loadTours(TournamentDto tournamentDto) {
        if(tournamentDto == null) {
            return Arrays.asList();
        }

        return tourService.getTournamentTours(tournamentDto.getId());
    }

    private List<GameDto> loadGames(TourDto tourDto) {
        if(tourDto == null) {
            return Arrays.asList();
        }

        return gameService.getTourGames(tourDto.getId());
    }

    private List<GameDto> loadGames(String season, String tournament, String tour) {
        if(ALL_TOURS_KEY.equals(tour)) {
            return gameService.getTournamentGames(season, tournament);
        }

        return gameService.getTourGames(season, tournament, tour);
    }

}
