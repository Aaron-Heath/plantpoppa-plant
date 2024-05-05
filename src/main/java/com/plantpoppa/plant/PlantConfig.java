package com.plantpoppa.plant;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

 import software.amazon.awssdk.regions.Region;
 import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
 import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
 import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Configuration
@ComponentScan("com.plantpoppa.plant")
public class PlantConfig {
    public static void getSecret() {

        String secretName = "plantServiceInfo";
        Region region = Region.of("us-east-1");

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

        String secret = getSecretValueResponse.secretString();

        // Your code goes here.
    }

}
