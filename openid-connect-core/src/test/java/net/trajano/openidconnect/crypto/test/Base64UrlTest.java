package net.trajano.openidconnect.crypto.test;

import java.math.BigInteger;

import net.trajano.openidconnect.crypto.Encoding;

import org.junit.Assert;
import org.junit.Test;

public class Base64UrlTest {

    /**
     * <p>
     * Based on <a href=
     * "http://self-issued.info/docs/draft-ietf-jose-json-web-algorithms-35.html#eRSADef"
     * >definiton of e</a>.
     * </p>
     * <blockquote> For instance, when representing the value 65537, the octet
     * sequence to be base64url encoded MUST consist of the three octets [1, 0,
     * 1]; the resulting representation for this value is "AQAB". </blockquote>
     */
    @Test
    public void test65537() {

        Assert.assertEquals(65537, Encoding.base64urlDecodeUint("AQAB")
                .intValue());

        Assert.assertEquals("AQAB", Encoding.base64EncodeUint(new BigInteger("65537")));

    }

    /**
     * Tests the zero value as per <a href=
     * "http://self-issued.info/docs/draft-ietf-jose-json-web-algorithms-35.html#Terminology"
     * >Base64urlUInt terminology</a>.
     */
    @Test
    public void testZeroDecode() {

        Assert.assertEquals(BigInteger.ZERO, Encoding.base64urlDecodeUint("AA"));

    }

    @Test
    public void testZeroEncode() {

        Assert.assertEquals("AA", Encoding.base64EncodeUint(BigInteger.ZERO));
    }
}
