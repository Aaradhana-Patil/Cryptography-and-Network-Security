package swap;

import java.util.Scanner;
class PolyAlpha{
	public static String generatekey(String s, String key) {
		int x=s.length();
		for(int i=0; ;i++) {
			if(x==i) 
				i=0;
			
			if(key.length()==s.length()) 
				break;
			
			key+=(key.charAt(i));
		}
		return key;
	}
	public static String encryption(String s, String key) {
		String CipherText="";
		for(int i=0;i<s.length();i++) {
			int x=(s.charAt(i)+key.charAt(i))%26;
			x+='A';
			CipherText+=(char)(x);
		}
		return CipherText;
	}
	public static String decryption(String CipherText, String key) {
		String PlainText="";
		for(int i=0;i<CipherText.length()&& i< key.length();i++) {
			int x=(CipherText.charAt(i)-key.charAt(i)+26)%26;
			x+='A';
			PlainText+=(char)(x);
		}
		return PlainText;
	}
	public static String LowertoUpper(String str) {
		StringBuffer s = new StringBuffer(str); 
		for(int i=0;i<str.length();i++) {
			if(Character.isLowerCase(s.charAt(i))) {
				s.setCharAt(i, Character.toUpperCase(str.charAt(i)));
			}
		}
		str=s.toString();
		return str;
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter a string:");
		String s=sc.nextLine();
		
		String keyword="PCCOE";
		s = LowertoUpper(s);
        String Keyword = LowertoUpper(keyword);
//
       String key = generatekey(s, Keyword);
       String CipherText = encryption(s, key);
//
   System.out.println("Ciphertext : "   + CipherText + "\n");
//
   System.out.println("Original/Decrypted Text : "   + decryption(CipherText, key));
	}

}
// Java code to implement Vigenere Cipher

//class PolyAlpha
//{
//
//// This function generates the key in
//// a cyclic manner until it's length isi'nt
//// equal to the length of original text
//static String generateKey(String str, String key)
//{
//    int x = str.length();
//
//    for (int i = 0; ; i++)
//    {
//        if (x == i)
//            i = 0;
//        if (key.length() == str.length())
//            break;
//        key+=(key.charAt(i));
//    }
//    return key;
//}
//
//// This function returns the encrypted text
//// generated with the help of the key
//static String cipherText(String str, String key)
//{
//    String cipher_text="";
//
//    for (int i = 0; i < str.length(); i++)
//    {
//        // converting in range 0-25
//        int x = (str.charAt(i) + key.charAt(i)) %26;
//
//        // convert into alphabets(ASCII)
//        x += 'A';
//
//        cipher_text+=(char)(x);
//    }
//    return cipher_text;
//}
//
//// This function decrypts the encrypted text
//// and returns the original text
//static String originalText(String cipher_text, String key)
//{
//    String orig_text="";
//
//    for (int i = 0 ; i < cipher_text.length() && 
//                            i < key.length(); i++)
//    {
//        // converting in range 0-25
//        int x = (cipher_text.charAt(i) - 
//                    key.charAt(i) + 26) %26;
//
//        // convert into alphabets(ASCII)
//        x += 'A';
//        orig_text+=(char)(x);
//    }
//    return orig_text;
//}
//
//// This function will convert the lower case character to Upper case
//static String LowerToUpper(String s)
//{
//    StringBuffer str =new StringBuffer(s); 
//    for(int i = 0; i < s.length(); i++)
//    {
//        if(Character.isLowerCase(s.charAt(i)))
//        {
//            str.setCharAt(i, Character.toUpperCase(s.charAt(i)));
//        }
//    }
//    s = str.toString();
//    return s;
//}
//
//// Driver code
//public static void main(String[] args) 
//{
//	Scanner sc = new Scanner(System.in); 
//	
//	System.out.println("Enter a string: "); 
//	
//	String str = sc.nextLine();
//    String Keyword = "PCCOE";
//      
//      str = LowerToUpper(str);
//      String keyword = LowerToUpper(Keyword);
//
//    String key = generateKey(str, keyword);
//    String cipher_text = cipherText(str, key);
//
//    System.out.println("Ciphertext : "
//        + cipher_text + "\n");
//
//    System.out.println("Original/Decrypted Text : "
//        + originalText(cipher_text, key));
//    }
//}
//
//// This code has been contributed by 29AjayKumar
