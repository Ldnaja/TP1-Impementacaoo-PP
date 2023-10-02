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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import DAO.ClienteDAO;
import DAO.ConsultaDAO;
import DAO.VeterinarioDAO;
import entity.Cliente;
import entity.Consulta;
import entity.Veterinario;

public class MarcarConsultaController {

    @FXML
    private Button botaoMarcarConsulta;

    @FXML
    private Button botaoPesquisarVeterinario;

    @FXML
    private Button botaoRetornar;

    @FXML
    private TextField campoCPFdono;

    @FXML
    private TextField campoTipoServico;

    @FXML
    private TableColumn<Veterinario, String> colunaVeterinario;

    @FXML
    private TableView<Veterinario> tabelaVeterinarios;

    @FXML
    void clicado(MouseEvent event) {
        int i = tabelaVeterinarios.getSelectionModel().getSelectedIndex();

        Veterinario veterinario = tabelaVeterinarios.getItems().get(i);

        System.out.println(veterinario.getNome());
    }

    @FXML
    void marcarConsulta(ActionEvent event) {
        if (tabelaVeterinarios.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Nenhum veterinário selecionado");
            alert.setContentText("Por favor, selecione um veterinário antes de marcar a consulta.");
            alert.showAndWait();
            return;
        }

        int selectedIndex = tabelaVeterinarios.getSelectionModel().getSelectedIndex();

        Veterinario veterinario = tabelaVeterinarios.getItems().get(selectedIndex);

        String cpfDono = campoCPFdono.getText();
        Cliente cliente = ClienteDAO.acharClientePeloCPF(cpfDono);

        if (cliente == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Cliente não encontrado");
            alert.setContentText("Por favor, verifique o CPF digitado.");
            alert.showAndWait();
            return;
        }

        Consulta consulta = new Consulta(cliente, veterinario);

        ConsultaDAO.inserirConsulta(consulta);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso!");
        alert.setHeaderText("Consulta marcada");
        alert.setContentText("A consulta foi marcada com sucesso.");
        alert.showAndWait();
    }

    @FXML
    void pesquisarVeterinario(ActionEvent event) {
        String cpfDono = campoCPFdono.getText();
        String tipoServico = campoTipoServico.getText();

        ClienteDAO clienteDAO = new ClienteDAO();

        if(ClienteDAO.verificarCliente(cpfDono) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Cliente não encontrado");
            alert.setContentText("Por favor, verifique o CPF digitado.");
            alert.showAndWait();
        }else{
            Cliente cliente = clienteDAO.acharClientePeloCPF(cpfDono);
            List<Veterinario> veterinarios = VeterinarioDAO.acharVeterinarios(tipoServico);

            if(veterinarios.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro!");
                alert.setHeaderText("Veterinário não encontrado");
                alert.setContentText("Por favor, verifique o tipo de serviço digitado.");
                alert.showAndWait();
            }else{

                tabelaVeterinarios.getItems().clear(); // Limpa a tabela antes de adicionar novos dados
                tabelaVeterinarios.getItems().addAll(veterinarios); // Adiciona os dados à tabela

                colunaVeterinario.setCellValueFactory(cellData -> cellData.getValue().nomePropertyVet());
            }
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
