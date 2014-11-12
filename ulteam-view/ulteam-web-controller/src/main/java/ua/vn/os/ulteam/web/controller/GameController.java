package ua.vn.os.ulteam.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.vn.os.ulteam.service.dto.SeasonDto;
import ua.vn.os.ulteam.service.dto.TourDto;
import ua.vn.os.ulteam.service.dto.TournamentDto;
import ua.vn.os.ulteam.service.logic.GameService;
import ua.vn.os.ulteam.service.logic.SeasonService;
import ua.vn.os.ulteam.service.logic.TournamentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author os
 */
@Controller
public class GameController {

    public static final String GAME_DTO_KEY = "gameDtoList";
    public static final String SEASON_DTO_KEY = "seasonDtoList";
    public static final String TOURNAMENT_DTO_KEY = "tournamentDtoList";
    public static final String TOUR_DTO_KEY = "tourDtoList";

    @Resource
    private GameService gameService;

    @Resource
    private SeasonService seasonService;

    @Resource
    private TournamentService tournamentService;

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public ModelAndView getAllGames() {
        ModelAndView modelAndView = new ModelAndView("games");

        modelAndView.addObject(GAME_DTO_KEY, gameService.getAllGameDtoList());
        // load all season
        List<SeasonDto> seasonDtoList = seasonService.getAllSeasonDtoList();

        // load all tournaments in current season
        String currentSeasonName = seasonDtoList.get(0).getName();
        List<TournamentDto> tournamentDtoList = tournamentService.getTournamentsInSeason(currentSeasonName);

        // load all tours in first tournament
        String currentTournamentName = tournamentDtoList.get(0).getName();
        List<TourDto> tournamentTours = tournamentService.getTournamentTours(currentSeasonName, currentTournamentName);


        modelAndView.addObject(SEASON_DTO_KEY, seasonDtoList);
        modelAndView.addObject(TOURNAMENT_DTO_KEY, tournamentDtoList);
        modelAndView.addObject(TOUR_DTO_KEY, tournamentTours);
        return modelAndView;
    }
}
