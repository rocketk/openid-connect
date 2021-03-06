package net.trajano.openidconnect.provider.spi;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriBuilder;

import net.trajano.openidconnect.auth.AuthenticationRequest;
import net.trajano.openidconnect.token.IdToken;

public interface Authenticator {

    /**
     * <p>
     * Obtains the URI to the start of the authentication process. This must be
     * the full URL including the necessary query parameters.
     * </p>
     * <p>
     * The simplest way of doing this is to pass the "request" parameter that
     * encodes the original authentication request.
     * </p>
     *
     * <pre>
     * return contextUriBuilder.path(&quot;login.jsp&quot;)
     *         .queryParam(OpenIdConnectKey.REQUEST, requestJwt)
     *         .build();
     * </pre>
     * <p>
     * Depending on some values on the authentication request such as "display"
     * or "ui-locale" a different URI can be provided.
     * </p>
     *
     * @param authenticationRequest
     *            authentication request
     * @param requestJwt
     *            the request encoded in a JWT
     * @param req
     *            servlet request
     * @param contextUriBuilder
     *            {@link UriBuilder} pointing to the context. The rest can be
     *            added on from there.
     * @return
     * @throws IOException
     * @throws ServletException
     */
    URI authenticate(AuthenticationRequest authenticationRequest,
            String requestJwt,
            HttpServletRequest req,
            UriBuilder contextUriBuilder);

    URI consent(AuthenticationRequest authenticationRequest,
            String requestJwt,
            HttpServletRequest req,
            UriBuilder contextUriBuilder);

    /**
     * Obtains the subject for the current user as authenticated by the OP. May
     * return <code>null</code> if the subject cannot be determined.
     *
     * @param req
     *            servlet request
     * @return
     */
    String getSubject(HttpServletRequest req);

    /**
     * Checks if the user is authenticated.</p>
     * <p>
     * If there is no UI application state for the OP aside, this will return
     * <code>false</code> to force the user to enter their credentials when
     * accessing the provider.
     * </p>
     *
     * @param authenticationRequest
     *            authentication request
     * @param req
     *            servlet request
     * @return <code>true</code> if the user is authenticated.
     * @throws IOException
     * @throws ServletException
     */
    boolean isAuthenticated(HttpServletRequest req);

    /**
     * The resulting URI MUST NOT have any of the data provided as they should
     * already be in the session with the exception of the "nonce" value. It
     * should call "end/confirm" with the "nonce" value that was passed in.
     * 
     * @param nonce
     *            nonce that is generated by the OP and must match when
     *            confirming the logout.
     * @param idToken
     * @param state
     * @param postLogoutRedirectUri
     * @param req
     * @param contextUriBuilder
     *            context URI builder.
     * @return
     */
    URI logout(String nonce,
            IdToken idToken,
            String state,
            URI postLogoutRedirectUri,
            HttpServletRequest req,
            UriBuilder contextUriBuilder);

    /**
     * Performs any session cleanup. Please note that this MUST NOT call
     * {@link HttpSession#invalidate()} as that is implicitly invoked.
     * 
     * @param req
     */
    void endSession(HttpServletRequest req);

}
