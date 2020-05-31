package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Request;
import com.udemy.spring.course.repositories.RequestRepository;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repository;

    public Request find(Integer id){
        Optional<Request> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                String.format("Object not found with id {%s} of type %s", id, Request.class.getName())
        ));
    }

}
