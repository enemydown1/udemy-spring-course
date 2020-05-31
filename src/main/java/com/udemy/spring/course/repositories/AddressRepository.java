package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
