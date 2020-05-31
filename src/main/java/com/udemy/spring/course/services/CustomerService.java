package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Cliente;
import com.udemy.spring.course.repositories.ResourceRepository;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private ResourceRepository repository;

    public Cliente buscar(Integer id){
        Optional<Cliente> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException((
                "Objeto não encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()
                )));
    }

}
