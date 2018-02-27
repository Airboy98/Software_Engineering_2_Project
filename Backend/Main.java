import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

class Encrypt{
    public static void main(String[] args) throws Exception {

//        passQL pass = new passQL();
//        //pass.AddUser("jimmy", "badname", "user");  // [B@6276ae34
//
//        boolean check = pass.CheckPass("jimmy","badname");
//
//        System.out.println(check);



        //store as 4 longs in sql
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your password: ");
        String n = reader.next();

        PasswordENC Pll = new PasswordENC();
        byte[] pass;
        pass = Pll.encryptPass(n);
        System.out.println(pass);

        char[] ch = bytesToChars(pass);
        System.out.println(Arrays.toString(ch));
        System.out.println();
        String passs = ch.toString();
        System.out.println(passs);
        //sql

        char[] chs = passs.toCharArray();
        System.out.println(chs);
        byte[] fromchar = charsToBytes(chs);
        System.out.println(fromchar);
        byte [] decrypted = Pll.decrypt(fromchar);

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
    public static String asHex (byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)
                strbuf.append("0");
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

    public static byte[] charsToBytes(char[] chars){
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(CharBuffer.wrap(chars));
        return Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
    }

    public static char[] bytesToChars(byte[] bytes){
        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(ByteBuffer.wrap(bytes));
        return Arrays.copyOf(charBuffer.array(), charBuffer.limit());
    }
}