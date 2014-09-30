package ua.vn.os.ulteam.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.vn.os.ulteam.service.dto.PhotoDto;
import ua.vn.os.ulteam.service.logic.PhotoAlbumService;
import java.util.List;

/**
 * Created by os.
 */
@RestController
public class PhotoRestController {

    @Autowired
    private PhotoAlbumService photoAlbumService;

    @RequestMapping(value = "/getAllPhotosInAlbum", method = RequestMethod.GET, produces = "application/json")
    public Long getAllPhotosInAlbum(@RequestParam(value = "id") long id) {

        return new Long(1);
    }
}
