package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.model.entity.Season;
import ua.vn.os.ulteam.service.dto.SeasonDto;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface SeasonService {
    List<SeasonDto> getAllSeasons();
    Season getSeasonByName(String seasonName);
}
