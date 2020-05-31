package com.udemy.spring.course.resources;

import com.udemy.spring.course.domain.Customer;
import com.udemy.spring.course.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/customers")
public class CustomerResource {

    @Autowired
    private CustomerService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){

        Customer obj = service.buscar(id);

        return ResponseEntity.ok().body(obj);
    }

}
