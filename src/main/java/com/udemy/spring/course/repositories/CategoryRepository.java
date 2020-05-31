package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
