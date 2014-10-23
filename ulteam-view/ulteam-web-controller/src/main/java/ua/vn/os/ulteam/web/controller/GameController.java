package ua.vn.os.ulteam.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.vn.os.ulteam.service.logic.GameService;
import ua.vn.os.ulteam.service.logic.SeasonService;

import javax.annotation.Resource;

/**
 * @author os
 */
@Controller
public class GameController {

    public static final String GAME_DTO_KEY = "gameDtoList";
    public static final String SEASON_DTO_KEY = "seasonDtoList";

    @Resource
    private GameService gameService;

    @Resource
    private SeasonService seasonService;


    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public ModelAndView getAllGames() {
        ModelAndView modelAndView = new ModelAndView("games");
        modelAndView.addObject(GAME_DTO_KEY, gameService.getAllGameDtoList());
        modelAndView.addObject(SEASON_DTO_KEY, seasonService.getAllSeasonDtoList());
        return modelAndView;
    }
}
