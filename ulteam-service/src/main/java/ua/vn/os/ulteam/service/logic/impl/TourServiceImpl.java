package ua.vn.os.ulteam.service.logic.impl;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.vn.os.ulteam.model.dao.TourDao;
import ua.vn.os.ulteam.model.entity.Game;
import ua.vn.os.ulteam.model.entity.Tour;
import ua.vn.os.ulteam.service.config.ServiceConfig;
import ua.vn.os.ulteam.service.dto.GameDto;
import ua.vn.os.ulteam.service.dto.TourDto;
import ua.vn.os.ulteam.service.logic.TourService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author oleg.sukhov
 */
@Service
@Import(ServiceConfig.class)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TourServiceImpl implements TourService {

    @Resource private TourDao tourDao;

    @Override
    public List<GameDto> getTourGames(long tourId) {
        Tour tour = tourDao.get(tourId);
        return convertToGameDtoList(tour.getGames());
    }

    private List<GameDto> convertToGameDtoList(List<Game> games) {
        return  games.stream().map(this::convertToGameDto).collect(Collectors.toList());
    }

    private GameDto convertToGameDto(Game game) {
        return GameDto.builder()
                .id(game.getId())
                .ownerTeam(game.getOwnerTeam().getName())
                .guestTeam(game.getGuestTeam().getName())
                .ownerTeamTown(game.getOwnerTeam().getTown())
                .guestTeamTown(game.getGuestTeam().getTown())
                .ownerTeamLogoUrl(game.getOwnerTeam().getLogoPath())
                .guestTeamLogoUrl(game.getGuestTeam().getLogoPath())
                .ownerTeamGoals(game.getOwnerTeamGoals())
                .guestTeamGoals(game.getGuestTeamGoals())
                .date(game.getGameDate().toString())
                .tournament(game.getTour().getTournament().getName())
                .tour(game.getTour().getName())
                .build();
    }
}
