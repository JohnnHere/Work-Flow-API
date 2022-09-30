package com.seachallenge.controller;

import com.seachallenge.model.Cargo;
import com.seachallenge.repository.CargoRepository;
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
@RequestMapping("/cargo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CargoController {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private SetorRepository setorRepository;


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
    public ResponseEntity<Cargo> post(@Valid @RequestBody Cargo cargo){
        if(setorRepository.existsById(cargo.getSetor().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cargoRepository.save(cargo));

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
        Optional<Cargo> cargo = cargoRepository.findById(id);

        if(cargo.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        cargoRepository.deleteById(id);
    }
}
