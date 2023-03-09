/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class QRcodeGenerator {
    
     public void createQr(String data) {
        try {
            String qrCodeData = data;
                    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        
        // create a random number generator
            Random random = new Random();
        
        // generate a random name
        String name = "";
        for (int i = 0; i < 4; i++) {
            char c = alphabet[random.nextInt(alphabet.length)];
            name += c;
        }



            String filePath = "C:\\Users\\asus\\Desktop\\ranimjavafx\\StoreShip2\\src\\GUI\\Images"+name+".jpg";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }
    
} 