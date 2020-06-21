package com.udemy.spring.course.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
public class RequestItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private RequestItemPK id = new RequestItemPK();

    private Double discount;
    private Integer quantity;
    private Double price;

    public RequestItem(){

    }

    public RequestItem(Request request, Product product, Double discount, Integer quantity, Double price) {
        id.setRequest(request);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public double getSubTotal(){
        return (price - discount) * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestItem that = (RequestItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @JsonIgnore
    public Request getRequest(){
        return id.getRequest();
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setRequest(Request request){
        id.setRequest(request);
    }

    public void setProduct(Product product){
        id.setProduct(product);
    }

    public RequestItemPK getId() {
        return id;
    }

    public void setId(RequestItemPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        StringBuilder builder = new StringBuilder();
        builder.append(getProduct().getName());
        builder.append(", Quantity: ");
        builder.append(getQuantity());
        builder.append(", Unity Price: ");
        builder.append(numberFormat.format(getPrice()));
        builder.append(", Subtotal: ");
        builder.append(numberFormat.format(getSubTotal()));
        builder.append("\n");
        return builder.toString();
    }
}
