package com.plantpoppa.plant.security;

import com.hubspot.horizon.HttpClient;
import com.hubspot.horizon.HttpRequest;
import com.hubspot.horizon.HttpResponse;
import com.hubspot.horizon.apache.ApacheHttpClient;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.security.auth.login.CredentialException;
import java.util.HashMap;

public class CredentialManager {
    private String uuid;// = System.getenv("plantUuid");
    private String secret;// = System.getenv("plantSecret");
    private String jwt = "";
    private String refreshToken;
//    private final String authUrl = "http://localhost:8080";
    private String authUrl;

    private HttpClient httpClient;


    public CredentialManager() {

    }

    @PostConstruct
    public void init() throws CredentialException {
        authenticate();

    }

    public void authenticate() throws CredentialException {
        HashMap<String, String> body = new HashMap<>();
        body.put("uuid",uuid);
        body.put("secret", secret);

        HttpRequest request = HttpRequest.newBuilder()
                .setUrl(authUrl + "/auth/service/authenticate")
                .setMethod(HttpRequest.Method.POST)
                .setBody(body)
                .setContentType(HttpRequest.ContentType.JSON)
                .build();

        HttpResponse response = httpClient.execute(request);

        if(response.getStatusCode() != 200) {
            throw new CredentialException("Unable to authenticate to auth service.");
        }

        RefreshResponse credentials = response.getAs(RefreshResponse.class);

        this.setJwt(credentials.getJwt());
        this.setRefreshToken(credentials.getRefreshToken());
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
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

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void refreshJwt() {
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
        this.setJwt(response.getJwt());
        this.setRefreshToken(response.getRefreshToken());

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
