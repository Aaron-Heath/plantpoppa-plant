package com.plantpoppa.plant.filters;

import com.ctc.wstx.util.StringUtil;
import com.hubspot.horizon.HttpClient;
import com.hubspot.horizon.HttpRequest;
import com.hubspot.horizon.HttpResponse;
import com.hubspot.horizon.apache.ApacheHttpClient;
import com.plantpoppa.plant.models.SimpleUser;
import com.plantpoppa.plant.security.CredentialManager;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.security.auth.login.CredentialException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                        ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse)  response;
        Optional<String> tokenParam = Optional.ofNullable(req.getHeader("AUTHORIZATION"));

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
            res.sendError(401, "Invalid Token");
            return;
        }



    }

    public Optional<SimpleUser> fetchUserFromAuth(String jwt) throws CredentialException {
        HttpClient httpClient = new ApacheHttpClient();

        // Check if we have a serviceJwt
        String serviceJwt;

        if(CredentialManager.getJwt().isEmpty()) {
            CredentialManager.refreshJwt(); // Run token refresh flow when Jwt is empty
        }

        serviceJwt = CredentialManager.getJwt();
        String userJwt = jwt;

        // Set request body
        HashMap<String, String> body = new HashMap<>();
        body.put("jwt", userJwt);

        // Build request
        String url = "http://localhost:8080/auth/service/validate-token";
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
            System.out.println(responseBody.get("message"));
            throw new CredentialException(responseBody.get("message"));
//            return Optional.empty();
        }


        SimpleUser simpleUser = authResponse.getAs(SimpleUser.class);
        return Optional.of(simpleUser);

    }
}
