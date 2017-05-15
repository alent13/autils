package com.applexis.utils;

public class StringUtils {

    private static final int HI_BYTE_MASK = 0xf0;
    private static final int LOW_BYTE_MASK = 0x0f;

    private static final char[] HEX_SYMBOLS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
    };

    public static String bytesToHex(byte[] bytes) {
        final StringBuilder builder = new StringBuilder(2 * bytes.length);
        for (byte item : bytes) {
            builder.append(HEX_SYMBOLS[(HI_BYTE_MASK & item) >>> 4]);
            builder.append(HEX_SYMBOLS[(LOW_BYTE_MASK & item)]);
        }
        return builder.toString();
    }

    public static byte[] fromHexString(String hexString) throws IllegalArgumentException {
        int len = hexString.length();
        if (len % 2 == 0) {
            byte[] data = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                        + Character.digit(hexString.charAt(i + 1), 16));
            }
            return data;
        } else {
            throw new IllegalArgumentException("Argument must be a hex-string!");
        }
    }

    public static String surroundQuotes(String str) {
        return '"' + str + '"';
    }

}
