package ua.vn.os.ulteam.service.logic;

import ua.vn.os.ulteam.service.dto.GameDto;

import java.util.List;

/**
 * @Author oleg.sukhov
 */
public interface GameService {
    public List<GameDto> getAllGamesDtos();
}
