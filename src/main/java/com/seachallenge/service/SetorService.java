package com.seachallenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seachallenge.model.Setor;
import com.seachallenge.repository.SetorRepository;

@Service
public class SetorService {

	@Autowired
    private SetorRepository setorRepository;
	
	public Optional<Setor> cadastrarSetor(Setor setor){
		if (setorRepository.findByNome(setor.getNome()).isPresent())
			return Optional.empty();
		
		return Optional.of(setorRepository.save(setor));
	}
	
}
