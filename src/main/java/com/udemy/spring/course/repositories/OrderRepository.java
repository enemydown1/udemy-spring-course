package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Pedido, Integer> {
}
