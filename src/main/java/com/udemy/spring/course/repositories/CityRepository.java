package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {

}
