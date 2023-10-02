package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.conexao;
import entity.Funcionario;

public class FuncionarioDAO {

    public void cadastrarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO FUNCIONARIO (login, senha) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, funcionario.getLogin());
            stmt.setString(2, funcionario.getSenha());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verificarFuncionario(Funcionario funcionario) {
        String sql = "SELECT * FROM FUNCIONARIO WHERE login = ? AND senha = ?";
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, funcionario.getLogin());
            stmt.setString(2, funcionario.getSenha());

            try (ResultSet resultSet = stmt.executeQuery()) {
                return resultSet.next(); // Retorna true se o funcionário existe no banco de dados
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de exceção ou se o funcionário não existe
        }
    }

    public boolean verificaFuncionarioExistente(Funcionario funcionario) {
        String sql = "SELECT * FROM FUNCIONARIO WHERE login = ?";
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, funcionario.getLogin());

            try (ResultSet resultSet = stmt.executeQuery()) {
                return resultSet.next(); // Retorna true se o funcionário existe no banco de dados
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de exceção ou se o funcionário não existe
        }
    }

    public boolean mostrarFuncionario(Funcionario funcionario) {
        String sql = "SELECT * FROM FUNCIONARIO WHERE login = ?";
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, funcionario.getLogin());

            try (ResultSet resultSet = stmt.executeQuery()) {
                return resultSet.next(); // Retorna true se o funcionário existe no banco de dados
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de exceção ou se o funcionário não existe
        }
    }

    public void excluirFuncionario(String login) {
        String sqlExcluir = "DELETE FROM FUNCIONARIO WHERE login = ?";
        try (PreparedStatement stmtExcluir = conexao.getConexao().prepareStatement(sqlExcluir)) {
            stmtExcluir.setString(1, login);
            stmtExcluir.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarSenha(Funcionario funcionario) {
        String sql = "UPDATE FUNCIONARIO SET senha = ? WHERE login = ?";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, funcionario.getSenha());
            stmt.setString(2, funcionario.getLogin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> listarLoginsFuncionarios() {
        List<String> logins = new ArrayList<>();
        String sql = "SELECT login FROM FUNCIONARIO";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                String login = resultSet.getString("login");
                logins.add(login);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logins;
    }
}
