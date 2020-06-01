package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Category;
import com.udemy.spring.course.domain.Request;
import com.udemy.spring.course.repositories.CategoryRepository;
import com.udemy.spring.course.services.exception.DataIntegrityException;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category find(Integer id){
        Optional<Category> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                String.format("Object not found with id {%s} of type %s", id, Category.class.getName()))
        );
    }

    public Category insert(Category obj){
        obj.setId(null);
        return repository.save(obj);
    }

    public Category update(Category obj) {
        find(obj.getId());
        return repository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try{
            repository.deleteById(id);
        } catch(DataIntegrityViolationException exception){
            throw new DataIntegrityException("Delete is not possible when categories has products associated");
        }
    }
}
