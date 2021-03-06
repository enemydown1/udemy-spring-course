package com.udemy.spring.course.dto;

import com.udemy.spring.course.domain.Category;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message="Cannot be null")
    @Length(min=5, max=80,message="Size must be between five and eighty")
    private String name;

    public CategoryDTO(){
    }

    public CategoryDTO(Category obj){
        id = obj.getId();
        name = obj.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
