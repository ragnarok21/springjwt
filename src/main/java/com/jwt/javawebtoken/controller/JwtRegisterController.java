package com.jwt.javawebtoken.controller;

import com.jwt.javawebtoken.domain.User;
import com.jwt.javawebtoken.service.GenericService;
import com.jwt.javawebtoken.utils.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.util.StringUtils.isEmpty;

@RestController
@RequestMapping("/register")
public class JwtRegisterController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private GenericService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody User user){
        userService.createUser(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('STANDARD_USER', 'ADMIN_USER')")
    public void logout(HttpServletRequest request){
        String fullToken = HeaderUtil.getHeader(request);
        if (!isEmpty(fullToken)){
            OAuth2AccessToken oAuth2Authentication  = tokenStore.readAccessToken((HeaderUtil.getTokenHeader(fullToken)));
            tokenStore.removeAccessToken(oAuth2Authentication);
        }

    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('STANDARD_USER', 'STANDARD_ADMIN')")
    public User userInfo(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        user.setPassword(null);
        return user;
     }

}
