package com.applexis.utils.crypto;

import com.applexis.utils.StringUtils;
import sun.rmi.server.InactiveGroupException;

import java.io.UnsupportedEncodingException;
import java.security.*;

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
    public AESCrypto(int keySize) throws InvalidParameterException {
        if (keySize != 128 && keySize != 192 && keySize != 256) {
            throw new InvalidParameterException("Key size, must be equal to 128, 192 or 256");
        }
        KEY_SIZE = keySize;
        generateKey();
    }

    public AESCrypto(String keyString) {
        keyFromString(keyString);
    }

    public AESCrypto(Key key) {
        this.key = key;
    }

    public AESCrypto(byte[] keyBytes) {
        key = new SecretKeySpec(keyBytes, AES_ALGORITHM);
    }

    public AESCrypto generateKey() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(AES_ALGORITHM);
            generator.init(KEY_SIZE, new SecureRandom());
            key = generator.generateKey();
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public byte[] encrypt(byte[] bytes) {
        byte[] res = null;
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            res = cipher.doFinal(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public byte[] decrypt(byte[] encrypted) {
        byte[] res = null;
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            res = cipher.doFinal(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
