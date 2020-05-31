package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Category;
import com.udemy.spring.course.repositories.CategoryRepository;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category buscar(Integer id){
        Optional<Category> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException((
                "Objeto não encontrado! Id: " + id + " Tipo: " + Category.class.getName()
                )));
    }

    public Category insert(Category obj){
        obj.setId(null);
        return repository.save(obj);
    }

}
