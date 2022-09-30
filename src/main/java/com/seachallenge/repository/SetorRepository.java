package com.seachallenge.repository;

import com.seachallenge.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SetorRepository extends JpaRepository<Setor, Long> {
    public List<Setor> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);
}
