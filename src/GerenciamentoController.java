import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GerenciamentoController {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button botaoCadastrarNovoCliente;

    @FXML
    private Button botaoCadastrarVeterinario;

    @FXML
    private Button botaoConcluirAtendimento;

    @FXML
    private Button botaoGerenciarFuncionarios;

    @FXML
    private Button botaoInformações;

    @FXML
    private Button botaoMarcarAtendimento;

    @FXML
    void informaçõesGerais(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("informacoesGerais.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void cadastrarVeterinario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("cadastroVeterinario.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void casdatrarNovoCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("cadastroClienteAnimal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void concluirAtendimento(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("concluirAtendimento.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gerenciarFuncionarios(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gerenciamentoFuncionarios.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void marcarAtendimento(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("marcarConsulta.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
