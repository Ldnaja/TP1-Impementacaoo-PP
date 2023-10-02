package entity;

public class Consulta {
    private int idconsulta;
    private Cliente cpf; // Referência ao dono do animal
    private Veterinario crmv; // Referência ao medico

    public Consulta(Cliente cpf, Veterinario crmv) {
        this.cpf = cpf;
        this.crmv = crmv;
    }

    public int getId() {
        return idconsulta;
    }

    public void setId(int idconsulta) {
        this.idconsulta = idconsulta;
    }

    // Getter e Setter para o dono do animal
    public Cliente getDono() {
        return cpf;
    }

    public void setDono(Cliente cpf) {
        this.cpf = cpf;
    }

    // Getter e Setter para o medico do animal
    public Veterinario getMedico() {
        return crmv;
    }

    public void setMedico(Veterinario crmv) {
        this.crmv = crmv;
    }
    
}
