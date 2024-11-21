package cns;

import java.io.*;
import java.util.Scanner;

public class assi8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Input image file path
            System.out.print("Enter the path of the image to encrypt: ");
            String inputPath = sc.nextLine();

            // Input key for encryption/decryption
            System.out.print("Enter the encryption key (positive integer): ");
            int key = sc.nextInt();

            // Encrypt the image
            String encryptedPath = "encrypted_image.jpg";
            encryptImage(inputPath, encryptedPath, key);
            System.out.println("Image encrypted and saved as: " + encryptedPath);

            // Decrypt the image
            String decryptedPath = "decrypted_image.jpg";
            decryptImage(encryptedPath, decryptedPath, key);
            System.out.println("Image decrypted and saved as: " + decryptedPath);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    // Method to encrypt the image
    public static void encryptImage(String inputPath, String outputPath, int key) throws IOException {
        processImage(inputPath, outputPath, key);
    }

    // Method to decrypt the image
    public static void decryptImage(String inputPath, String outputPath, int key) throws IOException {
        processImage(inputPath, outputPath, -key); // Reverse the encryption key
    }

    // Common method to process (encrypt/decrypt) the image
    private static void processImage(String inputPath, String outputPath, int key) throws IOException {
        FileInputStream fis = new FileInputStream(inputPath);
        FileOutputStream fos = new FileOutputStream(outputPath);

        int byteData;
        while ((byteData = fis.read()) != -1) {
            fos.write(byteData + key); // Shift each byte value by the key
        }

        fis.close();
        fos.close();
    }
}
