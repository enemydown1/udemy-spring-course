package com.udemy.spring.course.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date createdAt;

    @OneToOne(cascade=CascadeType.ALL, mappedBy= "request")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="delivery_address_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy= "id.request")
    private Set<RequestItem> itens = new HashSet<>();

    public Request(){

    }

    public Request(Integer id, Date createdAt, Customer customer, Address deliveryAddress) {
        this.id = id;
        this.createdAt = createdAt;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalValue(){
        double sum = 0.0;
        for(RequestItem requestItem : itens){
            sum += requestItem.getSubTotal();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id);
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        this.createdAt = new Date(simpleDateFormat.format(createdAt));
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

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Set<RequestItem> getItens() {
        return itens;
    }

    public void setItens(Set<RequestItem> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        final StringBuilder builder = new StringBuilder();
        builder.append("Request number: ");
        builder.append(getId());
        builder.append(", Created at: ");
        builder.append(simpleDateFormat.format(getCreatedAt()));
        builder.append(", Customer: ");
        builder.append(getCustomer().getName());
        builder.append(", Payment situation: ");
        builder.append(getPayment().getState().getDescription());
        builder.append("\nDetails:\n");
        for(RequestItem requestItem: getItens()){
            builder.append(requestItem.toString());
        }
        builder.append("Total value: ");
        builder.append(numberFormat.format(getTotalValue()));
        return builder.toString();
    }
}
