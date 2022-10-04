package com.seachallenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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
				throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Este cargo já existe neste setor!", null);
			
		}
	}
	}
	
	return Optional.of(cargoRepository.save(cargo));
	
	}
	
	public Optional<Cargo> atualizarCargo(Cargo cargo){
		if (cargoRepository.existsById(cargo.getId())){

			List<Setor> allSetor = setorRepository.findAll();
			
			for(Setor setor : allSetor) {
				for(Cargo cargo2 : setor.getCargo()) {
					if(cargo2.getNome().equals(cargo.getNome())) {
						throw new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Este cargo já existe neste setor!", null);     
        }
	
	}
				
}
}
	return Optional.of(cargoRepository.save(cargo));
	
	}
	
	public Optional<Cargo> deletarCargo(@PathVariable Long id){
		
		Optional<Cargo> cargo = cargoRepository.findById(id);
		
		if(cargo.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		 cargoRepository.deleteById(id);
		
		 return null;
	}
}