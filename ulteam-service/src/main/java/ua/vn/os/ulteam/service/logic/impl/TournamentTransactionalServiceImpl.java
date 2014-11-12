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
    public List<TournamentDto> getTournamentsInSeason(String seasonName) {
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
    public Tournament getTournament(String seasonName, String tournamentName) {
        if(seasonName == null || tournamentName.isEmpty()) {
            String errorMessage = "Incorrect method parameter - (seasonName or tournamentName) cannot be null or empty";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        Season season = seasonService.getSeasonByName(seasonName);
        List<Tournament> tournamentsInSeason = getTournamentsInSeason(season);
        Tournament resultTournament = null;
        int tournamentCount = 0;

        for (Tournament tournament : tournamentsInSeason) {
            if(tournamentName.equals(tournament.getName())) {
                resultTournament = tournament;
                tournamentCount++;
            }
        }

        if(tournamentCount > 1) {
            String errorMessage = "Number of tournaments in season cannot be more than one";
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        return resultTournament;
    }

    @Override
    public List<TourDto> getTournamentTours(String seasonName, String tournamentName) {
        Tournament tournament = getTournament(seasonName, tournamentName);
        return convertToTourDtoList(tournament.getTours());
    }

    private List<TournamentDto> convertToTournamentDtoList(List<Tournament> tournaments) {
        return tournaments.stream().map(this::convertToTournamentDto).collect(Collectors.toList());
    }

    private TournamentDto convertToTournamentDto(Tournament tournament) {
        return TournamentDto.builder().name(tournament.getName()).build();
    }

    private List<TourDto> convertToTourDtoList(List<Tour> tours) {
        return tours.stream().map(this::convertToTourDto).collect(Collectors.toList());
    }

    private TourDto convertToTourDto(Tour tour) {
        return TourDto.builder().name(tour.getName()).build();
    }
}
