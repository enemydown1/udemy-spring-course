package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
