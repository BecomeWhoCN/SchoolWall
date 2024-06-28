package online.xzjob.schoolwall.util;

import org.springframework.web.multipart.MultipartFile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageCompressor {

    public static byte[] compressImage(MultipartFile file, int targetWidth, int targetHeight, String formatName, long maxSizeKB) throws IOException {
        // 读取原始图片
        BufferedImage originalImage = ImageIO.read(file.getInputStream());

        // 压缩图片尺寸
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();

        // 写入字节数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(outputImage, formatName, baos);

        // 如果大小仍然超过目标大小，则尝试降低质量
        byte[] imageBytes = baos.toByteArray();
        while (imageBytes.length > maxSizeKB * 1024) {
            BufferedImage tempImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            baos = new ByteArrayOutputStream();
            ImageIO.write(tempImage, formatName, baos);
            imageBytes = baos.toByteArray();
        }

        return imageBytes;
    }
}
