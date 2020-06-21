package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Customer;
import com.udemy.spring.course.repositories.CustomerRepository;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.OverridingClassLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthenticationService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email){
        Customer customer = customerRepository.findByEmail(email);
        if(customer == null) { throw new ObjectNotFoundException("Email not found"); }
        String newPassword = newPassword();
        customer.setPassword(bCryptPasswordEncoder.encode(newPassword));
        customerRepository.save(customer);
        emailService.sendNewPasswordEmail(customer, newPassword);
    }

    private String newPassword() {
        char[] vector = new char[10];
        for(int i=0; i<10; i++){
            vector[i] = randomChar();
        }
        return new String(vector);
    }

    private char randomChar() {
        int option = random.nextInt(3);
        if(option == 0) {
            return (char) (random.nextInt(10) + 48);
        }
        else if(option == 1){
            return (char) (random.nextInt(26) + 65);
        }
        else {
            return (char) (random.nextInt(26) + 97);
        }
    }

}
