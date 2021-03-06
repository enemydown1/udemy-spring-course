package com.udemy.spring.course.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.udemy.spring.course.domain.enums.PaymentState;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("billetPayment")
public class BilletPayment extends Payment {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dueDate;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date payday;

    public BilletPayment(){

    }

    public BilletPayment(Integer id, PaymentState state, Request request, Date dueDate, Date payday) {
        super(id, state, request);
        this.dueDate = dueDate;
        this.payday = payday;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPayday() {
        return payday;
    }

    public void setPayday(Date payday) {
        this.payday = payday;
    }
}
