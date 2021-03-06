package net.trajano.openidconnect.token;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import net.trajano.openidconnect.core.ErrorCode;
import net.trajano.openidconnect.core.OpenIdConnectException;
import net.trajano.openidconnect.crypto.JsonWebKeySet;
import net.trajano.openidconnect.crypto.JsonWebTokenProcessor;
import net.trajano.openidconnect.rs.IdTokenProvider;

/**
 * <p>
 * <a href="http://openid.net/specs/openid-connect-core-1_0.html#TokenResponse">
 * Access Token Response</a>.
 * </p>
 * <p>
 * After receiving and validating a valid and authorized Token Request from the
 * Client, the Authorization Server returns a successful response that includes
 * an ID Token and an Access Token. The parameters in the successful response
 * are defined in Section 4.1.4 of OAuth 2.0 [RFC6749]. The response uses the
 * application/json media type.
 * </p>
 * <p>
 * The OAuth 2.0 token_type response parameter value MUST be Bearer, as
 * specified in OAuth 2.0 Bearer Token Usage [RFC6750], unless another Token
 * Type has been negotiated with the Client. Servers SHOULD support the Bearer
 * Token Type; use of other Token Types is outside the scope of this
 * specification.
 * </p>
 * <p>
 * All Token Responses that contain tokens, secrets, or other sensitive
 * information MUST include the following HTTP response header fields and
 * values:
 * <table>
 * <tr>
 * <th>Header Name</th>
 * <th>Header Value</th>
 * </tr>
 * <tr>
 * <td>Cache-Control</td>
 * <td>no-store</td>
 * </tr>
 * <tr>
 * <td>Pragma</td>
 * <td>no-cache</td>
 * </tr>
 * </table>
 *
 * @author Archimedes Trajano
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class IdTokenResponse extends TokenResponse {

    /**
     *
     */
    private static final long serialVersionUID = -6175007590579157079L;

    /**
     * Encoded ID Token value associated with the authenticated session.
     */
    @XmlElement(name = "id_token",
        required = true)
    private String encodedIdToken;

    /**
     * Flag to indicate that the token was retrieved using a used up
     * authentication code.
     */

    @XmlTransient
    private boolean usedUpAuthenticationCode;

    /**
     * Constructs IdTokenResponse.
     */
    public IdTokenResponse() {
    }

    /**
     * Constructs IdTokenResponse from a JSON Object.
     *
     * @param tokenResponse
     *            token response JSON
     */
    public IdTokenResponse(final JsonObject tokenResponse) throws GeneralSecurityException {
        super(tokenResponse);
        setEncodedIdToken(tokenResponse.getString("id_token"));
    }

    public String getEncodedIdToken() {

        return encodedIdToken;
    }

    /**
     * Gets the ID Token with signature validation.
     *
     * @param jwks
     *            JSON web key set
     * @return ID token
     */
    public IdToken getIdToken(final JsonWebKeySet jwks) {

        try {
            final JsonWebTokenProcessor p = new JsonWebTokenProcessor(encodedIdToken).jwks(jwks);
            if (!p.isJwkAvailable()) {
                throw new OpenIdConnectException(ErrorCode.invalid_request, "no jwk available for kid");
            }
            return new IdTokenProvider().readFrom(IdToken.class, null, null, MediaType.APPLICATION_JSON_TYPE, null, new ByteArrayInputStream(p.getPayload()));
        } catch (IOException
            | GeneralSecurityException e) {
            throw new WebApplicationException(e);
        }

    }

    public boolean isUsedUpAuthenticationCode() {

        return usedUpAuthenticationCode;
    }

    public void setEncodedIdToken(final String encodedIdToken) throws GeneralSecurityException {

        this.encodedIdToken = encodedIdToken;
    }

    public void setUsedUpAuthenticationCode(final boolean usedUpAuthenticationCode) {

        this.usedUpAuthenticationCode = usedUpAuthenticationCode;
    }

}
