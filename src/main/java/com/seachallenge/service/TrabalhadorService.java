package com.seachallenge.service;

import com.seachallenge.model.Trabalhador;
import com.seachallenge.repository.CargoRepository;
import com.seachallenge.repository.SetorRepository;
import com.seachallenge.repository.TrabalhadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TrabalhadorService {

	    @Autowired
	    private TrabalhadorRepository trabalhadorRepository;

	    @Autowired
	    private CargoRepository cargoRepository;

	    @Autowired
	    private SetorRepository setorRepository;
	    

        public Optional<Trabalhador> cadastrarTrabalhador(Trabalhador trabalhador) {
        	
        if(cargoRepository.existsById(trabalhador.getCargo().getId()) 
        	&& setorRepository.existsById(trabalhador.getSetor().getId())) {
        	
        	List<Trabalhador> allTrabalhador = trabalhadorRepository.findAll();
        	
        	for(Trabalhador trabalhadorChecagem : allTrabalhador) {
        		if(trabalhadorChecagem.getCpf().equals(trabalhador.getCpf()))
        			throw new ResponseStatusException(
        					HttpStatus.BAD_REQUEST, "Este CPF já existe, tente novamente.", null);
        	}
            		
        }

        return Optional.of(trabalhadorRepository.save(trabalhador));

    }

    public Optional<Trabalhador> atualizarTrabalhador(Trabalhador trabalhador) {

        if(trabalhadorRepository.findById(trabalhador.getId()).isPresent()) {
        	
        	if (cargoRepository.existsById(trabalhador.getCargo().getId()) 
        			&& setorRepository.existsById(trabalhador.getSetor().getId())) {

            List<Trabalhador> allTrabalhador = trabalhadorRepository.findAll();
        	
        	for(Trabalhador trabalhadorChecagem : allTrabalhador) {
        		if(trabalhadorChecagem.getCpf().equals(trabalhador.getCpf()))
        			throw new ResponseStatusException(
        					HttpStatus.BAD_REQUEST, "Este CPF já existe, tente novamente.", null);
        		
        }
        	
    }
         

}
        return Optional.ofNullable(trabalhadorRepository.save(trabalhador));
   
    }
    
        public Optional<Trabalhador> deletarTrabalhador(@PathVariable Long id){
		
		Optional<Trabalhador> trabalhador = trabalhadorRepository.findById(id);
		
		if(trabalhador.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		 trabalhadorRepository.deleteById(id);
		
		 return null;
	}
}
