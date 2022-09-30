package com.seachallenge.repository;

import com.seachallenge.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    public List<Endereco> findAllByCepContainingIgnoreCase(@Param("cep")String cep);

}
