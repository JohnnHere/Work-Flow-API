package com.seachallenge.repository;

import com.seachallenge.model.Trabalhador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {


}
