package net.trajano.openidconnect.provider.spi;

import java.io.IOException;

import javax.ejb.Local;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import net.trajano.openidconnect.provider.AuthenticationRequest;

/**
 * Upon successful authentication, implementers are expected to invoke any of
 * the methods below. This class is meant to be injected into a servlet or REST
 * service. ?? should I move this and perhaps key provider into a EJB jar.
 * 
 * @author Archimedes Trajano
 */
@Local
public interface AuthenticationRedirector {

    void performRedirect(HttpServletResponse response,
            AuthenticationRequest request,
            String subject) throws IOException, ServletException;

    Response buildResponse(AuthenticationRequest request,
            String subject);

}
