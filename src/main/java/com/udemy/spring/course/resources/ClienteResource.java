package com.udemy.spring.course.resources;

import com.udemy.spring.course.domain.Categoria;
import com.udemy.spring.course.domain.Cliente;
import com.udemy.spring.course.services.CategoriaService;
import com.udemy.spring.course.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){

        Cliente obj = service.buscar(id);

        return ResponseEntity.ok().body(obj);
    }

}
