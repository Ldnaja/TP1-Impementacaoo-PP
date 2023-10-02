package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.conexao;
import entity.Veterinario;

public class VeterinarioDAO {

    public void adicionarVeterinario(Veterinario veterinario, String CRMV, String tipoServico, String especializacaoVet){
        String sql = "INSERT INTO VETERINARIO (nome,CRMV) VALUES (?,?)";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, CRMV);
            stmt.execute();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    veterinario.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o ID do veterinario após a inserção.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Veterinario veterionarioAchado = acharVeterinario(veterinario);

        String sql2 = "INSERT INTO SERVICO (tiposervico, especializacaovet, idveterinario) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql2)) {
            stmt.setString(1, tipoServico);
            stmt.setString(2, especializacaoVet);
            stmt.setInt(3, veterionarioAchado.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Veterinario acharVeterinario(Veterinario veterinario) {
        String sql = "SELECT * FROM VETERINARIO WHERE CRMV = ?";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, veterinario.getCRMV());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    veterinario.setId(rs.getInt("idveterinario"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veterinario;
    }

    public static boolean verificarVeterinario(String CRMV) {
        String sql = "SELECT * FROM VETERINARIO WHERE CRMV = ?";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, CRMV);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Veterinario acharVeterinarioPeloCRMV(String CRMV) {
        String sql = "SELECT * FROM VETERINARIO WHERE CRMV = ?";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, CRMV);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Veterinario veterinario = new Veterinario();
                    veterinario.setId(rs.getInt("idveterinario"));
                    veterinario.setNome(rs.getString("nome"));
                    veterinario.setCRMV(rs.getString("CRMV"));
                    return veterinario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Veterinario> acharVeterinarios(String tipoServico) {
        String sql = "SELECT * FROM VETERINARIO V INNER JOIN SERVICO S ON V.idveterinario = S.idveterinario WHERE S.tiposervico = ?";

        List<Veterinario> lista = new ArrayList<Veterinario>();
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, tipoServico);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Veterinario veterinario = new Veterinario();
                    veterinario.setId(rs.getInt("idveterinario"));
                    veterinario.setNome(rs.getString("nome"));
                    veterinario.setCRMV(rs.getString("CRMV"));
                    lista.add(veterinario);

                }
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Veterinario> listaVeterinarios(){
        List<Veterinario> veterinarios = new ArrayList<>();
        String sql = "SELECT * FROM VETERINARIO";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                Veterinario veterinario = new Veterinario();
                veterinario.setId(resultSet.getInt("idVeterinario"));
                veterinario.setNome(resultSet.getString("nome"));
                veterinario.setCRMV(resultSet.getString("CRMV"));
                veterinarios.add(veterinario);
            }
            return veterinarios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluirVeterinario(String CRMV){
        String sql = "DELETE FROM VETERINARIO WHERE CRMV = ?";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, CRMV);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}