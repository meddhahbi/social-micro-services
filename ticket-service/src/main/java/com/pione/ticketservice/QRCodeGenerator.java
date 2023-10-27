package com.pione.ticketservice;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class QRCodeGenerator {

    public static void main(String[] args) {
        String text = "https://example.com"; // Replace with your desired content
        int width = 300;
        int height = 300;
        String format = "png"; // You can choose other formats like "jpg", "gif", etc.
        String outputPath = "qrcode.png"; // Output file path

        try {
            generateQRCode(text, width, height, format, outputPath);
            System.out.println("QR Code generated successfully!");
        } catch (Exception e) {
            System.err.println("Error generating QR Code: " + e.getMessage());
        }
    }

    public static void generateQRCode(String text, int width, int height, String format, String outputPath)
            throws WriterException, IOException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        File outputFile = new File(outputPath);
        ImageIO.write(image, format, outputFile);
    }
}
