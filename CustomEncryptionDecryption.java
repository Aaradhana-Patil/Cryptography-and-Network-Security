package swap;

import java.util.Scanner;

public class CustomEncryptionDecryption {

    // Encrypts text using a shift (key)
    public static String encrypt(String text, int shiftKey) {
        StringBuilder result = new StringBuilder();

        // Traverse the text
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // Encrypt uppercase characters
            if (Character.isUpperCase(ch)) {
                char encryptedChar = (char) (((ch - 'A' + shiftKey) % 26) + 'A');
                result.append(encryptedChar);
            }
            // Encrypt lowercase characters
            else if (Character.isLowerCase(ch)) {
                char encryptedChar = (char) (((ch - 'a' + shiftKey) % 26) + 'a');
                result.append(encryptedChar);
            }
            // Replace space with #
            else if (ch == ' ') {
                result.append('#');
            }
            // Replace punctuation symbols with $
            else if (ch == '!' || ch == '?' || ch == '.' || ch == ',') {
                result.append('$');
            }
            // For other characters, don't encrypt them
            else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    // Decrypts text using a shift (key)
    public static String decrypt(String cipherText, int shiftKey) {
        StringBuilder result = new StringBuilder();

        // Traverse the cipher text
        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);

            // Decrypt uppercase characters
            if (Character.isUpperCase(ch)) {
                char decryptedChar = (char) (((ch - 'A' - shiftKey + 26) % 26) + 'A');
                result.append(decryptedChar);
            }
            // Decrypt lowercase characters
            else if (Character.isLowerCase(ch)) {
                char decryptedChar = (char) (((ch - 'a' - shiftKey + 26) % 26) + 'a');
                result.append(decryptedChar);
            }
            // Replace # with space
            else if (ch == '#') {
                result.append(' ');
            }
            // Replace $ with original punctuation symbols
            else if (ch == '$') {
                result.append('!');
            }
            // For other characters, don't decrypt them
            else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice;

        do {
            // User input for plaintext
            System.out.print("Enter the text to be encrypted: ");
            String plainText = sc.nextLine();

            // User input for the key
            System.out.print("Enter the shift key: ");
            int key = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            // Encrypt the plaintext
            String encryptedText = encrypt(plainText, key);
            System.out.println("Encrypted Text: " + encryptedText);

            // Decrypt the encrypted text
            String decryptedText = decrypt(encryptedText, key);
            System.out.println("Decrypted Text: " + decryptedText);

            // Prompt the user if they want to continue or not
            System.out.println("\nDo you want to encrypt/decrypt another text? (yes/no): ");
            choice = sc.nextLine();
        } while (choice.equalsIgnoreCase("yes"));

        sc.close();
        System.out.println("Program ended.");
    }
}

