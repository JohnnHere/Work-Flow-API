package com.seachallenge.controller;

import com.seachallenge.model.Trabalhador;
import com.seachallenge.repository.TrabalhadorRepository;
import com.seachallenge.service.TrabalhadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trabalhador")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TrabalhadorController {

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;
    
    @Autowired
    private TrabalhadorService trabalhadorService;


    @GetMapping
    public ResponseEntity<List<Trabalhador>> getAll(){
        return ResponseEntity.ok(trabalhadorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trabalhador> getById(@PathVariable Long id){
        return trabalhadorRepository.findById(id)
                .map(reposta -> ResponseEntity.ok(reposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<Trabalhador>> getByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(trabalhadorRepository.findAllByCpfContainingIgnoreCase(cpf));
    }

    @PostMapping
    public ResponseEntity<Trabalhador> post(@Valid @RequestBody Trabalhador trabalhador){
    	
        return trabalhadorService.cadastrarTrabalhador(trabalhador)
        		.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
    			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        		
    }
    @PutMapping
    public ResponseEntity<Trabalhador> put(@Valid @RequestBody Trabalhador trabalhador){
    	return trabalhadorService.atualizarTrabalhador(trabalhador)
        		.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
    			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trabalhadorService.deletarTrabalhador(id);
    }
}
