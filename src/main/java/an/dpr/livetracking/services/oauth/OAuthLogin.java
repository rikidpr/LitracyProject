package an.dpr.livetracking.services.oauth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import an.dpr.livetracking.services.rest.OAuth2RS;

@WebServlet(value="/authFacebookCallback", name="facebook-auth-servlet")
public class OAuthLogin extends GenericServlet {
    
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(OAuth2RS.class);
    private static final Properties properties = new Properties(); 
    
    private String clientId;
    private String clientSecret;
    private String applicationHost;
    private String callbackUri;
    private final String FACEBOOK_CODE_ID = "?code=";
    
    static {
	InputStream inputStream = OAuth2RS.class.getResourceAsStream("/oauth.properties");
	try {
	    properties.load(inputStream);
	} catch (IOException e) {
	    log.error("Error cargando propiedades", e);
	}
    }

    @PostConstruct
    public void init() {
	clientId = properties.getProperty("facebook.clientId");
	clientSecret = properties.getProperty("facebook.clientSecret");
	applicationHost = properties.getProperty("facebook.applicationHost");
	callbackUri = applicationHost + properties.getProperty("facebook.callbackUri");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
	    throws ServletException, IOException {
	OAuthClientRequest request;
	String code = req.getParameter("code");
	try {
	    request = OAuthClientRequest
	                .tokenProvider(OAuthProviderType.FACEBOOK)
	                .setGrantType(GrantType.AUTHORIZATION_CODE)
	                .setClientId(clientId)
	                .setClientSecret(clientSecret)
	                .setRedirectURI(callbackUri)
	                .setCode(code)
	                .buildQueryMessage();

	    OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

	    // Facebook is not fully compatible with OAuth 2.0 draft 10, access
	    // token response isc
	    // application/x-www-form-urlencoded, not json encoded so we use
	    // dedicated response class for that
	    // Own response class is an easy way to deal with oauth providers
	    // that introduce modifications to
	    // OAuth specification
	    GitHubTokenResponse oAuthResponse = oAuthClient.accessToken(request, GitHubTokenResponse.class);
	    

	    log.debug("Access Token: " + oAuthResponse.getAccessToken() + ", Expires in: "
		    + oAuthResponse.getExpiresIn());
	    
	    OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest("https://graph.facebook.com/me")
	         .setAccessToken(oAuthResponse.getAccessToken()).buildQueryMessage();
	 
	    OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
	    System.out.println(resourceResponse.getBody());
	} catch (OAuthProblemException e) {
	    log.error("OAuth error: " + e.getError());
	    log.error("OAuth error description: " + e.getDescription());
	} catch (OAuthSystemException e) {
	    log.error("Error autenticando", e);
	}
	
    }

}
