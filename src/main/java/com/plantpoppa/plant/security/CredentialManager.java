package com.plantpoppa.plant.security;

import com.hubspot.horizon.HttpClient;
import com.hubspot.horizon.HttpRequest;
import com.hubspot.horizon.HttpResponse;
import com.hubspot.horizon.apache.ApacheHttpClient;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class CredentialManager {
    private static String uuid = System.getenv("plantUuid");
    private static String secret = System.getenv("plantSecret");
    private static String jwt = "";
    private static String refreshToken = System.getenv("plantRefreshToken");


    private static String authUrl = "http://localhost:8080";

    private static final String FILE_NAME = "credentials.dat";

    public static String getUuid() {
        return uuid;
    }

    public static void setUuid(String uuid) {
        CredentialManager.uuid = uuid;
    }

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        CredentialManager.secret = secret;
    }

    public static String getJwt() {
        return jwt;
    }

    public static void setJwt(String jwt) {
        CredentialManager.jwt = jwt;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }

    public static void setRefreshToken(String refreshToken) {
        CredentialManager.refreshToken = refreshToken;
    }

    public static void refreshJwt() {
        // Request new JWT
        // Create body
        HashMap<String, String> body = new HashMap<>();
        body.put("uuid",uuid);
        body.put("secret", secret);
        body.put("refreshToken",refreshToken);

        HttpClient httpClient = new ApacheHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .setUrl(authUrl + "/auth/service/refresh-token")
                .setMethod(HttpRequest.Method.POST)
                .setBody(body)
                .build();

        RefreshResponse response = httpClient.execute(request).getAs(RefreshResponse.class);

        // Store new JWT and refreshToken
        CredentialManager.setJwt(response.getJwt());
        CredentialManager.setRefreshToken(response.getRefreshToken());



    }


    @Component
    private static class RefreshResponse {
        private String jwt;
        private String refreshToken;

        public RefreshResponse(String jwt, String refreshToken) {
            this.jwt = jwt;
            this.refreshToken = refreshToken;
        }

        public RefreshResponse() {
        }

        public String getJwt() {
            return jwt;
        }

        public void setJwt(String jwt) {
            this.jwt = jwt;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }
}
