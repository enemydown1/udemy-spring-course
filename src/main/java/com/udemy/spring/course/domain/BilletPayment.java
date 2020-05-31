package com.udemy.spring.course.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.udemy.spring.course.domain.enums.PaymentState;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class BilletPayment extends Pagamento{
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date payday;

    public BilletPayment(){

    }

    public BilletPayment(Integer id, PaymentState state, Pedido order, Date dueDate, Date payday) {
        super(id, state, order);
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
