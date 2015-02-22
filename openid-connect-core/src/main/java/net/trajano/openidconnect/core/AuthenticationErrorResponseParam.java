package net.trajano.openidconnect.core;

/**
 * <p>
 * An Authentication Error Response is an OAuth 2.0 Authorization Error Response
 * message returned from the OP's Authorization Endpoint in response to the
 * Authorization Request message sent by the RP.
 * </p>
 * <p>
 * If the End-User denies the request or the End-User authentication fails, the
 * OP (Authorization Server) informs the RP (Client) by using the Error Response
 * parameters defined in Section 4.1.2.1 of OAuth 2.0 [RFC6749]. (HTTP errors
 * unrelated to RFC 6749 are returned to the User Agent using the appropriate
 * HTTP status code.)
 * </p>
 * <p>
 * Unless the Redirection URI is invalid, the Authorization Server returns the
 * Client to the Redirection URI specified in the Authorization Request with the
 * appropriate error and state parameters. Other parameters SHOULD NOT be
 * returned.
 * </p>
 * 
 * @author Archimedes Trajano
 * @see http://openid.net/specs/openid-connect-core-1_0.html#AuthError
 */
public final class AuthenticationErrorResponseParam {

    private AuthenticationErrorResponseParam() {

    }

    public enum ErrorCode {
        /**
         * The client is not authorized to request an authorization code using
         * this method.
         * 
         * @see http://tools.ietf.org/html/rfc6749#section-4.1.2.1
         */
        unauthorized_client

        , /**
         * The resource owner or authorization server denied the request.
         * 
         * @see http://tools.ietf.org/html/rfc6749#section-4.1.2.1
         */
        access_denied

        , /**
         * The authorization server does not support obtaining an
         * authorization code using this method.
         * 
         * @see http://tools.ietf.org/html/rfc6749#section-4.1.2.1
         */
        unsupported_response_type

        , /**
         * The requested scope is invalid, unknown, or malformed.
         * 
         * @see http://tools.ietf.org/html/rfc6749#section-4.1.2.1
         */
        invalid_scope

        ,
        /**
         * The authorization server encountered an unexpected condition that
         * prevented it from fulfilling the request. (This error code is needed
         * because a 500 Internal Server Error HTTP status code cannot be
         * returned to the client via an HTTP redirect.)
         * 
         * @see http://tools.ietf.org/html/rfc6749#section-4.1.2.1
         */
        server_error,
        /**
         * The authorization server is currently unable to handle the request
         * due to a temporary overloading or maintenance of the server. (This
         * error code is needed because a 503 Service Unavailable HTTP status
         * code cannot be returned to the client via an HTTP redirect.)
         * 
         * @see http://tools.ietf.org/html/rfc6749#section-4.1.2.1
         */
        temporarily_unavailable

        , /**
         * The Authorization Server requires End-User interaction of some form
         * to proceed. This error MAY be returned when the prompt parameter
         * value in the Authentication Request is none, but the Authentication
         * Request cannot be completed without displaying a user interface for
         * End-User interaction.
         */
        interaction_required, /**
         * The Authorization Server requires End-User
         * authentication. This error MAY be returned when the prompt parameter
         * value in the Authentication Request is none, but the Authentication
         * Request cannot be completed without displaying a user interface for
         * End-User authentication.
         */
        login_required, /**
         * The End-User is REQUIRED to select a session at the
         * Authorization Server. The End-User MAY be authenticated at the
         * Authorization Server with different associated accounts, but the
         * End-User did not select a session. This error MAY be returned when
         * the prompt parameter value in the Authentication Request is none, but
         * the Authentication Request cannot be completed without displaying a
         * user interface to prompt for a session to use.
         */
        account_selection_required, /**
         * The Authorization Server requires
         * End-User consent. This error MAY be returned when the prompt
         * parameter value in the Authentication Request is none, but the
         * Authentication Request cannot be completed without displaying a user
         * interface for End-User consent.
         */
        consent_required, /**
         * The request_uri in the Authorization Request
         * returns an error or contains invalid data.
         */
        invalid_request_uri, /**
         * The request parameter contains an invalid
         * Request Object.
         */
        invalid_request_object, /**
         * The OP does not support use of the request
         * parameter defined in Section 6.
         */
        request_not_supported, /**
         * The OP does not support use of the request_uri
         * parameter defined in Section 6.
         */
        request_uri_not_supported, /**
         * The OP does not support use of the
         * registration parameter defined in Section 7.2.1.
         */
        registration_not_supported

    }

    /**
     * REQUIRED. Error code.
     */
    public final static String ERROR = "error";

    /**
     * OPTIONAL. Human-readable ASCII encoded text description of the error.
     */
    public final static String ERROR_DESCRIPTION = "error_description";

    /**
     * OPTIONAL. URI of a web page that includes additional information about
     * the error.
     */
    public final static String ERROR_URI = "error_uri";

    /**
     * OAuth 2.0 state value. REQUIRED if the Authorization Request included the
     * state parameter. Set to the value received from the Client.
     */
    public final static String STATE = "state";

}
