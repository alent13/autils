import com.applexis.utils.crypto.AESCrypto;
import com.applexis.utils.crypto.RSACrypto;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;
import java.security.KeyPair;

public class RSACryptoTest {

    @Test
    public void keyStringManipulationTest() {
        KeyPair keyPair = RSACrypto.generateKeyPair();

        Assert.assertEquals(keyPair.getPublic(),
                RSACrypto.getPublicKey(RSACrypto.getPublicKeyString(keyPair.getPublic())));

        Assert.assertEquals(keyPair.getPrivate(),
                RSACrypto.getPrivateKey(RSACrypto.getPrivateKeyString(keyPair.getPrivate())));
    }

    @Test
    public void RSAEncryptDecryptTest() {
        Key key = new AESCrypto().getKey();
        KeyPair keyPair = RSACrypto.generateKeyPair();

        Assert.assertArrayEquals(key.getEncoded(),
                RSACrypto.decrypt(keyPair.getPublic(),
                        RSACrypto.encrypt(keyPair.getPrivate(), key.getEncoded())));
    }

}
