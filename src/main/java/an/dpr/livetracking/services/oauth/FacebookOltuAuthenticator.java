package an.dpr.livetracking.services.oauth;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FacebookOltuAuthenticator {
 
  private final String clientId;
  private final String clientSecret;
  private final String applicationHost;
  private final ObjectMapper objectMapper;
 
  @Autowired
  public FacebookOltuAuthenticator(
      @Value("#{properties['facebook.clientId']}") 
       String clientId,
      @Value("#{properties['facebook.clientSecret']}") 
       String clientSecret,
      @Value("#{properties['application.host']}") 
       String applicationHost) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.applicationHost = applicationHost;
    this.objectMapper = new ObjectMapper();
    //this.objectMapper.registerModule(new AfterburnerModule());
  }
  
}