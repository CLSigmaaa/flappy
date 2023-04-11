package edu.val.models;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    private final File file;

    public ImageLoader(File file) {
        this.file = file;
    }

    public Graphics2D loadImage() throws IOException {
        BufferedImage image = ImageIO.read(file);
        return image.createGraphics();
    }
}
