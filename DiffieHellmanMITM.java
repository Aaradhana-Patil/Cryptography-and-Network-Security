package cns;


import java.util.Scanner;

public class DiffieHellmanMITM{
	//function for modular exponiation
	private static long modexp(long base, long exp, long mod) {
		long result=1;
		base=base%mod;
		while(exp>0) {
			if(exp%2==1) {
				result=(result*base)%mod;
			}
			exp=exp>>1;
			base=(base*base)%mod;			
		}
		return result;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.print("Enter a Prime number(p):");
		long p=sc.nextLong();
		System.out.print("Enter generator (g):");
		long g=sc.nextLong();
		System.out.print("Enter Alice's Private key:");
		long a=sc.nextLong();
		System.out.print("Enter Bob's Private key:");
		long b=sc.nextLong();
		System.out.print("Enter Darth's Private key(Man in the middle):");
		long d=sc.nextLong();
		
		//public key calculations
		long A=modexp(g,a,p); //public key of alice
		long B=modexp(g,b,p);//public key of bob
		long DA=modexp(g,d,p);//public key of darth send to alice
		long DB=modexp(g,d,p);//public key of darth send to bob
		
		while(true) {
			System.out.println("\nChoose an option:");
			System.out.println("1. Exchange Public Keys between Alice and Bob");
			System.out.println("2. Perform Man-In-The-Middle Attack by Darth");          
			System.out.println("3. Exit");
			int choice = sc.nextInt();
			
			if(choice==1) {
				System.out.println("Alice's Public Key:"+A);
				System.out.println("Bob's Public Key:"+B);
				long sharedSecretAlice = modexp(B, a, p);
				long sharedSecretBob = modexp(A, b, p);
				
				System.out.println("Alice's computes shared key:"+sharedSecretAlice);
				System.out.println("bob's computes shared key:"+sharedSecretBob);
				
				if(sharedSecretAlice==sharedSecretBob) {
					System.out.println("secured communication established");
				}
				else {
					System.out.println("shared secret dont match");
				}
			}
			else if(choice==2) {
				System.out.println("Alice's Public Key:"+A);
				System.out.println("Bob's Public Key:"+B);
				
				System.out.println("darth shares public key with alice:"+DA);
				System.out.println("darth shares public key with Bob:"+DB);
				
				long sharedSecretAlice1 = modexp(DA, a, p);
				long sharedSecretBob1 = modexp(DB, b, p);
				
				System.out.println("alice computes shared secret with darth:"+sharedSecretAlice1);
				System.out.println("bob computes shared secret with darth:"+sharedSecretBob1);
				
				long sharedSecretDarthAlice= modexp(A, d ,p);
				long sharedSecretDarthBob= modexp(B, d ,p);
				
				System.out.println("darth computes shared secret with alice:"+sharedSecretDarthAlice);
				System.out.println("darth computes shared secret with bob:"+sharedSecretDarthBob);
				
			   if(sharedSecretAlice1==sharedSecretDarthAlice && sharedSecretBob1==sharedSecretDarthBob) {
				   System.out.print("MITM attack successful by darth..he can interfere and modify the communication");
			   }
			   else {
				   System.out.println("MITM attack failed");
			   }
			   
			}
			
			else if(choice==3) {
				System.out.println("program exits");
				break;
			}
			else {
				System.out.println("invalid choice!");
			}
		}
		
		
	}
}





























//public class DiffieHellmanMITM {
//
//    // Function to perform modular exponentiation
//    public static long modExp(long base, long exp, long mod) {
//        long result = 1;
//        base = base % mod;
//        while (exp > 0) {
//            if (exp % 2 == 1) { // If exp is odd
//                result = (result * base) % mod;
//            }
//            exp = exp >> 1; // Divide exp by 2
//            base = (base * base) % mod;
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // Inputs for Diffie-Hellman key exchange
//        System.out.print("Enter a prime number (p): ");
//        long p = scanner.nextLong(); // Prime modulus
//
//        System.out.print("Enter a generator (g): ");
//        long g = scanner.nextLong(); // Generator base
//
//        System.out.print("Enter Alice's private key: ");
//        long a = scanner.nextLong(); // Pragati's private key
//
//        System.out.print("Enter bob's private key: ");
//        long b = scanner.nextLong(); // Esha's private key
//
//        System.out.print("Enter darth's private key (for MITM): ");
//        long d = scanner.nextLong(); // Tisha's private key for MITM attack
//
//        // Public key calculations
//        long A = modExp(g, a, p); // Alice's public key
//        long B = modExp(g, b, p); // Bob's public key
//        long DA = modExp(g, d, p); // Darth's public key (sent to Alice)
//        long DB = modExp(g, d, p); //Darth's public key (sent to Bob)
//
//        while (true) {
//            System.out.println("\nChoose an option:");
//            System.out.println("1. Exchange Public Keys between Alice and Bob");
//            System.out.println("2. Perform Man-In-The-Middle Attack by Darth");
//            System.out.println("3. Exit");
//            int choice = scanner.nextInt();
//
//            if (choice == 1) {
//                // Simulate secure key exchange
//                System.out.println("Alice's Public Key: " + A);
//                System.out.println("Bob's Public Key: " + B);
//
//                // Compute shared secrets
//                long sharedSecretAlice = modExp(B, a, p);
//                long sharedSecretBob = modExp(A, b, p);
//
//                System.out.println("Alice computes shared secret: " + sharedSecretAlice);
//                System.out.println("Bob computes shared secret: " + sharedSecretBob);
//
//                if (sharedSecretAlice == sharedSecretBob) {
//                    System.out.println("Secure communication established.");
//                } else {
//                    System.out.println("Shared secrets do not match!");
//                }
//            } 
//            else if (choice == 2) {
//                // Simulate MITM attack
//                System.out.println("Alice's Public Key: " + A);
//                System.out.println("Bob's Public Key: " + B);
//
//                // darth intercepts and replaces public keys
//                System.out.println("Darth sends Public Key (to Alice): " + DA);
//                System.out.println("Darth sends Public Key (to Bob): " + DB);
//
//                // alice computes shared secret with darth
//                long sharedSecretAlice1 = modExp(DA, a, p);
//                System.out.println("Alice computes shared secret with Darth: " + sharedSecretAlice1);
//
//                // bob computes shared secret with darth
//                long sharedSecretBob1 = modExp(DB, b, p);
//                System.out.println("Bob computes shared secret with Darth: " + sharedSecretBob1);
//
//                // darth computes shared secrets with both
//                long sharedSecretDarthAlice = modExp(A, d, p);
//                long sharedSecretDarthBob = modExp(B, d, p);
//
//                System.out.println("Darth computes shared secret with Alice: " + sharedSecretDarthAlice);
//                System.out.println("Darth computes shared secret with Bob: " + sharedSecretDarthBob);
//
//                if (sharedSecretAlice1 == sharedSecretDarthAlice && sharedSecretBob1 == sharedSecretDarthBob) {
//                    System.out.println("Man-In-The-Middle attack successful. Darth can intercept and modify messages.");
//                } else {
//                    System.out.println("MITM attack failed!");
//                }
//            } else if (choice == 3) {
//                // Exit program
//                System.out.println("Exiting program.");
//                break;
//            } else {
//                System.out.println("Invalid option! Please try again.");
//            }
//        }
//        scanner.close();
//    }
//}
