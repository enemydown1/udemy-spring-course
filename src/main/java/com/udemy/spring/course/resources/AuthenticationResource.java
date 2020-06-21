package com.udemy.spring.course.resources;

import com.udemy.spring.course.security.JWTUtils;
import com.udemy.spring.course.security.UserSpringSecurity;
import com.udemy.spring.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value="/auth")
public class AuthenticationResource {

    @Autowired
    private JWTUtils jwtUtils;

    @RequestMapping(value="/refresh_token", method=RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSpringSecurity user = UserService.authenticated();
        String token = jwtUtils.generateToken(user.getUsername());
        response.addHeader("Authorization","Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
