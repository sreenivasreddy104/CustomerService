package com.practice.CustomerService.feignClient;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class FeignClientSecurityConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        String username = "ADMIN";
        String password = "aRAEqKwguCRIb1a";

        String credentials = username + ":" + password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        // Add the Authorization header for Basic Authentication
        template.header("Authorization", "Basic " + encodedCredentials);
    }
}
