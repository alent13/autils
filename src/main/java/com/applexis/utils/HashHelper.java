package com.applexis.utils;

import com.applexis.utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashHelper {

    public static byte[] getSHA(byte[] data, String salt) {
        MessageDigest md = null;
        byte[] resault = null;
        try {
            md = MessageDigest.getInstance("SHA");
            md.update(salt.getBytes("UTF-8"));
            resault = md.digest(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resault;
    }

    public static byte[] getSHA512(byte[] data, String salt) {
        MessageDigest md = null;
        byte[] resault = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            resault = md.digest(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resault;
    }

    public static byte[] getSHA512(String data, String salt) {
        byte[] resault = null;
        try {
            resault = getSHA512(data.getBytes("UTF-8"), salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resault;
    }

    public static String getSHA512String(byte[] data, String salt) {
        byte[] resault = null;
        try {
            resault = getSHA512(data, salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.bytesToHex(resault);
    }

    public static String getSHA512String(String data, String salt) {
        byte[] resault = null;
        try {
            resault = getSHA512(data.getBytes("UTF-8"), salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.bytesToHex(resault);
    }

    public static String getMD5String(String data, String salt) {
        byte[] digest = new byte[0];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(salt.getBytes());
            digest = messageDigest.digest(data.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return StringUtils.bytesToHex(digest);
    }

    public static String getMD5String(byte[] data, String salt) {
        byte[] digest = new byte[0];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(salt.getBytes());
            digest = messageDigest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return StringUtils.bytesToHex(digest);
    }

    public static String getFileMD5Hash(File file) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] dataBuffer = new byte[(int) Math.min(file.length(), 4 * 1024 * 1024)];
            int read;

            while((read = fileInputStream.read(dataBuffer)) != -1) {
                messageDigest.update(dataBuffer, 0, read);
            }

            return StringUtils.bytesToHex(messageDigest.digest());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
