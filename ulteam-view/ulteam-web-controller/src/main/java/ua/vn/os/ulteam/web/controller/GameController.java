package ua.vn.os.ulteam.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.vn.os.ulteam.service.dto.GameDto;
import ua.vn.os.ulteam.service.logic.GameService;

import java.util.List;

/**
 * @author os
 */
@Controller
public class GameController {

    public static final String GAME_DTO_KEY = "gameDtoList";
    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public ModelAndView getAllGames() {
        ModelAndView modelAndView = new ModelAndView("games");
        List<GameDto> gameDtoList = gameService.getAllGameDtoList();
        modelAndView.addObject(GAME_DTO_KEY, gameDtoList);
        return modelAndView;
    }
}
