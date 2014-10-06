package ua.vn.os.ulteam.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author os
 */
@Controller
public class GamesController {

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public ModelAndView getGamesInCurrentSeason() {
        ModelAndView modelAndView = new ModelAndView("games");
        return modelAndView;
    }
}
