package com.seachallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é uma informação obrigatória.")
    @Size(min = 3, max = 50)
    private String nome;

    @Size(min = 3, max = 2000)
    private String descricao;

    @OneToMany
    @JsonIgnoreProperties("cargo")
    private List<Trabalhador> trabalhador;

    @ManyToOne
    @JsonIgnoreProperties("cargo")
    private Setor setor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Trabalhador> getTrabalhador() {
        return trabalhador;
    }

    public void setTrabalhador(List<Trabalhador> trabalhador) {
        this.trabalhador = trabalhador;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}