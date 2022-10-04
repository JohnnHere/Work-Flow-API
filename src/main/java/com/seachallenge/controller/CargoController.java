package com.seachallenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.seachallenge.model.Cargo;
import com.seachallenge.repository.CargoRepository;
import com.seachallenge.service.CargoService;

@RestController
@RequestMapping("/cargo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CargoController {

    @Autowired
    private CargoRepository cargoRepository;
    
    @Autowired
    private CargoService cargoService;


    @GetMapping
    public ResponseEntity<List<Cargo>> getAll(){
        return ResponseEntity.ok(cargoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getById(@PathVariable Long id){
        return cargoRepository.findById(id)
                .map(reposta -> ResponseEntity.ok(reposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Cargo>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(cargoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Cargo> post(@Valid @RequestBody Cargo cargo) throws Exception{
        		
        	return cargoService.cadastrarCargo(cargo)
        			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
        			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        			
    }
    @PutMapping
    public ResponseEntity<Cargo> put(@Valid @RequestBody Cargo cargo){
    	
    	return cargoService.atualizarCargo(cargo)
    			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
    			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

        }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cargoService.deletarCargo(id);
    }
}
