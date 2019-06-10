/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/

package com.bookify.jpa.api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Can generate salt and hash for passwords.
 */

public class Authorization {


    public static String byte2Hex(byte[] input) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : input) {
            hexString.append(Integer.toHexString(0xFF & b));
        }
        return hexString.toString();
    }


    public static String generateRandomSaltString(int targetLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetLength);
        for(int i = 0; i < targetLength; i++) {
            int randomLimitedInt = leftLimit
                    + (int)(random.nextFloat()
                    * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private static String generateHashString(String salt, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String saltAndPassword = salt + password;
            md.update(saltAndPassword.getBytes());
            byte[] digest = md.digest();
            return byte2Hex(digest);
        }
        catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    public String generator (String pass, String salt){
        String h = generateHashString(salt, pass);
        return h;
    }
}
