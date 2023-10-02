package entity;

public class Servico {
    private int idservico;
    private String tiposervico;
    private String especializacao;
    private Veterinario medico; // Referência ao medico

    public Servico(String tiposervico, String especializacao) {
        this.tiposervico = tiposervico;
        this.especializacao = especializacao;
    }

    public int getId() {
        return idservico;
    }

    public void setId(int idservico) {
        this.idservico = idservico;
    }

    public String gettiposervico() {
        return tiposervico;
    }

    public void settiposervico(String tiposervico) {
        this.tiposervico = tiposervico;
    }

    public String getespecializacao() {
        return especializacao;
    }

    public void setespecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    // Getter e Setter para o medico do animal
    public Veterinario getMedico() {
        return medico;
    }

    public void setMedico(Veterinario medico) {
        this.medico = medico;
    }
}