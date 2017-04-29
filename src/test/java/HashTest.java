import com.applexis.utils.StringUtils;
import com.applexis.utils.crypto.HashHelper;
import org.junit.Assert;
import org.junit.Test;

public class HashTest {

    @Test
    public void SHA2Test() {
        String shaString = "263FEC58861449AACC1C328A4AFF64AFF4C62DF4A2D50B3F2" +
                "07FA89B6E242C9AA778E7A8BAEFFEF85B6CA6D2E7DC16FF0A760D59C13C2" +
                "38F6BCDC32F8CE9CC62";

        Assert.assertEquals(shaString,
                HashHelper.getSHA512String("123", "123"));
    }

    @Test
    public void SHATest() {
        String shaString = "601F1889667EFAEBB33B8C12572835DA3F027F78";

        Assert.assertArrayEquals(StringUtils.fromHexString(shaString),
                HashHelper.getSHA("123".getBytes(), "123"));
    }

    @Test
    public void MD5Test() {
        String md5String = "4297F44B13955235245B2497399D7A93";

        Assert.assertEquals(md5String,
                HashHelper.getMD5String("123", "123"));
    }

}
