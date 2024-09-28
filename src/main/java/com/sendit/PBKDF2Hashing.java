package com.sendit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PBKDF2Hashing {

    static final Logger logger = LoggerFactory.getLogger(PBKDF2Hashing.class);
    public static final Integer iterationCount = 65536;
    public static final Integer keyLenght = 256;


    public static String hashPassword(String password, UsersTable usersTable) {
        String hashedPass = null;
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = new byte[16];
            secureRandom.nextBytes(salt);

            usersTable.setSalt(salt);

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLenght);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            hashedPass = Base64.getEncoder().encodeToString(hash);
            logger.info("Password was hashed.");
            return hashedPass;
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        hashedPass = "Password wasn't hashed";
        logger.info("Password wasn't hashed.");
        return hashedPass;
    }


    public static boolean verifyPassword(String inputPassword, String storedHash, byte[] salt, int iterationCount, int keyLength) throws Exception {
        KeySpec spec = new PBEKeySpec(inputPassword.toCharArray(), salt, iterationCount, keyLength);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        String newHash = Base64.getEncoder().encodeToString(hash);
        logger.info("IS PASSWORD VALID? {}", newHash.equals(storedHash));
        return newHash.equals(storedHash);
    }
}

