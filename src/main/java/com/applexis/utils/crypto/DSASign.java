package com.applexis.utils.crypto;

import com.applexis.utils.StringUtils;

import java.security.*;
import java.security.spec.X509EncodedKeySpec;

public class DSASign {

    public static final String DSA_ALGORITHM = "DSA";

    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(DSA_ALGORITHM);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

            keyGen.initialize(1024, random);

            return keyGen.generateKeyPair();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] generateSignature(PrivateKey privateKey, byte[] info) {
        try {
            Signature dsa = Signature.getInstance("SHA1withDSA");
            dsa.initSign(privateKey);
            dsa.update(info);
            return dsa.sign();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean verifySignature(PublicKey publicKey, byte[] data, byte[] signature) {
        try {
            Signature dsa = Signature.getInstance("SHA1withDSA");
            dsa.initVerify(publicKey);
            dsa.update(data);
            return dsa.verify(signature);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static String getPublicKeyString(PublicKey publicKey) {
        KeyFactory kf = null;
        String str = null;
        try {
            kf = KeyFactory.getInstance(DSA_ALGORITHM);
            str = StringUtils.bytesToHex(kf.getKeySpec(publicKey, X509EncodedKeySpec.class).getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static PublicKey getPublicKey(String keyString) {
        PublicKey publicKey = null;
        try {
            byte[] byteKey = StringUtils.fromHexString(keyString);
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance(DSA_ALGORITHM);
            publicKey = kf.generatePublic(X509publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

}
