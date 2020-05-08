package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Categoria;
import com.udemy.spring.course.domain.Cliente;
import com.udemy.spring.course.repositories.CategoriaRepository;
import com.udemy.spring.course.repositories.ClienteRepository;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente buscar(Integer id){
        Optional<Cliente> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException((
                "Objeto não encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()
                )));
    }

}
