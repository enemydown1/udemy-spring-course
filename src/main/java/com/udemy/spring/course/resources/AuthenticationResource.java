package com.udemy.spring.course.resources;

import com.udemy.spring.course.dto.EmailDTO;
import com.udemy.spring.course.security.JWTUtils;
import com.udemy.spring.course.security.UserSpringSecurity;
import com.udemy.spring.course.services.AuthenticationService;
import com.udemy.spring.course.services.UserService;
import com.udemy.spring.course.services.exception.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value="/auth")
public class AuthenticationResource {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value="/refresh_token", method=RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSpringSecurity user = UserService.authenticated();
        if(user == null ) throw new AuthorizationException("Access denied!");
        String token = jwtUtils.generateToken(user.getUsername());
        response.addHeader("Authorization","Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/forgot", method=RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO){
        authenticationService.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }
}
