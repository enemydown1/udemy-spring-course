package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Customer;
import com.udemy.spring.course.domain.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Transactional(readOnly=true)
    Page<Request> findByCustomer(Customer customer, Pageable pageRequest);
}
