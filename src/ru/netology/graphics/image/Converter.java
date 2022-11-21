package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {
    private int maxWidth;
    private int maxHeight;
    private double maxRatio;
    private TextColorSchema schema = new Schema();

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        int width = img.getWidth();
        int height = img.getHeight();
        double ratio;
        if ((height / width) > (width / height)) {
            ratio = height / width;
        } else {
            ratio = width / height;
        }
        if ((ratio > maxRatio) && maxRatio != 0.0) {
            throw new BadImageSizeException(ratio, maxRatio);
        }
        int newWidth = width;
        int newHeight = height;
        if ((width > maxWidth && maxWidth != 0) || (height > maxHeight && maxHeight != 0)) {
            double newRatio = 0;
            if (maxWidth != 0) {
                newRatio = width / maxWidth;
            }
            if (maxHeight != 0) {
                if (height / maxHeight > newRatio) {
                    newRatio = height / maxHeight;
                }
            }
            newWidth = (int) (width / newRatio);
            newHeight = (int) (height / newRatio);
        }
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();
        int[] colors = new int[3];
        char[][] resChars = new char[newHeight][newWidth];
        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = bwRaster.getPixel(w, h, colors)[0];
                char c = schema.convert(color);
                resChars[h][w] = c;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                res.append(resChars[h][w]);
                res.append(resChars[h][w]);
            }
            res.append("\n");
        }

        return String.valueOf(res);
    }

    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
