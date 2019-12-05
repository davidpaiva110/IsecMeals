package vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Refeicao;
import modelo.RefeicaoAdmin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GerirEmentaAdminControler {
    PaneOrganizer po;
    private ArrayList<Refeicao> refeicoes;
    @FXML private TableView tabelaref;
    @FXML TableColumn<TableSenhasAdmin, Integer> colID;
    @FXML TableColumn<TableSenhasAdmin, String> colData;
    @FXML TableColumn<TableSenhasAdmin, String> colHorario;
    @FXML TableColumn<TableSenhasAdmin, Button> colAlterar;
    @FXML TableColumn<TableSenhasAdmin, Button> colRemover;

    public GerirEmentaAdminControler(PaneOrganizer po) {
        this.po = po;
    }

    @FXML
    private void handleSair(ActionEvent action) {
        po.getControlador().logout();
        po.setLoginView();
    }

    @FXML
    private void handleVoltar(ActionEvent action) throws IOException {
        po.setMenuAdminVIew();
    }

    @FXML
    private void handleAdicionaRefeicao(ActionEvent action) throws IOException {
        po.setAdicionaRefeicaoView();
    }

    public void initialize() {
        String aux;
        tabelaref.setEditable(false);
        colID.setCellValueFactory(new PropertyValueFactory<>("IdRefeicao"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("Fase"));
        colData.setCellValueFactory(new PropertyValueFactory<>("Data"));
        colAlterar.setCellValueFactory(new PropertyValueFactory<>("btalterar"));
        colRemover.setCellValueFactory(new PropertyValueFactory<>("btcancelar"));
       try {
            refeicoes = po.getControlador().getEmenta();

            for (Refeicao ref : refeicoes) {
                if (ref.getAlmocoJantar() == 0)
                    aux = "Almoco";
                else aux = "Jantar";
                TableRefeicao tab = new TableRefeicao(ref.getIdRefeicao(), aux, ref.getData());
                tab.getBtcancelar().setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {
                    ButtonType btSim=new ButtonType("Sim");
                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Deseja eliminar a refeição nº " + tab.getBtcancelar().getId() + " ?", btSim, new ButtonType("Não"));
                    confirmation.setHeaderText("Eliminar Refeição");
                    confirmation.setTitle("Eliminar");
                    confirmation.showAndWait();
                    if (confirmation.getResult() == btSim) {
                        try {
                            po.getControlador().cancelRefeicao(Integer.parseInt(tab.getBtcancelar().getId()));
                        } catch (Exception e) {
                            Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
                            error.setHeaderText("Erro ao Cancelar a Senha");
                            error.setTitle("Erro");
                            error.showAndWait();
                        }
                        try {
                            po.setGerirEmentaAdminView();
                        } catch (IOException e) {
                            Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
                            error.setHeaderText("Erro ao Atualizar a Vista");
                            error.setTitle("Erro");
                            error.showAndWait();
                        }
                    }
                }
            });

                tabelaref.getItems().add(tab);
            }
        } catch (SQLException e) {
            tabelaref.setPlaceholder(new Label(e.getMessage().toString()));
        }
        if (tabelaref.getItems().size() == 0) tabelaref.setPlaceholder(new Label("Não existem refeicoes a listar"));


        }




}

