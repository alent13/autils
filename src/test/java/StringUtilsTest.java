import com.applexis.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void toHexTest() {
        Assert.assertEquals("011d020f030a04ff",
                StringUtils.bytesToHex(new byte[] {0x1, 0x1d, 0x2, 0xf, 0x3, 0xa, 0x4, (byte) 0xff}));
    }

    @Test
    public void HexTest() {
        byte[] testArray = new byte[] {-100, 99, 21, 94, 18, -122, -1, 64};
        Assert.assertArrayEquals(testArray,
                StringUtils.fromHexString(StringUtils.bytesToHex(testArray)));
    }

}
