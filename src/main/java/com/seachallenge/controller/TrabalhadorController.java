package com.seachallenge.controller;

import com.seachallenge.model.Trabalhador;
import com.seachallenge.repository.CargoRepository;
import com.seachallenge.repository.SetorRepository;
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
@RequestMapping("/trabalhador")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TrabalhadorController {

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private SetorRepository setorRepository;


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
        if(cargoRepository.existsById(trabalhador.getCargo().getId()) && setorRepository.existsById(trabalhador.getSetor().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(trabalhadorRepository.save(trabalhador));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PutMapping
    public ResponseEntity<Trabalhador> put(@Valid @RequestBody Trabalhador trabalhador){
        if (trabalhadorRepository.existsById(trabalhador.getId())){

            if (cargoRepository.existsById(trabalhador.getCargo().getId()) && setorRepository.existsById(trabalhador.getSetor().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(trabalhadorRepository.save(trabalhador));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Trabalhador> trabalhador = trabalhadorRepository.findById(id);

        if(trabalhador.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        trabalhadorRepository.deleteById(id);
    }
}
