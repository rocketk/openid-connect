package net.trajano.openidconnect.crypto;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnumValue;

/**
 * <p>
 * This maps the algorithms to their JCA counterparts.
 * </p>
 * See Appendix A & B of
 * http://self-issued.info/docs/draft-ietf-jose-json-web-algorithms-00.html.
 */
public enum JsonWebAlgorithm {
    /**
     * Advanced Encryption Standard (AES) using 128 bit keys in Galois/Counter
     * Mode.
     */
    A128GCM("AES/GCM/NoPadding"),
    /**
     * Advanced Encryption Standard (AES) Key Wrap Algorithm RFC 3394 [RFC3394]
     * using 128 bit keys.
     */
    A128KW(null),
    /**
     * Advanced Encryption Standard (AES) using 256 bit keys in Cipher Block
     * Chaining mode.
     */
    A256CBC("AES/CBC/PKCS5Padding"),
    /**
     * Advanced Encryption Standard (AES) using 256 bit keys in Galois/Counter
     * Mode.
     */
    A256GCM("AES/GCM/NoPadding"),
    /**
     * Advanced Encryption Standard (AES) Key Wrap Algorithm RFC 3394 [RFC3394]
     * using 256 bit keys.
     */
    A256KW(null),
    /**
     * Elliptic Curve Diffie-Hellman Ephemeral Static.
     */
    @XmlEnumValue("ECDH-ES")
    ECDH_ES(null),
    /**
     * ECDSA using P-256 curve and SHA-256 hash algorithm.
     */
    ES256("SHA256withECDSA"),
    /**
     * ECDSA using P-384 curve and SHA-384 hash algorithm.
     */
    ES384("SHA384withECDSA"),

    /**
     * ECDSA using P-521 curve and SHA-512 hash algorithm.
     */
    ES512("SHA512withECDSA"),
    /**
     * HMAC using SHA-256 hash algorithm.
     */
    HS256("HmacSHA256"),
    /**
     * HMAC using SHA-384 hash algorithm.
     */
    HS384("HmacSHA384"),
    /**
     * HMAC using SHA-512 hash algorithm.
     */
    HS512("HmacSHA512"),
    /**
     * Special case where the value is sent unencrypted.
     */
    none(null),
    /**
     * RSA using SHA-256 hash algorithm.
     */
    RS256("SHA256withRSA"),
    /**
     * RSA using SHA-384 hash algorithm.
     */
    RS384("SHA384withRSA"),
    /**
     * RSA using SHA-512 hash algorithm.
     */
    RS512("SHA512withRSA"),
    /**
     * RSA using Optimal Asymmetric Encryption Padding (OAEP).
     */
    @XmlEnumValue("RSA-OAEP")
    RSA_OAEP("RSA/ECB/OAEPWithSHA-1AndMGF1Padding"),
    /**
     * RSA using RSA-PKCS1-1.5 padding.
     */
    RSA1_5("RSA/ECB/PKCS1Padding");

    private static final Map<String, JsonWebAlgorithm> REVERSE = new HashMap<>();

    /**
     * Symettric algorithms with JCA support.
     */
    public static final JsonWebAlgorithm[] SYMETTRIC_WITH_JCA = { A128GCM, A256CBC, A256GCM };

    static {
        for (final JsonWebAlgorithm jwa : EnumSet.allOf(JsonWebAlgorithm.class)) {
            if (jwa.jcaAlgorithm != null) {
                REVERSE.put(jwa.jcaAlgorithm, jwa);
            }
        }
    }
    public static JsonWebAlgorithm fromJca(final String jcaAlgorithm) {

        return REVERSE.get(jcaAlgorithm);
    }

    /**
     * JCA Algorithm.
     */
    private final String jcaAlgorithm;

    /**
     * Assigns a JCA Algorithm to the JWA.
     *
     * @param jcaAlgorithm
     *            JCA algorithm
     */
    private JsonWebAlgorithm(final String jcaAlgorithm) {

        this.jcaAlgorithm = jcaAlgorithm;
    }

    /**
     * Returns the JCA Algorithm Name.
     *
     * @return JCA Algorithm Name.
     */
    public String toJca() {

        return jcaAlgorithm;
    }
}
