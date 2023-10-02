import java.io.IOException;

import DAO.VeterinarioDAO;
import entity.Veterinario;
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

public class VeterinarioController {

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button botaoRetornar;

    @FXML
    private TextField campoEspecializacao;

    @FXML
    private TextField campoCRMV;

    @FXML
    private TextField campoNovoVeterinario;

    @FXML
    private TextField campoTipoServico;

    @FXML
    void fazerCadastro(ActionEvent event) {
        String nomeVeterinario = campoNovoVeterinario.getText();
        String CRMV = campoCRMV.getText();
        String tipoServico = campoTipoServico.getText();
        String especializacao = campoEspecializacao.getText();

        Veterinario veterinario = new Veterinario(nomeVeterinario, CRMV);
        VeterinarioDAO veterinarioDAO = new VeterinarioDAO();



        if(VeterinarioDAO.verificarVeterinario(veterinario.getCRMV()) == false){
            veterinarioDAO.adicionarVeterinario(veterinario, CRMV, tipoServico, especializacao);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso!");
            alert.setHeaderText("Cadastro realizado com sucesso");
            alert.setContentText("Por favor, retorne a tela de gerenciamento.");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Veterinario já cadastrado");
            alert.setContentText("Por favor, verifique o CRMV digitado.");
            alert.showAndWait();
        }
    }

    @FXML
    void retornarTela(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("telaGerenciamento.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
