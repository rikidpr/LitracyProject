package an.dpr.livetracking.services.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

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

@Path("/auth/")
public class OAuth2RS {
    
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


    @GET
    @Path("/facebook")
    public Response startFacebookAuthentication() throws OAuthSystemException, URISyntaxException {
	OAuthClientRequest request = OAuthClientRequest.authorizationProvider(OAuthProviderType.FACEBOOK)
		.setClientId(clientId).setRedirectURI(callbackUri).buildQueryMessage();
	return Response.temporaryRedirect(new URI(request.getLocationUri())).build();
    }

    @GET
    @Path("/facebook/callback/{code}")
    public void callback(@PathParam("code") String pCode) throws OAuthSystemException {
	OAuthClientRequest request;
	String code = pCode.substring(FACEBOOK_CODE_ID.length() - 1);
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
	    

	    //TODO si no falla, se da por logeado
	    // app
	    log.debug("Access Token: " + oAuthResponse.getAccessToken() + ", Expires in: "
		    + oAuthResponse.getExpiresIn());
	    
	    OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest("https://graph.facebook.com/me")
	         .setAccessToken(oAuthResponse.getAccessToken()).buildQueryMessage();
	 
	    OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
	    System.out.println(resourceResponse.getBody());
	} catch (OAuthProblemException e) {
	    System.out.println("OAuth error: " + e.getError());
	    System.out.println("OAuth error description: " + e.getDescription());
	}
    }
}
