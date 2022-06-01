package com.furthurprogramming.assignment2.util;

import com.furthurprogramming.assignment2.Main;
import javafx.scene.image.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageUtil {
    public static byte[] ImageToByteArray(String imgPath) throws IOException {

        if (imgPath == null)
            return null;

        imgPath = imgPath.replace("file:/", "");
        ImageIO.setUseCache(false);
        BufferedImage bImage = ImageIO.read(new File(imgPath));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos ); // png extension is a must.
        return bos.toByteArray();
    }

    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }
    public static Image ByteArrayToImage(byte[] buffer) throws IOException {
        if (buffer == null)
            return null;

        ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
        BufferedImage bImage2 = ImageIO.read(bis);

        return convertToFxImage(bImage2);
    }

    public static Image ReadImageFromFile(String path) throws IOException {

        return ByteArrayToImage(ImageToByteArray(path));


    }

    public static File chooseImageFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        return fileChooser.showOpenDialog(Main.getStage());
    }
}
