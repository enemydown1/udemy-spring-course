package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Categoria;
import com.udemy.spring.course.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
