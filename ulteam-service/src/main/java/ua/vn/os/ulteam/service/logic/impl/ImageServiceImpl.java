package ua.vn.os.ulteam.service.logic.impl;

import org.apache.commons.codec.binary.Base64;
import ua.vn.os.ulteam.service.logic.ImageService;

public class ImageServiceImpl implements ImageService {

    @Override
    public String convertImageForView(byte[] image) {
        return Base64.encodeBase64String(image);
    }
}
