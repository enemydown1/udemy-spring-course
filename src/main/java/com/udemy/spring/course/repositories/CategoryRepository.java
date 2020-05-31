package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categoria, Integer> {
}
