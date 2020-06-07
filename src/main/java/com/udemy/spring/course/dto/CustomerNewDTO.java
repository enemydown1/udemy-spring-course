package com.udemy.spring.course.dto;

import com.udemy.spring.course.services.validation.CustomerInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@CustomerInsert
public class CustomerNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message="Cannot be null")
    @Length(min=5, max=120, message="Must be between 5 and 120 characters")
    private String name;

    @NotEmpty(message="Cannot be null")
    @Email(message="Email not valid")
    private String email;

    @NotEmpty(message="Cannot be null")
    private String personIdentity;

    private Integer type;

    @NotEmpty(message="Cannot be null")
    private String publicPlace;

    @NotEmpty(message="Cannot be null")
    private String number;

    private String complement;

    private String neighborhood;

    @NotEmpty(message="Cannot be null")
    private String zipCode;

    @NotEmpty(message="Cannot be null")
    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    public CustomerNewDTO(){

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

    public String getPersonIdentity() {
        return personIdentity;
    }

    public void setPersonIdentity(String personIdentity) {
        this.personIdentity = personIdentity;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }





}
