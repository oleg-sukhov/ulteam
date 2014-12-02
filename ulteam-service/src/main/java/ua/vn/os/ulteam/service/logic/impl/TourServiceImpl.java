package ua.vn.os.ulteam.service.logic.impl;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.vn.os.ulteam.model.dao.TourDao;
import ua.vn.os.ulteam.model.dao.TournamentDao;
import ua.vn.os.ulteam.model.entity.Game;
import ua.vn.os.ulteam.model.entity.Tour;
import ua.vn.os.ulteam.model.entity.Tournament;
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
    @Resource private TournamentDao tournamentDao;

    @Override
    public List<TourDto> getTournamentTours(long tournamentId) {
        Tournament tournament = tournamentDao.get(tournamentId);
        return convertToTourDtoList(tournament.getTours());
    }

    @Override
    public List<TourDto> getTournamentTours(String seasonName, String tournamentName) {
        Tournament tournament = tournamentDao.getTournament(seasonName, tournamentName);
        return convertToTourDtoList(tournament.getTours());
    }

    private List<TourDto> convertToTourDtoList(List<Tour> tours) {
        return tours.stream().map(this::convertToTourDto).collect(Collectors.toList());
    }

    private TourDto convertToTourDto(Tour tour) {
        return TourDto.builder()
                .name(tour.getName())
                .id(tour.getId())
                .build();
    }
}
