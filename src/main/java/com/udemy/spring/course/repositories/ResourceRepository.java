package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Categoria;
import com.udemy.spring.course.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Cliente, Integer> {
}
