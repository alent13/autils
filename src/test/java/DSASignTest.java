import com.applexis.utils.crypto.DSASign;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;

public class DSASignTest {

    @Test
    public void singTest() {
        KeyPair keyPair = DSASign.generateKeyPair();

        String testString = "test";

        byte[] sign = DSASign.generateSignature(keyPair.getPrivate(), testString.getBytes());

        Assert.assertTrue(DSASign.verifySignature(keyPair.getPublic(), testString.getBytes(), sign));
    }

    @Test
    public void keyStringManipulationTest() {
        KeyPair keyPair = DSASign.generateKeyPair();

        Assert.assertEquals(keyPair.getPublic(), DSASign.getPublicKey(DSASign.getPublicKeyString(keyPair.getPublic())));
    }

}
