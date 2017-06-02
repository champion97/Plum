package org.java10.plum.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Tate on 2017/5/3.
 */
public class EncodeUtil {


    public static String string2MD5(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputBytes = input.getBytes();
            messageDigest.update(inputBytes);
            byte[] resultBytes = messageDigest.digest();
            return bytes2hex(resultBytes);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String bytes2hex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte aByte : bytes) {
            String tmp = Integer.toHexString(aByte & 0XFF);
            if (tmp.length() == 1) {
                hex.append("0");
            }
            hex.append(tmp);
        }
        return hex.toString();
    }
}
