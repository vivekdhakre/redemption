package com.marketeer.redemption.mvc.security.util;

import com.marketeer.redemption.util.StackTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.util.StringTokenizer;

/**
 * Created by vivek on 12/4/17.
 */
public class EncryptionUtil {

    public static Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);

    private static final String KEY_STRING = "197-195-249-98-299-59-101-233";


    public static String encrypt(String source) {
        try {
            // Get our secret key
            Key key = getKey();

            // Create the cipher
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Initialize the cipher for encryption
            desCipher.init(Cipher.ENCRYPT_MODE, key);

            // Our cleartext as bytes
            byte[] cleartext = source.getBytes();

            // Encrypt the cleartext
            byte[] ciphertext = desCipher.doFinal(cleartext);

            // Return a String representation of the cipher text
            return byteArrayToHexString(ciphertext);

        } catch (Exception e) {
            logger.error(StackTrace.getRootCause(e,EncryptionUtil.class.getName()));
        }
        return null;
    }


    private static String generateKey() {
        try {
            //created with a key
            KeyGenerator keygen = KeyGenerator.getInstance("DES");
            SecretKey desKey = keygen.generateKey();
            byte[] bytes = desKey.getEncoded();

            return getString(bytes);
        } catch (Exception e) {
            logger.error(StackTrace.getRootCause(e,EncryptionUtil.class.getName()));
            return null;
        }
    }


    public static String decrypt(String source) {
        try {
            // Get our secret key
            Key key = getKey();

            // Create the cipher
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Encrypt the cleartext
            //byte[] ciphertext = getBytes(source);
            byte[] ciphertext = hexStringToByteArray(source);
            // Initialize the same cipher for decryption
            desCipher.init(Cipher.DECRYPT_MODE, key);

            // Decrypt the ciphertext
            byte[] cleartext = desCipher.doFinal(ciphertext);

            // Return the clear text
            return new String(cleartext);

        } catch (Exception e) {
            logger.error(StackTrace.getRootCause(e,EncryptionUtil.class.getName()));
        }
        return null;
    }


    private static Key getKey() {
        try {
            byte[] bytes = getBytes(KEY_STRING);
            DESKeySpec pass = new DESKeySpec(bytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey s = skf.generateSecret(pass);
            return s;
        } catch (Exception e) {
            logger.error(StackTrace.getRootCause(e,EncryptionUtil.class.getName()));
        }
        return null;
    }


    private static String getString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            sb.append((int) (0x00FF & b));
            if (i + 1 < bytes.length) {
                sb.append("-");
            }
        }
        return sb.toString();
    }


    private static byte[] getBytes(String str) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        StringTokenizer st = new StringTokenizer(str, "-", false);
        while (st.hasMoreTokens()) {
            int i = Integer.parseInt(st.nextToken());
            bos.write((byte) i);
        }
        return bos.toByteArray();
    }

    private static String byteArrayToHexString(byte abyte[]) {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("");
        for (int i = 0; i < abyte.length; i++) {
            int j = abyte[i] & 0xff;
            if (j < 16) {
                stringbuffer.append("0");
                stringbuffer.append(Integer.toHexString(j).toUpperCase());
            } else {
                stringbuffer.append(Integer.toHexString(j).toUpperCase());
            }
        }
        return stringbuffer.toString();
    }

    private static byte[] hexStringToByteArray(String s) {
        byte abyte[] = new byte[s.length() / 2];
        int i = 0;
        for (int j = 0; j < abyte.length; j++) {
            String s1 = s.substring(i, i + 2);
            if (s1.equals("FF")) {
                abyte[j] = -1;
            } else {
                abyte[j] = (byte) Integer.parseInt(s1, 16);
            }
            i += 2;
        }
        return abyte;
    }

    public static void main(String[] args) {

        System.out.println(encrypt("welcome"));

        System.out.println(decrypt("6318C779D1C98572"));
    }

}
