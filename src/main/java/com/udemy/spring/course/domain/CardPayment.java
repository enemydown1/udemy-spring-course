package com.udemy.spring.course.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.udemy.spring.course.domain.enums.PaymentState;

import javax.persistence.Entity;

@Entity
@JsonTypeName("cardPayment")
public class CardPayment extends Payment {
    private static final long serialVersionUID = 1L;

    private Integer installmentsNumber;

    public CardPayment(){

    }

    public CardPayment(Integer id, PaymentState state, Request request, Integer installmentsNumber) {
        super(id, state, request);
        this.installmentsNumber = installmentsNumber;
    }

    public Integer getInstallmentsNumber() {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(Integer installmentsNumber) {
        this.installmentsNumber = installmentsNumber;
    }
}
