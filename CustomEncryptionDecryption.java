package cns;

import java.util.Scanner;

public class CustomEncryption {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the string
        System.out.print("Enter the string: ");
        String input = sc.nextLine();

        // Input the key
        System.out.print("Enter the key: ");
        int key = sc.nextInt();

        // Encrypt the string
        String encrypted = encrypt(input, key);
        System.out.println("Encrypted String: " + encrypted);

        // Decrypt the string
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted String: " + decrypted);

        sc.close();
    }

    // Encryption method
    public static String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) { // Check if the character is a letter
                char base = Character.isLowerCase(c) ? 'a' : 'A'; // Determine case
                if (i % 2 == 0) { // Odd position (0-based indexing)
                    encrypted.append((char) ((c - base + key) % 26 + base));
                } else { // Even position
                    encrypted.append((char) ((c - base + (key - 1)) % 26 + base));
                }
            } else {
                encrypted.append(c); // Non-alphabet characters remain unchanged
            }
        }
        return encrypted.toString();
    }

    // Decryption method
    public static String decrypt(String input, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) { // Check if the character is a letter
                char base = Character.isLowerCase(c) ? 'a' : 'A'; // Determine case
                if (i % 2 == 0) { // Odd position (0-based indexing)
                    decrypted.append((char) ((c - base - key + 26) % 26 + base));
                } else { // Even position
                    decrypted.append((char) ((c - base - (key - 1) + 26) % 26 + base));
                }
            } else {
                decrypted.append(c); // Non-alphabet characters remain unchanged
            }
        }
        return decrypted.toString();
    }
}
