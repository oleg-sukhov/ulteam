package ua.vn.os.ulteam.service.logic.impl;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import ua.vn.os.ulteam.model.entity.Photo;
import ua.vn.os.ulteam.service.logic.ImageService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ImageServiceImpl implements ImageService {

    private static Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    private MessageSource messageSource;

    @Override
    public String convertImageForView(byte[] image) {
        return Base64.encodeBase64String(image);
    }

    @Override
    public Set<String> loadPhotosFromFileSystemAndConvertToBase64(String albumPath, Set<Photo> photos) {
        Set<String> imageSet = new HashSet<>();
        for(Photo photo : photos) {
            throw new UnsupportedOperationException();
            //imageSet.add(loadPhotoFromFileSystemAndConvertToBase64(albumPath, photo));
        }
        return imageSet;
    }

    @Override
    public String loadPhotoFromFileSystemAndConvertToBase64(String albumPath, String photoName) {
        String imagePath = new StringBuilder().append(albumPath).append(photoName).toString();
        return Base64.encodeBase64String(loadImageFromFileSystem(imagePath));
    }

    private byte[] loadImageFromFileSystem(String imagePath) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage bufferedImage = null;
        File imageFile = new File(imagePath);

        try {
            bufferedImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            Object[] args = new Object[]{imagePath, e.getMessage().toString()};
            logger.error(messageSource.getMessage("ua.vn.os.ulteam.service.load.image.error", args, Locale.getDefault()));
        }

        try {
            ImageIO.write(bufferedImage, "jpg", baos);
            baos.flush();
        } catch (IOException e) {
            Object[] args = new Object[]{e.getMessage().toString()};
            logger.error(messageSource.getMessage("ua.vn.os.ulteam.service.write.image.to.baos", args, Locale.getDefault()));
        }

        return baos.toByteArray();
    }
}
