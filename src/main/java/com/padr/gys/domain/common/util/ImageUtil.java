package com.padr.gys.domain.common.util;

import java.io.File;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.awt.Image;
import java.awt.image.BufferedImage;

@Component
@RequiredArgsConstructor
public class ImageUtil {

    private final FileUtil fileUtil;

    public void resizeImages(File rawImageFile, int width, int height) {
        try {
            BufferedImage rawImageBufferedImage = ImageIO.read(rawImageFile);

            Image resizedImage = rawImageBufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);

            BufferedImage resizedBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            resizedBufferedImage.getGraphics().drawImage(resizedImage, 0, 0, null);

            ImageIO.write(resizedBufferedImage, fileUtil.getFileExtension(rawImageFile.getName()), rawImageFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
