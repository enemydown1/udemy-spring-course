package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<Cidade, Integer> {

}
