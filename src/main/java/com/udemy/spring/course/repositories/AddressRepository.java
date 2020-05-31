package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Endereco, Integer> {
}
