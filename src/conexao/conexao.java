package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao{
    private static final String URL = "jdbc:mysql://localhost:3306/clinica";
    private static final String user = "root";
    private static final String password = "1234";

    private static Connection conexao;

    public static Connection getConexao(){
        if(conexao == null){
            try{
                conexao = DriverManager.getConnection(URL, user, password);
            }catch(SQLException e){
                System.out.println("Erro ao conectar com o banco de dados");
            }
        }
        return conexao;
    }
}