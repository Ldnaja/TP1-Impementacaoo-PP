package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class Cliente {
    private int idcliente;
    private String nome;
    private String cpf;
    private List<Animal> animais; // Relacionamento 1:n com Animal

    public Cliente() {
        this.nome = "";
        this.cpf = "";
    }
    
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // Getter para o campo 'idcliente'
    public int getId() {
        return idcliente;
    }

    // Setter para o campo 'idcliente'
    public void setId(int idcliente) {
        this.idcliente = idcliente;
    }

    // Getter para o campo 'nome'
    public String getNome() {
        return nome;
    }

    // Setter para o campo 'nome'
    public void setNome(String nome) {
        this.nome = nome;
    }

     public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Getter para a lista de animais
    public List<Animal> getAnimais() {
        return animais;
    }

    // Setter para a lista de animais
    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public StringProperty nomePropertyCli() {
        return new SimpleStringProperty(nome);
    }
}