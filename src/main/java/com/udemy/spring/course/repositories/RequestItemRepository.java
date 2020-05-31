package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.RequestItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestItemRepository extends JpaRepository<RequestItem, Integer> {
}
