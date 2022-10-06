package com.seachallenge.repository;

import com.seachallenge.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SetorRepository extends JpaRepository<Setor, Long> {
    public List<Setor> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);

	public Optional<Setor> findByNome(@Param ("nome")String nome);
}
