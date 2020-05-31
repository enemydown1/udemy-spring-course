package com.udemy.spring.course.domain;

import com.udemy.spring.course.domain.enums.PaymentState;

import javax.persistence.Entity;

@Entity
public class CardPayment extends Pagamento{
    private static final long serialVersionUID = 1L;

    private Integer installmentsNumber;

    public CardPayment(){

    }

    public CardPayment(Integer id, PaymentState state, Pedido order, Integer installmentsNumber) {
        super(id, state, order);
        this.installmentsNumber = installmentsNumber;
    }

    public Integer getInstallmentsNumber() {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(Integer installmentsNumber) {
        this.installmentsNumber = installmentsNumber;
    }
}
