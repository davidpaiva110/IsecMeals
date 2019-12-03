package vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Refeicao;
import modelo.Senha;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
    @FXML Label lbSaldo;

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

    public void initialize(){
        try {
            double saldo = po.getControlador().getSaldo();
            DecimalFormat df = new DecimalFormat("0.00");
            String saldoFormatado = df.format(saldo);
            lbSaldo.setText(saldoFormatado + "€");
        } catch (SQLException e) {
            lbSaldo.setText("Indisponível");
        }
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
                        try {
                            Senha senha = po.getControlador().getSenha(Integer.parseInt(tbSenha.getBtAlterar().getId()));
                            Refeicao ref = po.getControlador().getRefeicao(senha.getIdRefeicao());
                            po.setAlterarSenhaView(ref, senha);
                        } catch (SQLException | IOException e) {
                            Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
                            error.setHeaderText("Erro ao Carregar Vista");
                            error.setTitle("Erro");
                            error.showAndWait();
                        }
                    }
                });
                tbSenha.getBtCancelar().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        ButtonType btSim=new ButtonType("Sim");
                        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cancelar a senha de refeição nº " + tbSenha.getBtAlterar().getId() + " ?", btSim, new ButtonType("Não"));
                        confirmation.setHeaderText("Cancelar Senha de Refeição");
                        confirmation.setTitle("Cancelar");
                        confirmation.showAndWait();
                        if (confirmation.getResult() == btSim) {
                            try {
                                po.getControlador().cancelSenha(Integer.parseInt(tbSenha.getBtAlterar().getId()));
                            } catch (Exception e) {
                                Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
                                error.setHeaderText("Erro ao Cancelar a Senha");
                                error.setTitle("Erro");
                                error.showAndWait();
                            }
                            try {
                                po.setGestaoSenhasView();
                            } catch (IOException e) {
                                Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
                                error.setHeaderText("Erro ao Atualizar a Vista");
                                error.setTitle("Erro");
                                error.showAndWait();
                            }
                        }
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
        if (tableSenhas.getItems().size()==0) tableSenhas.setPlaceholder(new Label("Não existem senhas compradas"));
    }
}
