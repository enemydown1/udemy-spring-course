package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Pedido;
import com.udemy.spring.course.repositories.OrderRepository;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Pedido buscar(Integer id){
        Optional<Pedido> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException((
                "Objeto não encontrado! Id: " + id + " Tipo: " + Pedido.class.getName()
                )));
    }

}
