package com.udemy.spring.course.resources;

import com.udemy.spring.course.domain.Category;
import com.udemy.spring.course.domain.Customer;
import com.udemy.spring.course.dto.CategoryDTO;
import com.udemy.spring.course.dto.CustomerDTO;
import com.udemy.spring.course.dto.CustomerNewDTO;
import com.udemy.spring.course.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/customers")
public class CustomerResource {

    @Autowired
    private CustomerService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Customer> find(@PathVariable Integer id){

        Customer obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerNewDTO objDto){
        Customer obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CustomerDTO objDto, @PathVariable Integer id){
        Customer obj = service.fromDTO(objDto);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<CustomerDTO>> findAll(){
        List<Customer> list = service.findAll();
        List<CustomerDTO> listDto = list.stream().map(CustomerDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<CustomerDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction){
        Page<Customer> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CustomerDTO> listDto = list.map(CustomerDTO::new);
        return ResponseEntity.ok().body(listDto);
    }

}
