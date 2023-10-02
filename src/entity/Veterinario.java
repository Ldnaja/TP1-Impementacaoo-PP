package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Veterinario {
    private int idveterinario;
    private String nome;
    private String CRMV;
    private List<Servico> servicos;  // Lista de serviços associados a este veterinário

    public Veterinario() {
        this.nome = "";
        this.CRMV = "";
        this.servicos = new ArrayList<>();
    }

    public Veterinario(String nome, String CRMV) {
        this.nome = nome;
        this.CRMV = CRMV;
        this.servicos = new ArrayList<>();
    }

    public int getId() {
        return idveterinario;
    }

    public void setId(int idveterinario) {
        this.idveterinario = idveterinario;
    }

    public String getCRMV() {
        return CRMV;
    }

    public void setCRMV(String CRMV) {
        this.CRMV = CRMV;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) { this.servicos = servicos; }

    public void adicionarServico(Servico servico) {
        this.servicos.add(servico);
    }

    public StringProperty nomePropertyVet() {
        return new SimpleStringProperty(nome);
    }
}



