package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.conexao;
import entity.Cliente;
import entity.Consulta;
import entity.Veterinario;

public class ConsultaDAO {

    public static void inserirConsulta(Consulta consulta) {
        String sql = "INSERT INTO Consulta (cpf_cliente, crmv_veterinario) VALUES (?, ?)";
        
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, consulta.getDono().getCpf());
            stmt.setString(2, consulta.getMedico().getCRMV());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verificaConsulta(Consulta consulta) {
        String sql = "SELECT * FROM Consulta WHERE cpf_cliente = ? AND crmv_veterinario = ?";
        
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, consulta.getDono().getCpf());
            stmt.setString(2, consulta.getMedico().getCRMV());
            
            // Executa a consulta usando executeQuery() em vez de executeUpdate()
            try (ResultSet resultSet = stmt.executeQuery()) {
                // Verifica se há alguma linha no resultado (se a consulta foi encontrada)
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Em caso de exceção, retorna false
            return false;
        }
    }

    public static Consulta acharConsultaPeloId(int id) {
        Consulta consulta = null;
    
        String sql = "SELECT * FROM Consulta WHERE idconsulta = ?";
    
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setInt(1, id);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Obter dados do resultado e criar instância de Consulta
                    // Você precisa adaptar isso com base na nova estrutura da tabela
                    String cpf_cliente = rs.getString("cpf_cliente");
                    String crmv_veterinario = rs.getString("crmv_veterinario");

                    // Buscar Cliente e Veterinario associados
                    Cliente cliente = ClienteDAO.acharClientePeloCPF(cpf_cliente);
                    Veterinario veterinario = VeterinarioDAO.acharVeterinarioPeloCRMV(crmv_veterinario);
                        
                    // Criar instância de Consulta e adicioná-la à lista
                    consulta = new Consulta(cliente, veterinario);
                    consulta.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return consulta;
    }

    public static List<Consulta> acharConsultas() {
        List<Consulta> consultas = new ArrayList<>();
    
        String sql = "SELECT * FROM Consulta";
    
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Obter dados do resultado e criar instância de Consulta
                    // Você precisa adaptar isso com base na nova estrutura da tabela
                    int idconsulta = rs.getInt("idconsulta");
                    String cpf_cliente = rs.getString("cpf_cliente");
                    String crmv_veterinario = rs.getString("crmv_veterinario");

                    // Buscar Cliente e Veterinario associados
                    Cliente cliente = ClienteDAO.acharClientePeloCPF(cpf_cliente);
                    Veterinario veterinario = VeterinarioDAO.acharVeterinarioPeloCRMV(crmv_veterinario);
                        
                    // Criar instância de Consulta e adicioná-la à lista
                    Consulta consulta = new Consulta(cliente, veterinario);
                    consulta.setId(idconsulta);
                    consultas.add(consulta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return consultas;
    }

    public static void removerAtendimento(Consulta consulta) throws SQLException {
        String sql = "DELETE FROM Consulta WHERE idconsulta = ?";
        
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setInt(1, consulta.getId());
            
            stmt.executeUpdate();
        }
    }

    public static void atualizarConsulta(Consulta consulta) throws SQLException {
        String sql = "UPDATE Consulta SET cpf_cliente = ?, crmv_veterinario = ? WHERE idconsulta = ?";
        
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, consulta.getDono().getCpf());
            stmt.setString(2, consulta.getMedico().getCRMV());
            stmt.setInt(3, consulta.getId());
            
            stmt.executeUpdate();
        }
    }
}