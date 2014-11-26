package ua.vn.os.ulteam.service.logic.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ua.vn.os.ulteam.model.dao.TournamentDao;
import ua.vn.os.ulteam.model.entity.Season;
import ua.vn.os.ulteam.model.entity.Tour;
import ua.vn.os.ulteam.model.entity.Tournament;
import ua.vn.os.ulteam.service.config.ServiceConfig;
import ua.vn.os.ulteam.service.dto.TourDto;
import ua.vn.os.ulteam.service.dto.TournamentDto;
import ua.vn.os.ulteam.service.logic.SeasonService;
import ua.vn.os.ulteam.service.logic.TournamentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author oleg.sukhov
 */
@Service
@Import(ServiceConfig.class)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TournamentTransactionalServiceImpl implements TournamentService {

    private static Logger logger = LoggerFactory.getLogger(TournamentTransactionalServiceImpl.class);

    @Resource
    private TournamentDao tournamentDao;

    @Resource
    private SeasonService seasonService;

    @Override
    public List<TournamentDto> getTournamentsInSeasonDto(String seasonName) {
        if(StringUtils.isEmpty(seasonName)) {
            String errorMessage = "Incorrect method parameter - seasonName cannot be null or empty";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        Season season = seasonService.getSeasonByName(seasonName);
        List<Tournament> tournamentsInSeason = getTournamentsInSeason(season);

        if(CollectionUtils.isEmpty(tournamentsInSeason)) {
            //TODO:throw exception
        }

        return convertToTournamentDtoList(tournamentsInSeason);
    }

    @Override
    public List<Tournament> getTournamentsInSeason(Season season) {
        return tournamentDao.getTournamentsInSeason(season);
    }

    @Override
    public List<Tournament> getTournamentsInSeason(String seasonName) {
        Season season = seasonService.getSeasonByName(seasonName);
        return tournamentDao.getTournamentsInSeason(season);
    }

    @Override
    public Tournament getTournament(long tournamentId) {
        if(tournamentId == 0) {
            String errorMessage = "Incorrect method parameter - tournamentId cannot 0";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return tournamentDao.get(tournamentId);
    }

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

    @Override
    public Tournament getTournament(String seasonName, String tournamentName) {
        return tournamentDao.getTournament(seasonName, tournamentName);
    }

    private List<TournamentDto> convertToTournamentDtoList(List<Tournament> tournaments) {
        return tournaments.stream().map(this::convertToTournamentDto).collect(Collectors.toList());
    }

    private TournamentDto convertToTournamentDto(Tournament tournament) {
        return TournamentDto.builder()
                .name(tournament.getName())
                .id(tournament.getId())
                .build();
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
