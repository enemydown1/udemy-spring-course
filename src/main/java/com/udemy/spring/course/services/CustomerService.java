package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Category;
import com.udemy.spring.course.domain.Customer;
import com.udemy.spring.course.domain.Request;
import com.udemy.spring.course.dto.CategoryDTO;
import com.udemy.spring.course.dto.CustomerDTO;
import com.udemy.spring.course.repositories.ResourceRepository;
import com.udemy.spring.course.services.exception.DataIntegrityException;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Customer> findAll(){
        return repository.findAll();
    }

    public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Customer insert(Customer obj){
        obj.setId(null);
        return repository.save(obj);
    }

    public Customer update(Customer obj) {
        Customer newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(Customer newObj, Customer obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try{
            repository.deleteById(id);
        } catch(DataIntegrityViolationException exception){
            throw new DataIntegrityException("Delete is not possible when customer has requests and address");
        }
    }

    public Customer fromDTO(CustomerDTO objDto){
        return new Customer(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
    }
}
