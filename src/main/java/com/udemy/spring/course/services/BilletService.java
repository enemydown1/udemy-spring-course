package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.BilletPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BilletService {
    public void setBilletPayment(BilletPayment billetPayment, Date createdAt){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createdAt);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        billetPayment.setDueDate(calendar.getTime());
    }
}
