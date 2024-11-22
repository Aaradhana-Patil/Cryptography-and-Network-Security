package cns;


	import javax.crypto.Cipher;
	import java.security.*;
	import java.util.Scanner;

	public class SecureCommunicationSystem  {

	    public static KeyPair generateKeyPair()throws Exception{
	        KeyPairGenerator generator=KeyPairGenerator.getInstance("RSA");
	        generator.initialize(2048);
	        return generator.generateKeyPair();
	    }
	    public static byte[] encryptMessage(String message, PublicKey yPublicKey) throws Exception{
	        Cipher cipher=Cipher.getInstance("RSA");
	        cipher.init(Cipher.ENCRYPT_MODE,yPublicKey);
	        return cipher.doFinal(message.getBytes());

	    }
	    public static byte[] signMessage(String message, PrivateKey xPrivateKey)throws Exception{
	         Signature signature=Signature.getInstance("SHA256withRSA");
	         signature.initSign(xPrivateKey);
	         signature.update(message.getBytes());
	         return signature.sign();
	    }
	    public static boolean verifySignature(String message, PublicKey xPublicKey, byte[] sign) throws Exception{
	          Signature signature=Signature.getInstance("SHA256withRSA");
	          signature.initVerify(xPublicKey);
	          signature.update(message.getBytes());
	          return signature.verify(sign);
	    }
	    public static byte[] hashMessage(String message)throws Exception{
	        MessageDigest digest=MessageDigest.getInstance("SHA-256");
	        return  digest.digest(message.getBytes());

	    }

	    public static String decryptMessage(byte[] encryptedMessage , PrivateKey yPrivateKey)throws Exception{
	        Cipher cipher=Cipher.getInstance("RSA");
	        cipher.init(Cipher.DECRYPT_MODE,yPrivateKey);
	        byte[] decryptedBytes=cipher.doFinal(encryptedMessage);
	        return new String(decryptedBytes);
	    }
	    public static void main(String[] args) throws Exception {
	        KeyPair xKeyPair = generateKeyPair();
	        PublicKey xPublicKey = xKeyPair.getPublic();
	        PrivateKey  xPrivateKey = xKeyPair.getPrivate();

	        KeyPair yKeyPair = generateKeyPair();
	        PublicKey yPublicKey = yKeyPair.getPublic();
	        PrivateKey yPrivateKey = yKeyPair.getPrivate();

	        //String msg="Hello World";

	        Scanner sc=new Scanner(System.in);
	        System.out.println("Enter the message: ");
	        String msg=sc.nextLine();
	        byte[]hash=hashMessage(msg);
	        System.out.println("Hash: "+byteToHex(hash));

	        byte[]signature=signMessage(new String(hash),xPrivateKey);
	        System.out.println("Signature: "+byteToHex(signature));

	        byte[] encryptedMsg=encryptMessage(msg,yPublicKey);
	        System.out.println("Encrypted Message: "+byteToHex(encryptedMsg));


	        String decryptedMsg=decryptMessage(encryptedMsg,yPrivateKey);
	        System.out.println("Decrypted Message: "+decryptedMsg);
	         boolean isVerified=verifySignature(new String(hash),xPublicKey,signature);
	        System.out.println("Is Verified: "+isVerified);

	       boolean isIntegrityManintained=MessageDigest.isEqual(hash,hashMessage(decryptedMsg));
	        System.out.println("Is Integrity Maintained: "+isIntegrityManintained);


	    }
	    public static String byteToHex(byte []bytes){
	        StringBuilder sb=new StringBuilder();
	        for(byte b:bytes){
	            sb.append(String.format("%02x",b));

	        }
	        return sb.toString();
	    }
	}