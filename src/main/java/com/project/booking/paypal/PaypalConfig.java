package com.project.booking.paypal;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Configuration
public class PaypalConfig {

    /**
     * LOAD THE PAYPAL PROPERTIES PAYPAL_MODE, PAYPAL_ID, PAYPAL_SECRET VALUE
     * ANNOTATION--> import org.springframework.beans.factory.annotation.Value;
     */
    @Value("${paypal.client.id}")
    private String clientId;
    @Value("${paypal.client.secret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;

    /**
     * PAYPAL SDK CONFIG
     * @return 
     */
    @Bean
    public Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);
        return configMap;
    }
    
    /**
     * CREDENTIAL
     * @return 
     */
    @Bean
    public OAuthTokenCredential oAuthTokenCredential() {
        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }
    
    /**
     * 
     * @return
     * @throws PayPalRESTException 
     */
    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }

}
