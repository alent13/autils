package com.applexis.utils.crypto;

import com.applexis.utils.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSACrypto {

    public static final String RSA_ALGORITHM = "RSA";

    public static KeyPair generateKeyPair() {
        return generateKeyPair(1024);
    }

    public static KeyPair generateKeyPair(int keySize) {
        KeyPair pair = null;
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            generator.initialize(keySize);
            pair = generator.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return pair;
    }

    public static byte[] encrypt(PrivateKey key, byte[] bytes) {
        byte[] encrypted = null;
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = cipher.doFinal(bytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return encrypted;
    }

    public static byte[] decrypt(PublicKey key, byte[] bytes) {
        byte[] decrypted = null;
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypted = cipher.doFinal(bytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return decrypted;
    }

    public static PublicKey getPublicKey(String key) {
        PublicKey publicKey = null;
        try {
            byte[] byteKey = StringUtils.fromHexString(key);
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance(RSA_ALGORITHM);
            publicKey = kf.generatePublic(X509publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String key) {
        PrivateKey privateKey = null;
        try {
            byte[] byteKey = StringUtils.fromHexString(key);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return privateKey;
    }

    public static String getPublicKeyString(PublicKey key) {
        String str = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            str = StringUtils.bytesToHex(keyFactory.getKeySpec(key, X509EncodedKeySpec.class).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getPrivateKeyString(PrivateKey key) {
        String str = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            str = StringUtils.bytesToHex(keyFactory.getKeySpec(key, PKCS8EncodedKeySpec.class).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return str;
    }

}
