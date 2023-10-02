

import DAO.FuncionarioDAO;
import entity.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class LoginController {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button botaoLogin;

    @FXML
    private Button botaoSemCadastro;

    @FXML
    private TextField campoSenha;

    @FXML
    private TextField campoUsuario;

    @FXML
    void fazerCadastro(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("cadastro.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void fazerLogin(ActionEvent event) throws IOException {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();

        Funcionario funcionario = new Funcionario(usuario, senha);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        
        if(funcionarioDAO.verificarFuncionario(funcionario)){
            //System.out.println("Login realizado com sucesso!");
            Parent root = FXMLLoader.load(getClass().getResource("telaGerenciamento.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            //System.out.println("Login ou senha incorretos!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Login ou senha incorretos!");
            alert.setContentText("Por favor, tente novamente.");
            alert.showAndWait();
        }
    }

}
