package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<ItemPedido, Integer> {
}
