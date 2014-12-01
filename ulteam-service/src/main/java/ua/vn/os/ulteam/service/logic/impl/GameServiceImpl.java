package ua.vn.os.ulteam.service.logic.impl;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ua.vn.os.ulteam.model.dao.GameDao;
import ua.vn.os.ulteam.model.entity.Game;
import ua.vn.os.ulteam.service.config.ServiceConfig;
import ua.vn.os.ulteam.service.dto.GameDto;
import ua.vn.os.ulteam.service.logic.GameService;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author oleg.sukhov
 */
@Service
@Import(ServiceConfig.class)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GameServiceImpl implements GameService {

    @Resource
    private GameDao gameDao;

    @Override
    public List<GameDto> getAllGames() {
        List<Game> allGames = gameDao.getAllGames();

        if(CollectionUtils.isEmpty(allGames)) {
            throw new EntityNotFoundException();
        }

        return convertToGameDtoList(allGames);
    }

    @Override
    public List<GameDto> getGamesInSeason(String seasonName) {
        return null;
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
