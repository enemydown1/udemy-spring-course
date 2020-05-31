package com.udemy.spring.course.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udemy.spring.course.domain.enums.CustomerType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String personIdentity;
    private Integer type;

    @OneToMany(mappedBy="customer")
    private List<Endereco> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="PHONE")
    private Set<String> phones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="customer")
    private List<Pedido> orders = new ArrayList<>();

    public Customer(){
    }

    public Customer(Integer id, String name, String email, String personType, CustomerType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.personIdentity = personType;
        this.type = type.getCode();
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

    public String getPersonIdentity() {
        return personIdentity;
    }

    public void setPersonIdentity(String personIdentity) {
        this.personIdentity = personIdentity;
    }

    public CustomerType getType() {
        return CustomerType.toEnum(type);
    }

    public void setType(CustomerType type) {
        this.type = type.getCode();
    }

    public List<Endereco> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Endereco> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public List<Pedido> getOrders() {
        return orders;
    }

    public void setOrders(List<Pedido> orders) {
        this.orders = orders;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}