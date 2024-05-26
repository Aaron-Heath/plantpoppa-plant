package com.plantpoppa.plant;

import com.hubspot.horizon.apache.ApacheHttpClient;
import com.plantpoppa.plant.security.CredentialManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.plantpoppa.plant")
public class PlantConfig {
    @Bean
    public CredentialManager credentialManager(@Value("${credentialManager.uuid}") String uuid,
                                               @Value("${credentialManager.secret}") String secret,
                                               @Value("${credentialManager.authUrl}") String authUrl) {
        CredentialManager credentialManager = new CredentialManager();

        credentialManager.setUuid(uuid);
        credentialManager.setSecret(secret);
        credentialManager.setAuthUrl(authUrl);
        credentialManager.setHttpClient(new ApacheHttpClient());

        return credentialManager;
    }

}
