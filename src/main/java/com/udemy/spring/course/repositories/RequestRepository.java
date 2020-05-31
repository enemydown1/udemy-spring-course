package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
