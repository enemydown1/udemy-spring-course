package com.udemy.spring.course.dto;

import com.udemy.spring.course.domain.Customer;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message="Cannot be null")
    @Length(min=5, max=120, message="Must be between 5 and 120 characters")
    private String name;

    @NotEmpty(message="Cannot be null")
    @Email(message="Email not valid")
    private String email;

    public CustomerDTO(){
    }

    public CustomerDTO(Customer obj){
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
