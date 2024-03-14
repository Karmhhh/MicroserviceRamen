package com.example.MicroserviceRamen.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.util.Base64;

public class EncriptService {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "MySecretKey12345"; // Chiave segreta per la crittografia

    public static String encrypt(String plainText) {
        try {
            if (plainText == null || plainText.isEmpty()) {
                throw new IllegalArgumentException("Il testo da crittografare non può essere vuoto o nullo.");
            }

            if (SECRET_KEY.length() != 16 && SECRET_KEY.length() != 24 && SECRET_KEY.length() != 32) {
                throw new IllegalArgumentException("La lunghezza della chiave segreta deve essere 16, 24 o 32 byte per AES.");
            }

            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String encryptedText) {
        try {
            if (encryptedText == null || encryptedText.isEmpty()) {
                throw new IllegalArgumentException("Il testo crittografato non può essere vuoto o nullo.");
            }

            if (SECRET_KEY.length() != 16 && SECRET_KEY.length() != 24 && SECRET_KEY.length() != 32) {
                throw new IllegalArgumentException("La lunghezza della chiave segreta deve essere 16, 24 o 32 byte per AES.");
            }

            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
