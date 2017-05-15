import com.applexis.utils.crypto.AESCrypto;
import org.junit.Assert;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.security.Key;

public class AESCryptoTest {

    @Test
    public void keyStringLengthTest() {
        AESCrypto aes = new AESCrypto(192);
        Assert.assertEquals(aes.KEY_SIZE / 8 * 2, aes.getKeyString().length());

        aes = new AESCrypto();
        Assert.assertEquals(aes.KEY_SIZE / 8 * 2, aes.getKeyString().length());
    }

    @Test
    public void keyStringManipulationsTest() {
        AESCrypto aes = new AESCrypto();

        Key startKey = aes.getKey();

        String keyString = aes.getKeyString();
        Key keyFromString = aes.keyFromString(keyString).getKey();

        Assert.assertArrayEquals(startKey.getEncoded(), keyFromString.getEncoded());
    }

    @Test
    public void encryptDecryptTest() {
        AESCrypto aes = new AESCrypto();

        String testString = "qwerty_asd";

        Assert.assertEquals(testString, aes.decrypt(aes.encrypt(testString)));
    }

    @Test(expected = InvalidParameterException.class)
    public void wrongKeyLengthExceptionTest() {
        new AESCrypto(193);
    }

}
