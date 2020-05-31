package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {

}
