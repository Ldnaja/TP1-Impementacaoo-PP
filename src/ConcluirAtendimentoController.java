import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import entity.Consulta;
import DAO.ConsultaDAO;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ConcluirAtendimentoController{

    @FXML
    private Button botaoAtualizarTabela;

    @FXML
    private Button botaoConcluirAtendimento;

    @FXML
    private Button botaoRetornar;

    @FXML
    private TableColumn<Consulta, String> colunaCRMVVet;

    @FXML
    private TableView<Consulta> tabelaGeral;

    @FXML
    void atualizarTabela(ActionEvent event) {
        List<Consulta> consultas = ConsultaDAO.acharConsultas();
        tabelaGeral.getItems().clear();
        tabelaGeral.getItems().addAll(consultas);

        colunaCRMVVet.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedico().getNome()));
    }

    @FXML
    void clicado(MouseEvent event) {
        int i = tabelaGeral.getSelectionModel().getSelectedIndex();

        Consulta consulta = tabelaGeral.getItems().get(i);

        System.out.println(consulta.getMedico().getCRMV());
    }

    @FXML
    void concluirAtendimento(ActionEvent event) throws SQLException {
        int i = tabelaGeral.getSelectionModel().getSelectedIndex();

        Consulta consulta = tabelaGeral.getItems().get(i);

        ConsultaDAO.removerAtendimento(consulta);;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Concluir Atendimento");
        alert.setHeaderText("Atendimento concluido com sucesso!");
        alert.showAndWait();

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