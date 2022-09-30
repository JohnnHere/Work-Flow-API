package com.seachallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "País é uma informação obrigatória.")
    @Size(min = 3, max = 50)
    private String pais;

    @NotBlank(message = "Estado é uma informação obrigatória.")
    @Size(min = 3, max = 50)
    private String estado;

    @NotBlank(message = "Cidade é uma informação obrigatória.")
    @Size(min = 3, max = 50)
    private String cidade;

    @NotBlank(message = "Bairro é uma informação obrigatória.")
    @Size(min = 3, max = 50)
    private String bairro;

    @NotBlank(message = "Rua é uma informação obrigatória.")
    @Size(min = 3, max = 50)
    private String rua;

    @NotBlank(message = "Número é uma informação obrigatória.")
    @Size(min = 1, max = 20)
    private Number numero;

    @NotBlank(message = "CEP é uma informação obrigatória.")
    @Size(min = 5, max = 15)
    private String cep;

    @Size(min = 1, max = 100)
    private String complemento;

    @OneToMany
    @JsonIgnoreProperties("endereco")
    private List<Trabalhador> trabalhador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Number getNumero() {
        return numero;
    }

    public void setNumero(Number numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public List<Trabalhador> getTrabalhador() {
        return trabalhador;
    }

    public void setTrabalhador(List<Trabalhador> trabalhador) {
        this.trabalhador = trabalhador;
    }
}
