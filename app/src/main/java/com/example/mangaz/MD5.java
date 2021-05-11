/*
 * Coder: Nguyen Van An
 * Date: 6-5-2021
 * Content: Mã hóa mật khẩu 1 chiều (MD5)
 *
 *
 *
 * */
package com.example.mangaz;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String Encrypt(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MANGAZ");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
