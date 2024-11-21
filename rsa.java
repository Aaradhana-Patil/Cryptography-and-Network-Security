package cns;
import java.util.Scanner;

public class rsa{
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		//key generation
		System.out.print("Enter first prime number(p):");
		long p=sc.nextLong();
		System.out.print("Enter second prime number(q):");
		long q=sc.nextLong();
		
		long n=p*q;
		long phi=(p-1)*(q-1);
		long e=7;
		while(gcd(e,phi)!=1) {
			e+=2;
		}
		
		long d=modInverse(e,phi);
		System.out.println("Public key:("+e+","+n+")");
		System.out.println("Private Key:(" +d+","+n+")");
		
		//encrytion
		System.out.print("Enter message to encrypt(as a integer):");
        long message=sc.nextLong();
        
        long startencrypt = System.nanoTime();
        long ciphertext=modexp(message,e,n);
        long endencrypt=System.nanoTime();
        System.out.println("Encryption time: %.3f ms\n" +(endencrypt-startencrypt)/ 1e6);
        System.out.println("Encrypted Message:"+ciphertext);
        
        //decryption
        long startdecrypt = System.nanoTime();
        long decryptedmsg=modexp(ciphertext,d,n);
        long enddecrypt=System.nanoTime();
        System.out.println("Encryption time: %.3f ms\n" +(enddecrypt-startdecrypt)/ 1e6);
        System.out.println("Decrypted Message:"+ decryptedmsg);
        
        sc.close();
        
	}
	private static long gcd(long a, long b) {
		while(b!=0) {
			long temp=b;
			b=a%b;
			a=temp;
		}
		return a;
		
	}
	
	private static long modexp(long base, long exp, long mod) {
		long result=1;
		base=base%mod;
		while(exp>0) {
			if((exp&1)==1) {
				result=(result*base)%mod;
			}
			exp=exp>>1;
			base=(base*base)%mod;
		}
		
		return result;
	}
	 
	
	private static long modInverse(long e, long phi) {
		for(long d=1;d<phi;d++) {
			if((e*d)%phi==1) {
				return d;
			}
		}
		throw new IllegalArgumentException("Modular inverse not found");
	}
	
}


	































//import java.util.Scanner;
//
//public class rsa {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // Input prime numbers
//        System.out.print("Enter first prime number (p): ");
//        long p = scanner.nextLong();
//        System.out.print("Enter second prime number (q): ");
//        long q = scanner.nextLong();
//
//        // Step 1: Generate keys
//        long n = p * q;
//        long phi = (p - 1) * (q - 1);
//        long e = 7; // Start with a small odd number
//        while (gcd(e, phi) != 1) {
//            e += 2; // Increment by 2 to stay odd
//        }
//
//        long d = modularInverse(e, phi); // Compute private key
//        System.out.println("Public Key: (" + e + ", " + n + ")");
//        System.out.println("Private Key: " + d);
//
//        // Step 2: Encrypt the message
//        System.out.print("Enter a message to encrypt (as an integer): ");
//        long message = scanner.nextLong();
//        long startEncrypt = System.nanoTime();
//        long ciphertext = modExp(message, e, n); // Encrypt the message
//        long endEncrypt = System.nanoTime();
//        System.out.printf("Encryption Time: %.3f ms\n", (endEncrypt - startEncrypt) / 1e6);
//        System.out.println("Encrypted message: " + ciphertext);
//
//        // Step 3: Decrypt the message
//        long startDecrypt = System.nanoTime();
//        long decryptedMessage = modExp(ciphertext, d, n); // Decrypt the message
//        long endDecrypt = System.nanoTime();
//        System.out.printf("Decryption Time: %.3f ms\n", (endDecrypt - startDecrypt) / 1e6);
//        System.out.println("Decrypted message: " + decryptedMessage);
//
//        scanner.close();
//    }
//
//    // Function to calculate GCD
//    private static long gcd(long a, long b) {
//        while (b != 0) {
//            long temp = b;
//            b = a % b;
//            a = temp;
//        }
//        return a;
//    }

    // Function to calculate modular exponentiation
//    private static long modExp(long base, long exp, long mod) {
//        long result = 1;
//        base = base % mod; // Ensure base is within mod
//        while (exp > 0) {
//            if ((exp & 1) == 1) { // Check if exp is odd
//                result = (result * base) % mod;
//            }
//            exp = exp >> 1; // Divide exp by 2
//            base = (base * base) % mod; // Square the base
//        }
//        return result;
//    }
//
//    // Function to calculate modular inverse
//    private static long modularInverse(long e, long phi) {
//        for (long d = 1; d < phi; d++) {
//            if ((e * d) % phi == 1) {
//                return d;
//            }
//        }
//        throw new IllegalArgumentException("Modular inverse not found");
//    }
//}


//package cns;
//
//import java.util.Scanner;
//
//public class rsa {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // Input prime numbers
//        System.out.print("Enter first prime number (p): ");
//        long p = scanner.nextLong();
//        System.out.print("Enter second prime number (q): ");
//        long q = scanner.nextLong();
//
//        // Step 1: Generate keys
//        long n = p * q;
//        long phi = (p - 1) * (q - 1);
//        long e = 7; // Start with a small odd number
//        while (gcd(e, phi) != 1) {
//            e += 2; // Increment by 2 to stay odd
//        }
//
//        long d = modularInverse(e, phi); // Compute private key
//        System.out.println("Public Key: (" + e + ", " + n + ")");
//        System.out.println("Private Key: " + d);
//
//        // Step 2: Encrypt the message
//        System.out.print("Enter a message to encrypt (as an integer): ");
//        long message = scanner.nextLong();
//        long ciphertext = modExp(message, e, n); // Encrypt the message
//        System.out.println("Encrypted message: " + ciphertext);
//
//        // Step 3: Decrypt the message
//        long decryptedMessage = modExp(ciphertext, d, n); // Decrypt the message
//        System.out.println("Decrypted message: " + decryptedMessage);
//
//        scanner.close();
//    }
//
//    // Function to calculate GCD
//    private static long gcd(long a, long b) {
//        while (b != 0) {
//            long temp = b;
//            b = a % b;
//            a = temp;
//        }
//        return a;
//    }
//
//    // Function to calculate modular exponentiation
//    private static long modExp(long base, long exp, long mod) {
//        long result = 1;
//        base = base % mod; // Ensure base is within mod
//        while (exp > 0) {
//            if ((exp & 1) == 1) { // Check if exp is odd
//                result = (result * base) % mod;
//            }
//            exp = exp >> 1; // Divide exp by 2
//            base = (base * base) % mod; // Square the base
//        }
//        return result;
//    }
//
//    // Function to calculate modular inverse
//    private static long modularInverse(long e, long phi) {
//        for (long d = 1; d < phi; d++) {
//            if ((e * d) % phi == 1) {
//                return d;
//            }
//        }
//        throw new IllegalArgumentException("Modular inverse not found");
//    }
//}
