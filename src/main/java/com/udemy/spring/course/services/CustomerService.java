package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Customer;
import com.udemy.spring.course.domain.Request;
import com.udemy.spring.course.repositories.ResourceRepository;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private ResourceRepository repository;

    public Customer find(Integer id){
        Optional<Customer> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                String.format("Object not found with id {%s} of type %s", id, Customer.class.getName())
        ));
    }

}
