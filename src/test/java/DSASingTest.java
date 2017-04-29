import com.applexis.utils.crypto.DSASing;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;

public class DSASingTest {

    @Test
    public void singTest() {
        KeyPair keyPair = DSASing.generateKeyPair();

        String testString = "test";

        byte[] sign = DSASing.generateSignature(keyPair.getPrivate(), testString.getBytes());

        Assert.assertTrue(DSASing.verifySignature(keyPair.getPublic(), testString.getBytes(), sign));
    }

    @Test
    public void keyStringManipulationTest() {
        KeyPair keyPair = DSASing.generateKeyPair();

        Assert.assertEquals(keyPair.getPublic(), DSASing.getPublicKey(DSASing.getPublicKeyString(keyPair.getPublic())));
    }

}
