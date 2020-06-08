package com.udemy.spring.course.resources;

import com.udemy.spring.course.domain.Category;
import com.udemy.spring.course.domain.Request;
import com.udemy.spring.course.dto.CategoryDTO;
import com.udemy.spring.course.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/requests")
public class RequestResource {

    @Autowired
    private RequestService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Request> find(@PathVariable Integer id){

        Request obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Request obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
