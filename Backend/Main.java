import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class Encrypt{
    public static void main(String[] args) throws Exception {

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your password: ");
        String n = reader.next();

        PasswordENC Pll = new PasswordENC();
        byte[] pass;
        pass = Pll.encryptPass(n);
        byte [] decrypted = Pll.decrypt(pass);

        String st = new String(decrypted, StandardCharsets.UTF_8);
        System.out.println(st);

//    String message="bla bla bla";
//    System.out.println("String to encrypt: " + message);

//    KeyGenerator key = KeyGenerator.getInstance("AES");
//    key.init(256);
//
//    SecretKey s = key.generateKey();
//    byte[] raw = s.getEncoded();

//    String stril = "1qazxsw23edcvfr45tgbnhy67ujm,ki8";
//    byte[] raw = stril.getBytes();
//
//    System.out.println(raw);
//
//    SecretKeySpec skey= new SecretKeySpec(raw, "AES");
//
//    Cipher c = Cipher.getInstance("AES");
//
//    c.init(Cipher.ENCRYPT_MODE, skey);
//
//    byte[] encrypted = c.doFinal(message.getBytes());
//
//    System.out.println("encrypted string: " + asHex(encrypted));
//
//    c.init(Cipher.DECRYPT_MODE, skey);
//
//    byte[] decrypted = c.doFinal(encrypted);
//
//    String st = new String(decrypted,StandardCharsets.UTF_8);
//
//    System.out.println("Decrypted string: " + st);

//      decrypt(encrypted, skey);
    }
}