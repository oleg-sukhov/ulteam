package ua.vn.os.ulteam.service.logic.impl;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.vn.os.ulteam.model.dao.SeasonDao;
import ua.vn.os.ulteam.model.entity.Season;
import ua.vn.os.ulteam.service.config.ServiceConfig;
import ua.vn.os.ulteam.service.dto.SeasonDto;
import ua.vn.os.ulteam.service.logic.SeasonService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author oleg.sukhov
 */
@Service
@Import(ServiceConfig.class)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SeasonTransactionalServiceImpl implements SeasonService {

    @Resource
    private SeasonDao seasonDao;

    @Override
    public List<SeasonDto> getAllSeasonDtoList() {
        List<Season> allSeasons =  seasonDao.getAllSeasons();
        return convertToDtoList(allSeasons);
    }

    @Override
    public Season getSeasonByName(String seasonName) {
        return seasonDao.getSeasonByName(seasonName);
    }

    private List<SeasonDto> convertToDtoList(List<Season> seasons) {
        return seasons.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private SeasonDto convertToDto(Season season) {
        return SeasonDto.builder()
                .id(season.getId())
                .name(season.getName())
                .build();
    }
}
