package com.seachallenge.controller;

import com.seachallenge.model.Cargo;
import com.seachallenge.model.Endereco;
import com.seachallenge.repository.EnderecoRepository;
import com.seachallenge.repository.TrabalhadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<List<Endereco>> getAll(){
        return ResponseEntity.ok(enderecoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getById(@PathVariable Long id){
        return enderecoRepository.findById(id)
                .map(reposta -> ResponseEntity.ok(reposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<List<Endereco>> getByCep(@PathVariable String cep){
        return ResponseEntity.ok(enderecoRepository.findAllByCepContainingIgnoreCase(cep));
    }

    @PostMapping
    public ResponseEntity<Endereco> post(@Valid @RequestBody Endereco endereco){
        if(trabalhadorRepository.existsById(endereco.getTrabalhador().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(enderecoRepository.save(endereco));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PutMapping
    public ResponseEntity<Cargo> put(@Valid @RequestBody Cargo cargo){
        if (cargoRepository.existsById(cargo.getId())){

            if(setorRepository.existsById(cargo.getSetor().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(cargoRepository.save(cargo));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);

        if(endereco.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        enderecoRepository.deleteById(id);
    }
}
