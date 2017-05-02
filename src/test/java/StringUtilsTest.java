import com.applexis.utils.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void toHexTest() {
        Assert.assertEquals("011D020F030A04FF",
                StringUtils.bytesToHex(new byte[] {0x1, 0x1d, 0x2, 0xf, 0x3, 0xa, 0x4, (byte) 0xff}));
    }

    @Test
    public void hexTest() {
        byte[] testArray = new byte[] {-100, 99, 21, 94, 18, -122, -1, 64};
        Assert.assertArrayEquals(testArray,
                StringUtils.fromHexString(StringUtils.bytesToHex(testArray)));
    }

    @Test
    public void surroundTest() {
        Assert.assertEquals("\"123\"", StringUtils.surroundQuotes("123"));
    }

}
