package ua.vn.os.ulteam.service.util;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.jpeg.JpegDirectory;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by os
 */
public class ImageUtil {
    public static final double DEGREE_90 = Math.PI / 2;

    public static BufferedImage rotateOn90Degrees(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage biFlip = new BufferedImage(height, width, image.getType());
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
                biFlip.setRGB(height-1-j, width-1-i, image.getRGB(i, j));
        return biFlip;
    }

    //TODO: implement image rotation
    public static BufferedImage readImageInformation(BufferedImage bufferedImage, File imageFile) {
        int width=0, height=0, orientation=1;
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
            Directory directory = metadata.getDirectory(ExifIFD0Directory.class);
            JpegDirectory jpegDirectory = (JpegDirectory)metadata.getDirectory(JpegDirectory.class);

            orientation = 1;

            orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);

            width = jpegDirectory.getImageWidth();
            height = jpegDirectory.getImageHeight();

        } catch (Exception me) {
            //logger.warn("Could not get orientation");
        }

        AffineTransform t = new AffineTransform();

        switch (orientation) {
            case 1:
                return bufferedImage;
            case 2: // Flip X
                t.scale(-1.0, 1.0);
                t.translate(-width, 0);
                break;
            case 3: // PI rotation
                t.translate(width, height);
                t.rotate(Math.PI);
                break;
            case 4: // Flip Y
                t.scale(1.0, -1.0);
                t.translate(0, -height);
                break;
            case 5: // - PI/2 and Flip X
                t.rotate(-Math.PI / 2);
                t.scale(-1.0, 1.0);
                break;
            case 6: // -PI/2 and -width
                t.translate(height, 0);
                t.rotate(Math.PI / 2);
                break;
            case 7: // PI/2 and Flip
                t.scale(-1.0, 1.0);
                t.translate(-height, 0);
                t.translate(0, width);
                t.rotate(  3 * Math.PI / 2);
                break;
            case 8: // PI / 2
                t.translate(0, width);
                t.rotate(  3 * Math.PI / 2);
                break;
        }

        return transformImage(bufferedImage, t);

    }

    public static BufferedImage transformImage(BufferedImage image, AffineTransform transform) {

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);

        BufferedImage destinationImage = op.createCompatibleDestImage(image,  (image.getType() == BufferedImage.TYPE_BYTE_GRAY)? image.getColorModel() : null );
        Graphics2D g = destinationImage.createGraphics();
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, destinationImage.getWidth(), destinationImage.getHeight());
        destinationImage = op.filter(image, destinationImage);
        return destinationImage;
    }
}
