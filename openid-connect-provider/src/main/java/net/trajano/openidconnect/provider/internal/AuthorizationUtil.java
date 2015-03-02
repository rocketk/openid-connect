package net.trajano.openidconnect.provider.internal;

import javax.servlet.http.HttpServletRequest;

import net.trajano.openidconnect.core.InvalidClientException;
import net.trajano.openidconnect.core.SslRequiredException;
import net.trajano.openidconnect.crypto.Base64Url;

/**
 * Helper class to manage the Authorization tag
 *
 * @author Archimedes
 */
public final class AuthorizationUtil {

    private static final String BASIC_AUTHORIZATION = "Basic";

    private static final String BEARER_AUTHORIZATION = "Bearer";

    private static String getDecodedValue(final HttpServletRequest request,
            final String type) {

        final String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            throw new InvalidClientException(type);
        }
        final String[] authorizationComponents = authorization.split("\\s+");
        if (authorizationComponents.length != 2) {
            throw new InvalidClientException(type);
        }

        if (!type.equals(authorizationComponents[0])) {
            throw new InvalidClientException(type);
        }

        return Base64Url.decodeToString(authorizationComponents[1]);

    }

    public static ClientCredentials processBasicOrQuery(final HttpServletRequest req) {

        if (!req.isSecure()) {
            throw new SslRequiredException();
        }

        if (req.getHeader("Authorization") != null) {
            final String basicCredentials = getDecodedValue(req, BASIC_AUTHORIZATION);
            final String[] credentials = basicCredentials.split(":");
            if (credentials.length != 2) {
                throw new InvalidClientException(BASIC_AUTHORIZATION);
            }
            return new ClientCredentials(credentials[0], credentials[1]);
        } else if (req.getParameter("client_id") != null && req.getParameter("client_secret") != null) {
            return new ClientCredentials(req.getParameter("client_id"), req.getParameter("client_secret"));
        } else {
            throw new InvalidClientException(BASIC_AUTHORIZATION);
        }
    }

    public static String processBearer(final HttpServletRequest req) {

        if (!req.isSecure()) {
            throw new SslRequiredException();
        }

        if (req.getHeader("Authorization") != null) {
            return getDecodedValue(req, BEARER_AUTHORIZATION);
        } else {
            throw new InvalidClientException(BEARER_AUTHORIZATION);
        }
    }

    private AuthorizationUtil() {

    }
}
