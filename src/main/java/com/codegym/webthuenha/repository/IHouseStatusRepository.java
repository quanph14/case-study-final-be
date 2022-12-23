package com.codegym.webthuenha.repository;

import com.codegym.webthuenha.model.HouseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IHouseStatusRepository extends JpaRepository<HouseStatus, Long> {
}
