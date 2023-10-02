package entity;

public class Animal {
    private int idanimal;
    private String nome;
    private String categoria;
    private Cliente dono; // Referência ao dono do animal

    public Animal(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public int getId() {
        return idanimal;
    }

    public void setId(int idanimal) {
        this.idanimal = idanimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Getter e Setter para o dono do animal
    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }
}