package dang.gun.com;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES256Cipher {
    private static volatile  AES256Cipher INSTANCE;

    final static String secretKey ="shplab123456789abcdefghijklmnopq";
    static String IV = "";

    public static AES256Cipher getInstance() {
        if(INSTANCE == null) {
            synchronized (AES256Cipher.class) {
                if(INSTANCE == null) {
                    INSTANCE = new AES256Cipher();
                }
            }
        }
        return INSTANCE;
    }

    public AES256Cipher() {
        IV = secretKey.substring(0, 16);
    }

    //암호화
    public static String AES_Encode(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException , NoSuchPaddingException ,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        byte[] keyData = secretKey.getBytes();
        SecretKey secretKey = new SecretKeySpec(keyData , "AES");
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secretKey , new IvParameterSpec(IV.getBytes()));

        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));

        return enStr;
    }

    //복호화
    public static String AES_Decode(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException , NoSuchPaddingException ,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        byte[] keyData = secretKey.getBytes();
        SecretKey secretKey = new SecretKeySpec(keyData, "AES");
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(IV.getBytes("UTF-8")));

        byte[] byteStr = Base64.decodeBase64(str.getBytes());

        return new String(c.doFinal(byteStr), "UTF-8");
    }

}
