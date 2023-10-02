import java.io.IOException;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroController {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button cadastrar;

    @FXML
    private TextField novoFuncionario;

    @FXML
    private PasswordField senhaFuncionario;

    @FXML
    private Button retornarLogin;

    @FXML
    void fazerCadastro(ActionEvent event) {

        String usuario = novoFuncionario.getText();
        String senha = senhaFuncionario.getText();

        Funcionario funcionario = new Funcionario(usuario, senha);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        //verificar o cadastro do funcionario no banco de dados
        if (funcionarioDAO.verificarFuncionario(funcionario)) {
            //System.out.println("Funcionario já cadastrado!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Funcionario já cadastrado!");
            alert.setContentText("Por favor, retorne a tela de login!");
            alert.showAndWait();
        } else {
            funcionarioDAO.cadastrarFuncionario(funcionario);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Funcionario cadastrado com sucesso!");
            alert.setContentText("Por favor, retorne a tela de login!");
            alert.showAndWait();
            //System.out.println("Funcionario cadastrado com sucesso!");
        }
    }

    @FXML
    void retornar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
