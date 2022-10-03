package com.seachallenge.repository;

import com.seachallenge.model.Trabalhador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {
    public List<Trabalhador> findAllByCpfContainingIgnoreCase(@Param("cpf")String cpf);
}
