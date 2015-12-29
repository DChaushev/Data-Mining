package com.fmi.dm.hw10.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Dimitar
 */
public class ImageFilesOperator {

    /**
     *
     * @param path The path to the file
     * @return BufferedImage containing the image
     *
     * @see ImageFilesOperator#loadImage(java.io.File)
     */
    public static BufferedImage loadImage(String path) {
        return loadImage(new File(path));
    }

    /**
     *
     * @param file the image file.
     * @return Buffered image if the file exists and is an image. Null otherwise
     */
    public static BufferedImage loadImage(File file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(ImageFilesOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    /**
     *
     * @param image BufferedImage to be saved
     * @param format The format of the output image
     * @param path Absolute file path.
     * @return true if the file is created successfully. False otherwise
     *
     * @see ImageFilesOperator#saveImage(java.awt.image.BufferedImage,
     * java.lang.String, java.io.File)
     */
    public static boolean saveImage(BufferedImage image, String format, String path) {
        return saveImage(image, format, new File(path));
    }

    /**
     *
     * @param image BufferedImage to be saved
     * @param format The format of the output image
     * @param file The file to be created
     * @return true if the file is created successfully. False otherwise
     */
    public static boolean saveImage(BufferedImage image, String format, File file) {
        boolean result = false;
        try {
            ImageIO.write(image, format, file);
            result = true;
        } catch (IOException ex) {
            Logger.getLogger(ImageFilesOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
