package com.sendit;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PBKDF2Hashing {

    public static String hashPassword(String password, UsersTable usersTable) throws Exception {
        String hashedPass = null;
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = new byte[16];
            secureRandom.nextBytes(salt);

            String saltToString = Base64.getEncoder().encodeToString(salt);
            usersTable.setSalt(saltToString);

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            hashedPass = Base64.getEncoder().encodeToString(hash);
            return hashedPass;
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        hashedPass = "Password wasn't hashed";
        return hashedPass;
    }


    public static boolean verifyPassword(String inputPassword, String storedHash, byte[] salt, int iterationCount, int keyLength) throws Exception {
        KeySpec spec = new PBEKeySpec(inputPassword.toCharArray(), salt, iterationCount, keyLength);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        String newHash = Base64.getEncoder().encodeToString(hash);

        return newHash.equals(storedHash);
    }
}

