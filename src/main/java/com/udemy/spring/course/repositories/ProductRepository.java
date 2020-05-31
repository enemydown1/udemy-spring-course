package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
