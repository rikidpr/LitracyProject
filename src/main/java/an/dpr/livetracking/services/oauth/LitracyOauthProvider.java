package an.dpr.livetracking.services.oauth;

import java.util.Calendar;
import java.util.List;

import org.apache.cxf.rs.security.oauth2.common.AccessTokenRegistration;
import org.apache.cxf.rs.security.oauth2.common.Client;
import org.apache.cxf.rs.security.oauth2.common.OAuthPermission;
import org.apache.cxf.rs.security.oauth2.common.ServerAccessToken;
import org.apache.cxf.rs.security.oauth2.common.UserSubject;
import org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider;
import org.apache.cxf.rs.security.oauth2.provider.OAuthServiceException;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LitracyOauthProvider implements OAuthDataProvider{
    
    private static final Logger log = LoggerFactory.getLogger(LitracyOauthProvider.class);

    @Override
    public Client getClient(String clientId) throws OAuthServiceException {
	log.debug("inicio");
	// TODO Auto-generated method stub
	Client client = new Client(clientId, "secreto", true);//TODO PRUEBA
	return client;
    }

    @Override
    public ServerAccessToken createAccessToken(
	    AccessTokenRegistration accessToken) throws OAuthServiceException {
	// TODO Auto-generated method stub
	log.debug("inicio");
	return null;
    }

    @Override
    public ServerAccessToken getAccessToken(String accessToken)
	    throws OAuthServiceException {
	// TODO Auto-generated method stub
	log.debug("inicio:"+accessToken);
	ServerAccessToken sat = null;
	if(validateToken(accessToken)){
	    sat = new ServerAccessToken() {
	    };
	    sat.setClient(getClient("clientIdDeFulano"));
	    Long issuedAt = Calendar.getInstance().getTimeInMillis();
	    sat.setIssuedAt(issuedAt);//cuando se expidio para conprobar si ha expirado 
	    sat.setExpiresIn(issuedAt+360000);
	    sat.setTokenKey(accessToken);
	    sat.setTokenType("Bearer");
	    sat.setGrantType("ALL");
	    UserSubject subject = new UserSubject("Fulano");
	    sat.setSubject(subject);
	}
	return sat;
    }

    private boolean validateToken(String accessToken) {
	// TODO Auto-generated method stub estamos validnado todo token que venga
	
	return true;
    }

    @Override
    public ServerAccessToken getPreauthorizedToken(Client client,
	    List<String> requestedScopes, UserSubject subject, String grantType)
	    throws OAuthServiceException {
	// TODO Auto-generated method stub
	log.debug("inicio");
	return null;
    }

    @Override
    public ServerAccessToken refreshAccessToken(Client client,
	    String refreshToken, List<String> requestedScopes)
	    throws OAuthServiceException {
	// TODO Auto-generated method stub
	log.debug("inicio");
	return null;
    }

    @Override
    public void removeAccessToken(ServerAccessToken accessToken)
	    throws OAuthServiceException {
	// TODO Auto-generated method stub
	log.debug("inicio");
	
    }

    @Override
    public void revokeToken(Client client, String token, String tokenTypeHint)
	    throws OAuthServiceException {
	// TODO Auto-generated method stub
	log.debug("inicio");
    }

    @Override
    public List<OAuthPermission> convertScopeToPermissions(Client client,
	    List<String> requestedScope) {
	// TODO Auto-generated method stub
	log.debug("inicio");
	return null;
    }

}
