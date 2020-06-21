package com.udemy.spring.course.services;

import com.udemy.spring.course.security.UserSpringSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
    public static UserSpringSecurity authenticated(){
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception exception){
            return null;
        }
    }
}
