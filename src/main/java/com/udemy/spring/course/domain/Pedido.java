package com.udemy.spring.course.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instante;

    @OneToOne(cascade=CascadeType.ALL, mappedBy= "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="endereco_de_entrega_id")
    private Address addressDeEntrega;

    @OneToMany(mappedBy= "id.order")
    private Set<OrderItem> itens = new HashSet<>();

    public Pedido(){

    }

    public Pedido(Integer id, Date instante, Customer customer, Address addressDeEntrega) {
        this.id = id;
        this.instante = instante;
        this.customer = customer;
        this.addressDeEntrega = addressDeEntrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddressDeEntrega() {
        return addressDeEntrega;
    }

    public void setAddressDeEntrega(Address addressDeEntrega) {
        this.addressDeEntrega = addressDeEntrega;
    }

    public Set<OrderItem> getItens() {
        return itens;
    }

    public void setItens(Set<OrderItem> itens) {
        this.itens = itens;
    }
}
