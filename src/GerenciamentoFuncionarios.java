import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class GerenciamentoFuncionarios {

    @FXML
    private Button botaoRetornar;

    @FXML
    private Button botaoAlterarDados;

    @FXML
    private Button botaoAtualizarLista;

    @FXML
    private Button botaoExcluirFuncionario;

    @FXML
    private TextField campoSenha;

    @FXML
    private TableColumn<Funcionario, String> colunaLogin;

    @FXML
    private TableView<Funcionario> tabelaFuncionarios;

    @FXML
    void alterarDados(ActionEvent event) {
        if(tabelaFuncionarios.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum funcionário selecionado");
            alert.setContentText("Selecione um funcionário para alterar os dados");
            alert.showAndWait();
        }

        int selectedIndex = tabelaFuncionarios.getSelectionModel().getSelectedIndex();

        Funcionario funcionario = tabelaFuncionarios.getItems().get(selectedIndex);

        funcionario.setSenha(campoSenha.getText());

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarioDAO.alterarSenha(funcionario);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Dados alterados com sucesso");
        alert.setContentText("Os dados do funcionário foram alterados com sucesso");
        alert.showAndWait();
    }

    @FXML
    void atualizarLista(ActionEvent event) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        List<String> logins = funcionarioDAO.listarLoginsFuncionarios();

        // Atualizar os dados da tabela com a nova lista de logins
        ObservableList<Funcionario> dadosTabela = FXCollections.observableArrayList();

        // Preencher a tabela diretamente com os logins obtidos
        for (String login : logins) {
            dadosTabela.add(new Funcionario(login, ""));
        }

        // Configurar a coluna na tabela (se não estiver configurada)
        if (colunaLogin.getCellValueFactory() == null) {
            colunaLogin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLogin()));
        }

        tabelaFuncionarios.setItems(dadosTabela);
    }

    @FXML
    void clicado(MouseEvent event) {
         int i = tabelaFuncionarios.getSelectionModel().getSelectedIndex();

        Funcionario funcionario = (Funcionario) tabelaFuncionarios.getItems().get(i);

        System.out.println(funcionario.getLogin());
    }

    @FXML
    void excluirFuncionario(ActionEvent event) {
        // Verifica se algum funcionário está selecionado na tabela
        int selectedIndex = tabelaFuncionarios.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            // Nenhum funcionário selecionado, exibe uma mensagem de alerta
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um funcionário para excluir.");
            alert.showAndWait();
            return;
        }

        // Obtém o funcionário selecionado
        Funcionario funcionarioSelecionado = tabelaFuncionarios.getItems().get(selectedIndex);

        // Confirmação do usuário para excluir
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmação");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Tem certeza que deseja excluir o funcionário selecionado?");
        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Exclui o funcionário do banco de dados
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.excluirFuncionario(funcionarioSelecionado.getLogin());

            // Remove o funcionário da tabela
            tabelaFuncionarios.getItems().remove(selectedIndex);
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
