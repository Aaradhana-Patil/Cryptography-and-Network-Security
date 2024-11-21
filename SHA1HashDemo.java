package cns;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class SHA1HashDemo {

    // Left rotate a 32-bit integer value by shift bits
    public static int leftRotate(int value, int shift) {
        return (value << shift) | (value >>> (32 - shift));
    }

    // Compute the SHA-1 hash of a message
    public static String sha1(String message) {
        // Initial hash values (constants)
        int h0 = 0x67452301;
        int h1 = 0xEFCDAB89;
        int h2 = 0x98BADCFE;
        int h3 = 0x10325476;
        int h4 = 0xC3D2E1F0;

        // Convert message to byte array
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        int originalByteLen = bytes.length;
        long originalBitLen = (long) originalByteLen * 8;

        // Step 1: Pre-processing (Padding)
        bytes = Arrays.copyOf(bytes, ((bytes.length + 8) / 64 + 1) * 64);
        bytes[originalByteLen] = (byte) 0x80; // Append single '1' bit

        // Append original length as 64-bit big-endian integer
        for (int i = 0; i < 8; i++) {
            bytes[bytes.length - 8 + i] = (byte) ((originalBitLen >>> (8 * (7 - i))) & 0xFF);
        }

        // Step 2: Process the message in 512-bit (64-byte) chunks
        for (int i = 0; i < bytes.length; i += 64) {
            // Break chunk into sixteen 32-bit big-endian words
            int[] w = new int[80];
            for (int j = 0; j < 16; j++) {
                w[j] = ((bytes[i + 4 * j] & 0xFF) << 24)
                     | ((bytes[i + 4 * j + 1] & 0xFF) << 16)
                     | ((bytes[i + 4 * j + 2] & 0xFF) << 8)
                     | (bytes[i + 4 * j + 3] & 0xFF);
            }

            // Extend the 16 words into 80 words for the message schedule
            for (int j = 16; j < 80; j++) {
                w[j] = leftRotate(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1);
            }

            // Initialize hash value for this chunk
            int a = h0;
            int b = h1;
            int c = h2;
            int d = h3;
            int e = h4;

            // Step 3: Main SHA-1 compression function
            for (int j = 0; j < 80; j++) {
                int f, k;
                if (0 <= j && j <= 19) {
                    f = (b & c) | ((~b) & d);
                    k = 0x5A827999;
                } else if (20 <= j && j <= 39) {
                    f = b ^ c ^ d;
                    k = 0x6ED9EBA1;
                } else if (40 <= j && j <= 59) {
                    f = (b & c) | (b & d) | (c & d);
                    k = 0x8F1BBCDC;
                } else {
                    f = b ^ c ^ d;
                    k = 0xCA62C1D6;
                }
                int temp = leftRotate(a, 5) + f + e + k + w[j];
                e = d;
                d = c;
                c = leftRotate(b, 30);
                b = a;
                a = temp;
            }

            // Add this chunk's hash to the result so far
            h0 += a;
            h1 += b;
            h2 += c;
            h3 += d;
            h4 += e;
        }

        // Produce the final hash value as a 160-bit number
        return String.format("%08x%08x%08x%08x%08x", h0, h1, h2, h3, h4);
    }

    public static void main(String[] args) {

       // String message = "The quick brown fox jumps over the lazy dog";
    	Scanner sc=new Scanner(System.in);
		System.out.print("Enter message for transmission:");
		String message = sc.nextLine();
		String hashvalue = sha1(message);
		System.out.println("SHA-1 Hashvalue :"+hashvalue);
		
    }
}

//import java.math.BigInteger;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Scanner;
//
//public class SHA1HashDemo{
//	public static String calculateSHA1(String input) {
//		try {
//			MessageDigest md = MessageDigest.getInstance("SHA-1");
//			byte[] MessageDigest=md.digest(input.getBytes());
//			BigInteger no= new BigInteger(1,MessageDigest);
//			
//			String hashtext=no.toString(16);
//			while(hashtext.length()>40) {
//				hashtext = "0"+hashtext;
//			}
//			return hashtext;
//			
//		}catch(Exception e) {
//			return "error calculating sha hash";
//		}
//	}
//	public static void main(String[] args) {
//		Scanner sc=new Scanner(System.in);
//		System.out.print("Enter message for transmission:");
//		String message = sc.nextLine();
//		String hashvalue = calculateSHA1(message);
//		System.out.println("SHA-1 Hashvalue :"+hashvalue);
//		sc.close();
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////import java.math.BigInteger;
////import java.security.MessageDigest;
////import java.security.NoSuchAlgorithmException;
////import java.util.Scanner;
////
////	public class SHA1HashDemo {
////	    public static String calculateSHA1(String input) {
////	        try {
////	            MessageDigest md = MessageDigest.getInstance("SHA-1");
////	            byte[] messageDigest = md.digest(input.getBytes());
////	            BigInteger no = new BigInteger(1, messageDigest);
////	            String hashtext = no.toString(16);
////	            while (hashtext.length() < 40) {
////	                hashtext = "0" + hashtext;
////	            }
////	            return hashtext;
////	        } catch (Exception e) {
////	            return "Error calculating SHA-1 hash.";
////	        }
////	    }
////
////	    public static void main(String[] args) {
////	        Scanner scanner = new Scanner(System.in);
////	        System.out.print("Enter the message: ");
////	        String message = scanner.nextLine();
////	        String hashValue = calculateSHA1(message);
////	        System.out.println("SHA-1 Hash Value: " + hashValue);
////	        scanner.close();
////	    }
////	}
