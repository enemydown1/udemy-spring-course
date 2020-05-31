package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<Estado, Integer> {

}
