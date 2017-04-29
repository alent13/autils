package com.applexis.crypto;

import com.applexis.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/*
 * Class for easy AES encryption/decryption
 *
 * Java Cryptography Extension need to be installed
 * http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html
*/
public class AESCrypto {

    public static final String AES_ALGORITHM = "AES";

    private Key key;

    public int KEY_SIZE = 256;

    /*
     * Constructor with auto-generate 256-length AES key
     */
    public AESCrypto() {
        generateKey();
    }

    /*
     * Constructor with custom key size
     * @param keySize EAS key size, must be equal to 128, 192 or 256
     */
    public AESCrypto(int keySize) {
        KEY_SIZE = keySize;
        generateKey();
    }

    public AESCrypto(String keyString) {
        keyFromString(keyString);
    }

    public AESCrypto(Key key) {
        this.key = key;
    }

    public AESCrypto generateKey() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(AES_ALGORITHM);
            generator.init(KEY_SIZE, new SecureRandom());
            key = generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return this;
    }

    public AESCrypto keyFromString(String keyString) {
        key = new SecretKeySpec(StringUtils.fromHexString(keyString), AES_ALGORITHM);
        return this;
    }

    public String getKeyString() {
        return StringUtils.bytesToHex(key.getEncoded());
    }

    public String encrypt(String message) {
        String str = null;
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] stringBytes = message.getBytes("UTF8");
            byte[] raw = cipher.doFinal(stringBytes);
            str = StringUtils.bytesToHex(raw);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String decrypt(String encrypted) {
        String str = null;
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] raw = StringUtils.fromHexString(encrypted);
            byte[] stringBytes = cipher.doFinal(raw);
            str = new String(stringBytes, "UTF8");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String encrypt(byte[] bytes) {
        String str = null;
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] raw = cipher.doFinal(bytes);
            str = StringUtils.bytesToHex(raw);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String decrypt(byte[] encrypted) {
        String str = null;
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] stringBytes = cipher.doFinal(encrypted);
            str = new String(stringBytes, "UTF8");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return str;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
