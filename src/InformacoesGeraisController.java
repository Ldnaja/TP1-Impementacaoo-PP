import java.io.IOException;
import java.util.List;
import java.util.Optional;


import DAO.ClienteDAO;
import DAO.VeterinarioDAO;
import entity.Cliente;
import entity.Veterinario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InformacoesGeraisController {

    @FXML
    private Button botaoAtualizarListaClientes;

    @FXML
    private Button botaoAtualizarListaVeterinarios;

    @FXML
    private Button botaoExcluirCliente;

    @FXML
    private Button botaoExcluirVeterinario;

    @FXML
    private Button botaoRetornar;

    @FXML
    private TableColumn<Cliente, String> colunaCliente;

    @FXML
    private TableColumn<Veterinario, String> colunaVeterinarios;

    @FXML
    private TableView<Cliente> tabelaClientes;

    @FXML
    private TableView<Veterinario> tabelaVeterinarios;

    @FXML
    void atualizarListaClientes(ActionEvent event) {
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listarClientes();

        ObservableList<Cliente> observableListClientes = FXCollections.observableArrayList(clientes);

        colunaCliente.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getNome()));

        tabelaClientes.setItems(observableListClientes);
    }

    @FXML
    void atualizarListaVeterinarios(ActionEvent event) {
        VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
        List<Veterinario> veterinarios = VeterinarioDAO.listaVeterinarios();

        ObservableList<Veterinario> observableListVeterinarios = FXCollections.observableArrayList(veterinarios);

        colunaVeterinarios.setCellValueFactory(veterinario -> new SimpleStringProperty(veterinario.getValue().getNome()));

        tabelaVeterinarios.setItems(observableListVeterinarios);

    }

    @FXML
    void clicadoCliente(MouseEvent event) {
        int i = tabelaClientes.getSelectionModel().getSelectedIndex();

        Cliente cliente = (Cliente) tabelaClientes.getItems().get(i);

        System.out.println(cliente.getNome());
    }

    @FXML
    void clicadoVeterinario(MouseEvent event) {
        int i = tabelaVeterinarios.getSelectionModel().getSelectedIndex();

        Veterinario veterinario = (Veterinario) tabelaVeterinarios.getItems().get(i);

        System.out.println(veterinario.getNome());
    }

    @FXML
    void excluirCliente(ActionEvent event) {
        int selectedIndex = tabelaClientes.getSelectionModel().getSelectedIndex();
        if(selectedIndex == -1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Por favor, selecione um cliente para excluir.");
            alert.showAndWait();
            return;
        }

        Cliente clienteSelecionado = tabelaClientes.getItems().get(selectedIndex);

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmação");
        confirmAlert.setHeaderText("Excluir cliente");
        confirmAlert.setContentText("Tem certeza que deseja excluir o cliente " + clienteSelecionado.getNome() + "?");
        Optional<ButtonType> result = confirmAlert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.excluirCliente(clienteSelecionado.getCpf());
        
            tabelaClientes.getItems().remove(selectedIndex);
        }

    }

    @FXML
    void excluirVeterinario(ActionEvent event) {
        int selectedIndex = tabelaVeterinarios.getSelectionModel().getSelectedIndex(); 
        if(selectedIndex == -1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Por favor, selecione um veterinario para excluir.");
            alert.showAndWait();
            return;
        }

        Veterinario veterinarioSelecionado = tabelaVeterinarios.getItems().get(selectedIndex);

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmação");
        confirmAlert.setHeaderText("Excluir veterinario");
        confirmAlert.setContentText("Tem certeza que deseja excluir o veterinario " + veterinarioSelecionado.getNome() + "?");
        Optional<ButtonType> result = confirmAlert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
            veterinarioDAO.excluirVeterinario(veterinarioSelecionado.getCRMV());
        
            tabelaVeterinarios.getItems().remove(selectedIndex);
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
