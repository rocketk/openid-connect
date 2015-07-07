package net.trajano.openidconnect.token;

/**
 * <p>
 * <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#IDTokenValidation">ID
 * Token validation</a>.
 * <p>
 * Clients MUST validate the ID Token in the Token Response in the following
 * manner:
 * </p>
 * <ol>
 * <li>If the ID Token is encrypted, decrypt it using the keys and algorithms
 * that the Client specified during Registration that the OP was to use to
 * encrypt the ID Token. If encryption was negotiated with the OP at
 * Registration time and the ID Token is not encrypted, the RP SHOULD reject it.
 * </li>
 * <li>The Issuer Identifier for the OpenID Provider (which is typically
 * obtained during Discovery) MUST exactly match the value of the iss (issuer)
 * Claim.</li>
 * <li>The Client MUST validate that the aud (audience) Claim contains its
 * client_id value registered at the Issuer identified by the iss (issuer) Claim
 * as an audience. The aud (audience) Claim MAY contain an array with more than
 * one element. The ID Token MUST be rejected if the ID Token does not list the
 * Client as a valid audience, or if it contains additional audiences not
 * trusted by the Client.</li>
 * <li>If the ID Token contains multiple audiences, the Client SHOULD verify
 * that an azp Claim is present.</li>
 * <li>If an azp (authorized party) Claim is present, the Client SHOULD verify
 * that its client_id is the Claim Value.</li>
 * <li>If the ID Token is received via direct communication between the Client
 * and the Token Endpoint (which it is in this flow), the TLS server validation
 * MAY be used to validate the issuer in place of checking the token signature.
 * The Client MUST validate the signature of all other ID Tokens according to
 * JWS [JWS] using the algorithm specified in the JWT alg Header Parameter. The
 * Client MUST use the keys provided by the Issuer.</li>
 * <li>The alg value SHOULD be the default of RS256 or the algorithm sent by the
 * Client in the id_token_signed_response_alg parameter during Registration.
 * </li>
 * <li>If the JWT alg Header Parameter uses a MAC based algorithm such as HS256,
 * HS384, or HS512, the octets of the UTF-8 representation of the client_secret
 * corresponding to the client_id contained in the aud (audience) Claim are used
 * as the key to validate the signature. For MAC based algorithms, the behavior
 * is unspecified if the aud is multi-valued or if an azp value is present that
 * is different than the aud value.</li>
 * <li>The current time MUST be before the time represented by the exp Claim.
 * </li>
 * <li>The iat Claim can be used to reject tokens that were issued too far away
 * from the current time, limiting the amount of time that nonces need to be
 * stored to prevent attacks. The acceptable range is Client specific.</li>
 * <li>If a nonce value was sent in the Authentication Request, a nonce Claim
 * MUST be present and its value checked to verify that it is the same value as
 * the one that was sent in the Authentication Request. The Client SHOULD check
 * the nonce value for replay attacks. The precise method for detecting replay
 * attacks is Client specific.</li>
 * <li>If the acr Claim was requested, the Client SHOULD check that the asserted
 * Claim Value is appropriate. The meaning and processing of acr Claim Values is
 * out of scope for this specification.</li>
 * <li>If the auth_time Claim was requested, either through a specific request
 * for this Claim or by using the max_age parameter, the Client SHOULD check the
 * auth_time Claim value and request re-authentication if it determines too much
 * time has elapsed since the last End-User authentication.</li>
 * </ol>
 * 
 * @author Archimedes
 */
public class IdTokenValidator {

}
