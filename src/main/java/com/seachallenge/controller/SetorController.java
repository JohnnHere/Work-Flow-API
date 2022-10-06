package com.seachallenge.controller;

import com.seachallenge.model.Setor;
import com.seachallenge.repository.SetorRepository;
import com.seachallenge.service.SetorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/setor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SetorController {
	
    @Autowired
    private SetorRepository setorRepository;
    
    @Autowired
    private SetorService setorService;


    @GetMapping
    public ResponseEntity<List<Setor>> getAll(){
        return ResponseEntity.ok(setorRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Setor> getById(@PathVariable Long id){
        return setorRepository.findById(id)
                .map(reposta -> ResponseEntity.ok(reposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Setor>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(setorRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Setor> post(@Valid @RequestBody Setor setor) throws Exception{
    	
    	return setorService.cadastrarSetor(setor)
        		.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
    			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    	
    	}
    	

    @PutMapping
    public ResponseEntity<Setor> put(@Valid @RequestBody Setor setor){
    	
    	return setorService.atualizarSetor(setor)
        		.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
    			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    	
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    	
        setorService.deletarSetor(id);
        
    }
    
}
