package com.jwt.javawebtoken.config;

import com.jwt.javawebtoken.domain.CustomUserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        CustomUserDetails currentUser = (CustomUserDetails) oAuth2Authentication.getUserAuthentication().getPrincipal();

            Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("name", currentUser.getName());
            additionalInfo.put("lastName", currentUser.getLastName());
            additionalInfo.put("email", currentUser.getEmail());
            additionalInfo.put("roles",currentUser.getRoles());
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(
                    additionalInfo);
            return oAuth2AccessToken;
    }
}
