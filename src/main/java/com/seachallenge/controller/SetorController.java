package com.seachallenge.controller;

import com.seachallenge.model.Setor;
import com.seachallenge.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/setor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SetorController {
	
    @Autowired
    private SetorRepository setorRepository;


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
    public ResponseEntity<Setor> post(@Valid @RequestBody Setor setor){
    	List<Setor> allSetor = setorRepository.findAll();
    	
    	for(Setor setorChecagem: allSetor) {
    		if(setorChecagem.getNome().equals(setor.getNome())) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(setor);
    		}
    	}
    	
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(setorRepository.save(setor));
    }

    @PutMapping
    public ResponseEntity<Setor> put(@Valid @RequestBody Setor setor){
        return setorRepository.findById(setor.getId())
                .map(reposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(setorRepository.save(setor)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Setor> setor = setorRepository.findById(id);

        if(setor.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        setorRepository.deleteById(id);
    }
}
