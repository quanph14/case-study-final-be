package com.codegym.webthuenha.repository;

import com.codegym.webthuenha.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
