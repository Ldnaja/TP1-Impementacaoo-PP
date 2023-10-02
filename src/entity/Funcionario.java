package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Funcionario {
    private int idFuncionario;
    private final StringProperty login;
    private final StringProperty senha;

    public Funcionario(String login, String senha) {
        this.login = new SimpleStringProperty(login);
        this.senha = new SimpleStringProperty(senha);
    }

    // Getter e Setter para o campo 'idFuncionario'
    public int getId() {
        return idFuncionario;
    }

    public void setId(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    // Getter e Setter para o campo 'login'
    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    // Getter e Setter para o campo 'senha'
    public String getSenha() {
        return senha.get();
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
    }

    // Propriedades observáveis para 'login'
    public StringProperty loginProperty() {
        return login;
    }

    // Propriedades observáveis para 'senha'
    public StringProperty senhaProperty() {
        return senha;
    }
}
