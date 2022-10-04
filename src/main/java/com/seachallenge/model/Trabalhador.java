package com.seachallenge.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_trabalhador")
public class Trabalhador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Gênero é uma informação obrigatória.")
    @Size(min = 3, max = 50)
    private String genero;

    @NotBlank(message = "Turno é uma informação obrigatória.")
    @Size(min = 3, max = 50)
    private String turno;

    @NotBlank(message = "Telefone é uma informação obrigatória.")
    @Size(min = 3, max = 50)
    private String telefone;

    @NotBlank(message = "CPF é uma informação obrigatória.")
    @Size(min = 11, max = 14)
    private String cpf;

    @NotBlank(message = "Escolaridade é uma informação obrigatória.")
    @Size(min = 3, max = 50)
    private String escolaridade;

    @NotBlank(message = "Nome é uma informação obrigatória.")
    @Size(min = 3, max = 50)
    private String nome;

    @NotBlank(message = "Salário é uma informação obrigatória.")
    @Size(min = 3, max = 50)
    private String salario;

    @NotBlank(message = "Idade é uma informação obrigatória.")
    @Size(min = 1, max = 10)
    private String idade;

    @ManyToOne
    @JsonIgnoreProperties("trabalhador")
    private Cargo cargo;

    @ManyToOne
    @JsonIgnoreProperties("trabalhador")
    private Setor setor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }


}
