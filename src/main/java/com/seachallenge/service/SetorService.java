package com.seachallenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import com.seachallenge.model.Setor;
import com.seachallenge.repository.SetorRepository;

@Service
public class SetorService {
	
	@Autowired
    private SetorRepository setorRepository;
	
	public Optional<Setor> cadastrarSetor(Setor setor) throws Exception{
	
	List<Setor> allSetor = setorRepository.findAll();
	
	
	for(Setor setorChecagem: allSetor) {
		
		if(setorChecagem.getNome().equals(setor.getNome())) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Este setor já existe, tente novamente.", null);
		}
			
		}
	
	return Optional.of(setorRepository.save(setor));
	
	}
	
	public Optional<Setor> atualizarSetor(Setor setor){
		
		if(setorRepository.findById(setor.getId()).isPresent()) {
			
            List<Setor> allSetor = setorRepository.findAll();
        	
        	for(Setor setorChecagem : allSetor) {
        		if(setorChecagem.getNome().equals(setor.getNome()))
        			throw new ResponseStatusException(
        					HttpStatus.BAD_REQUEST, "Este setor já existe, tente novamente.", null);
        		
        }
	
	}
				
	return Optional.of(setorRepository.save(setor));
	
	}
	
	public Optional<Setor> deletarSetor(@PathVariable Long id){
		
		Optional<Setor> setor = setorRepository.findById(id);
		
		if(setor.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		 setorRepository.deleteById(id);
		
		 return null;
	}
}
