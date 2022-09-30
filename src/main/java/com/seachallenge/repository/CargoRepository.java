package com.seachallenge.repository;

import com.seachallenge.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
    public List<Cargo> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);
}
