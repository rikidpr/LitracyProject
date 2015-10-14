package an.dpr.livetracking.services.oauth;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.apache.cxf.rs.security.oauth2.common.Client;
import org.apache.cxf.rs.security.oauth2.common.OAuthPermission;
import org.apache.cxf.rs.security.oauth2.common.ServerAccessToken;
import org.apache.cxf.rs.security.oauth2.common.UserSubject;
import org.apache.cxf.rs.security.oauth2.provider.AbstractOAuthDataProvider;
import org.apache.cxf.rs.security.oauth2.provider.OAuthServiceException;
import org.apache.cxf.rs.security.oauth2.tokens.refresh.RefreshToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import an.dpr.livetracking.util.Contracts;

public class LitracyOauth2Provider extends AbstractOAuthDataProvider{
    
    private static final Logger log = LoggerFactory.getLogger(LitracyOauth2Provider.class);
    private static final ConcurrentHashMap<String, OAuthPermission> AVAILABLE_PERMISSIONS = new ConcurrentHashMap<String, OAuthPermission>();
    static {
	AVAILABLE_PERMISSIONS.put(Contracts.READ_PERMISSION, new OAuthPermission(Contracts.READ_PERMISSION, "Read information"));
	AVAILABLE_PERMISSIONS.put(Contracts.WRITE_PERMISSION, new OAuthPermission(Contracts.WRITE_PERMISSION, "Modify personal information"));
    }
    protected ConcurrentHashMap<String, Client> clientAuthInfo = new ConcurrentHashMap<String, Client>();
    protected MetadataMap<String, String> userRegisteredClients = new MetadataMap<String, String>();
    protected MetadataMap<String, String> userAuthorizedClients = new MetadataMap<String, String>();
    protected ConcurrentHashMap<String, ServerAccessToken> oauthTokens = new ConcurrentHashMap<String, ServerAccessToken>();
    protected ConcurrentHashMap<String, ServerAccessToken> refreshTokens = new ConcurrentHashMap<String, ServerAccessToken>();
    protected ConcurrentHashMap<String, ServerAccessToken> auxAccessTokens = new ConcurrentHashMap<String, ServerAccessToken>();
    
    public LitracyOauth2Provider(){
	//TODO creamos uno para pruebas, esto debera hacerlo una factoria cuando se logueen los usuarios 

	ServerAccessToken accessToken = new ServerAccessToken() {
	};
	accessToken.setClient(getClient("clientIdDeFulano"));
	Long issuedAt = Calendar.getInstance().getTimeInMillis();
	accessToken.setIssuedAt(issuedAt);//cuando se expidio para conprobar si ha expirado 
	accessToken.setExpiresIn(issuedAt+360000);
	accessToken.setTokenKey("fulanakoTokenDeAcceso");
	accessToken.setTokenType("Bearer");
	accessToken.setGrantType("ALL");
	UserSubject subject = new UserSubject("Fulano");
	accessToken.setSubject(subject);
//	indica la "audiencia" permitida pro este token, todavia no tengo muy claro de que manera se puede identificar dicha audiencia, si con una url, un mnombre...
	accessToken.setAudience("appTesteadoraProboneira"); 
//        accessToken.setApprovedScope(Arrays.asList("admin","tracking","live"));
	oauthTokens.put("fulanakoTokenDeAcceso", accessToken);
	userAuthorizedClients.putSingle("clientIdDeFulano", "secretoDeFulano");
    }

    @Override
    public Client getClient(String clientId) throws OAuthServiceException {
	log.debug("inicio");
	Client client = new Client(clientId, userAuthorizedClients.getFirst(clientId), true);//TODO PRUEBA
	return client;
    }

    @Override
    public ServerAccessToken getAccessToken(String accessToken)
	    throws OAuthServiceException {
	// TODO Auto-generated method stub
	log.debug("inicio:"+accessToken);
	ServerAccessToken sat = oauthTokens.get(accessToken);
	return sat;
    }

    @Override
    public void removeAccessToken(ServerAccessToken accessToken) throws OAuthServiceException {
	// TODO Auto-generated method stub
	log.debug("inicio");
	auxAccessTokens.remove(accessToken);
    }

    @Override
    protected void saveAccessToken(ServerAccessToken serverToken) {
	// TODO Auto-generated method stub
	log.debug("inicio");
	auxAccessTokens.put(serverToken.getTokenKey(), serverToken);
    }

    @Override
    protected void saveRefreshToken(ServerAccessToken at, RefreshToken refreshToken) {
	// TODO Auto-generated method stub
	log.debug("inicio");
    }

    @Override
    protected boolean revokeAccessToken(String accessTokenKey) {
	// TODO Auto-generated method stub
	removeAccessToken(auxAccessTokens.get(accessTokenKey));
	return true;
    }

    @Override
    protected RefreshToken revokeRefreshToken(Client client, String refreshTokenKey) {
	// TODO Auto-generated method stub
	log.debug("inicio");
	return null;
    }

}
