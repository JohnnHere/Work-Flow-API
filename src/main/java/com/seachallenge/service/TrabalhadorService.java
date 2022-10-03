package com.seachallenge.service;

import com.seachallenge.model.Trabalhador;
import com.seachallenge.repository.TrabalhadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class TrabalhadorService {

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    /**
     *  Cadastrar Usuário
     *
     *  Checa se o usuário já existe no Banco de Dados através do método findByUsuario,
     *  porquê não pode existir 2 usuários com o mesmo email.
     *  Se não existir retorna um Optional vazio.
     *
     *  isPresent() -> Se um valor estiver presente retorna true, caso contrário
     *  retorna false.
     *
     *  empty -> Retorna uma instância de Optional vazia.
     */
    public Optional<Trabalhador> cadastrarTrabalhador(Trabalhador trabalhador) {

        if (trabalhadorRepository.findByCpf(trabalhador.getCpf()).isPresent())
            return Optional.empty();

        return Optional.of(trabalhadorRepository.save(trabalhador));

    }

    /**
     *  Atualizar Usuário
     *
     *  Checa se o usuário já existe no Banco de Dados através do método findById,
     *  porquê não é possíve atualizar 1 usuário inexistente.
     *  Se não existir retorna um Optional vazio.
     *
     *  isPresent() -> Se um valor estiver presente retorna true, caso contrário
     *  retorna false.
     *
     */
    public Optional<Trabalhador> atualizarTrabalhador(Trabalhador trabalhador) {

        if(trabalhadorRepository.findById(trabalhador.getId()).isPresent()) {


            Optional<Trabalhador> buscaTrabalhador = trabalhadorRepository.findByCpf(trabalhador.getCpf());


            if ( (buscaTrabalhador.isPresent()) && ( buscaTrabalhador.get().getId() != trabalhador.getId()))
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Trabalhador já existe!", null);


            return Optional.ofNullable(trabalhadorRepository.save(trabalhador));

        }


        return Optional.empty();

    }

}
