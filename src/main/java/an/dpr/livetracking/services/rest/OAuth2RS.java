package an.dpr.livetracking.services.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

@Path("/auth/")
public class OAuth2RS {

    private String clientId;
    private String clientSecret;
    private String applicationHost;
    private String callbackUri;
    private String loginUri;
    private final String FACEBOOK_CODE_ID = "?code=";

    @PostConstruct
    public void init() {
	clientId = "131804060198305";
	clientSecret = "3acb294b071c9aec86d60ae3daf32a93";
	applicationHost = "localhost:8282";
	callbackUri = applicationHost + "/auth/facebook/callback";
	loginUri = applicationHost + "/welcome";
    }

    @GET
    @Path("/facebook")
    public Response startFacebookAuthentication() throws OAuthSystemException, URISyntaxException {
	OAuthClientRequest request = OAuthClientRequest.authorizationProvider(OAuthProviderType.FACEBOOK)
		.setClientId(clientId).setRedirectURI(callbackUri).buildQueryMessage();
	return Response.temporaryRedirect(new URI(request.getLocationUri())).build();
    }

    @GET
    @Path("/auth/facebook/callback/{code}")
    public void callback(@PathParam("code") String pCode) throws OAuthSystemException {
	OAuthClientRequest request;
	String code = pCode.substring(FACEBOOK_CODE_ID.length() - 1);
	try {
	    request = OAuthClientRequest.tokenProvider(OAuthProviderType.FACEBOOK)
		    .setGrantType(GrantType.AUTHORIZATION_CODE).setClientId(clientId).setClientSecret(clientSecret)
		    .setRedirectURI(loginUri).setCode(code).buildBodyMessage();

	    OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

	    // Facebook is not fully compatible with OAuth 2.0 draft 10, access
	    // token response is
	    // application/x-www-form-urlencoded, not json encoded so we use
	    // dedicated response class for that
	    // Own response class is an easy way to deal with oauth providers
	    // that introduce modifications to
	    // OAuth specification
	    GitHubTokenResponse oAuthResponse = oAuthClient.accessToken(request, GitHubTokenResponse.class);

	    // TODO con el token comprobamos que el idusuario y entramos en la
	    // app
	    System.out.println("Access Token: " + oAuthResponse.getAccessToken() + ", Expires in: "
		    + oAuthResponse.getExpiresIn());
	} catch (OAuthProblemException e) {
	    System.out.println("OAuth error: " + e.getError());
	    System.out.println("OAuth error description: " + e.getDescription());
	}
    }
}
