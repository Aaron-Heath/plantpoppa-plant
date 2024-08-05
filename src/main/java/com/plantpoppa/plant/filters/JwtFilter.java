package com.plantpoppa.plant.filters;

import com.hubspot.horizon.HttpClient;
import com.hubspot.horizon.HttpRequest;
import com.hubspot.horizon.HttpResponse;
import com.hubspot.horizon.apache.ApacheHttpClient;
import com.plantpoppa.plant.models.SimpleUser;
import com.plantpoppa.plant.security.CredentialManager;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.security.auth.login.CredentialException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtFilter implements Filter {
    @Autowired
    CredentialManager credentialManager;


    @Override
    public void doFilter(ServletRequest request,
                        ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse)  response;
        Optional<String> tokenParam = Optional.ofNullable(req.getHeader("AUTHORIZATION"));

        System.out.println(req.getMethod());
        if(req.getMethod().equals("OPTIONS")) {
            chain.doFilter(request, response);
            return;
        }

        if(tokenParam.isEmpty()) {
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.setContentType(MediaType.TEXT_HTML_VALUE);

            res.sendError(401, "No token provided");
            return;
        }

        String userJwt = StringUtils.replace(tokenParam.get(), "Bearer ", "");
        Optional<SimpleUser> optionalUser;
        try {
            optionalUser = fetchUserFromAuth(userJwt);
            SimpleUser simpleUser = optionalUser.get();

            request.setAttribute("userInfo", simpleUser);
            chain.doFilter(request, response);

        } catch (CredentialException e) {
            String errorMessage = e.getMessage();
            res.sendError(401, errorMessage);
            return;
        }



    }

    public Optional<SimpleUser> fetchUserFromAuth(String jwt) throws CredentialException {
        HttpClient httpClient = new ApacheHttpClient();

        // Check if we have a serviceJwt
        String serviceJwt;

        if(credentialManager.getJwt().isEmpty()) {
            credentialManager.refreshJwt(); // Run token refresh flow when Jwt is empty
        }

        serviceJwt = credentialManager.getJwt();
        String userJwt = jwt;

        // Set request body
        HashMap<String, String> body = new HashMap<>();
        body.put("jwt", userJwt);

        // Build request
        String url = credentialManager.getAuthUrl() + "/auth/service/validate-token";
        HttpResponse authResponse;
        HttpRequest request = HttpRequest.newBuilder()
                .setUrl(url)
                .setMethod(HttpRequest.Method.POST)
                .setContentType(HttpRequest.ContentType.JSON)
                .addHeader("AUTHORIZATION", "Bearer " + serviceJwt)
                .setBody(body)
                .build();
        authResponse = httpClient.execute(request);
        if(authResponse.getStatusCode() != 200) {
            HashMap<String, String> responseBody = authResponse.getAs(HashMap.class);
            throw new CredentialException(responseBody.get("Message"));
        }


        SimpleUser simpleUser = authResponse.getAs(SimpleUser.class);
        return Optional.of(simpleUser);

    }
}
