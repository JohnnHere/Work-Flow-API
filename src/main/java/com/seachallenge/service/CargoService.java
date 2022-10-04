package com.seachallenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.seachallenge.model.Cargo;
import com.seachallenge.model.Setor;

import com.seachallenge.repository.CargoRepository;
import com.seachallenge.repository.SetorRepository;

@Service
public class CargoService {

	@Autowired
    private CargoRepository cargoRepository;
	
	@Autowired
    private SetorRepository setorRepository;
	
	public Optional<Cargo> cadastrarCargo(Cargo cargo) throws Exception{
	
	List<Setor> allSetor = setorRepository.findAll();
	
	for(Setor setor : allSetor) {
		for(Cargo cargo2 : setor.getCargo()) {
			if(cargo2.getNome().equals(cargo.getNome())) {
				throw new Exception("Cargo já cadastrado.");
			
		}
	}
	}
	
	return Optional.of(cargoRepository.save(cargo));
	
	}
}
