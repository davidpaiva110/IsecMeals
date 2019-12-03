package vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Senha;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GerirSenhasController {

    PaneOrganizer po;
    private ArrayList<Senha> senhas;
    @FXML private TableView tableSenhas;
    @FXML TableColumn<TableSenha, Integer> colNumero;
    @FXML TableColumn<TableSenha, String> colPrato;
    @FXML TableColumn<TableSenha, String> colSobremesa;
    @FXML TableColumn<TableSenha, Double> colPreco;
    @FXML TableColumn<TableSenha, Button> colAlterar;
    @FXML TableColumn<TableSenha, Button> colCancelar;

    public GerirSenhasController(PaneOrganizer paneOrganizer) {
        po=paneOrganizer;
    }

    @FXML
    private void handleSair(ActionEvent action){
        po.getControlador().logout();
        po.setLoginView();
    }

    @FXML
    private void handleVoltar(ActionEvent action) throws IOException {
        po.setMenuUserView();
    }

    public void initialize() {
        tableSenhas.setEditable(false);
        colNumero.setCellValueFactory(new PropertyValueFactory<>("IdSenha"));
        colPrato.setCellValueFactory(new PropertyValueFactory<>("Prato"));
        colSobremesa.setCellValueFactory(new PropertyValueFactory<>("Sobremesa"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("Preco"));
        colAlterar.setCellValueFactory(new PropertyValueFactory<>("BtAlterar"));
        colCancelar.setCellValueFactory(new PropertyValueFactory<>("BtCancelar"));

        try {
            senhas = po.getControlador().getSenhasCompradas();
            for (Senha senha : senhas) {
                TableSenha tbSenha=new TableSenha(senha.getIdSenha(), senha.getPrato(), senha.getSombremesa(), senha.getPreco(), senha.getIdRefeicao());
                tbSenha.getBtAlterar().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println("Alterar" + tbSenha.getBtAlterar().getId());
                    }
                });
                tbSenha.getBtCancelar().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println("Cancelar " + tbSenha.getBtAlterar().getId());
                    }
                });
                if(!po.getControlador().hasMoreThan48Hours(tbSenha.getIdRefeicao())){ //Verificação das 48 horas de antecedência
                    tbSenha.noBtCancelar();
                    tbSenha.noBtAlterar();
                }
                tableSenhas.getItems().add(tbSenha);

            }
        } catch (SQLException e) {
            tableSenhas.setPlaceholder(new Label(e.getMessage().toString()));
        }
    }
}
