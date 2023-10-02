
import java.io.IOException;
import DAO.ClienteDAO;
import entity.Cliente;
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

public class CadastroClienteAnimalController {

    @FXML
    private TextField campoNomeDono;

    @FXML
    private TextField campoCPF;
    
    @FXML
    private TextField campoNomeAnimal;

    @FXML
    private TextField campoCategoriaAnimal;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button botaoRetornar;


    @FXML
    void cadastrar(ActionEvent event) {
        String nomeDono = campoNomeDono.getText();
        String cpf = campoCPF.getText();
        String nomeAnimal = campoNomeAnimal.getText();
        String categoriaAnimal = campoCategoriaAnimal.getText();

        Cliente cliente = new Cliente(nomeDono,cpf);
        ClienteDAO clienteDAO = new ClienteDAO();

        System.out.println(ClienteDAO.verificarCliente(cliente.getCpf()));

        if(ClienteDAO.verificarCliente(cliente.getCpf()) == false){
            //System.out.println("To no adicionar");
            clienteDAO.adicionarCliente(cliente, cpf, nomeAnimal, categoriaAnimal);
            //System.out.println("Cliente cadastrado com sucesso!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso!");
            alert.setHeaderText("Cadastro realizado com sucesso");
            alert.setContentText("Por favor, retorne a tela de gerenciamento.");
            alert.showAndWait();
        }else{
            //System.out.println("to no ja cadastrado");
            //System.out.println("Cliente já cadastrado!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Cliente já cadastrado!");
            alert.setContentText("Por favor, retorne a tela de gerenciamento.");
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