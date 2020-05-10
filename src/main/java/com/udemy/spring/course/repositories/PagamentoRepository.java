package com.udemy.spring.course.repositories;

import com.udemy.spring.course.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
