package vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.RefeicaoAdmin;
import modelo.Senha;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;

public class VerSenhasCompradasAdminControler {
    PaneOrganizer po;
    private ArrayList<RefeicaoAdmin> refeicoes;
    @FXML private TableView tabelarefadmin;
    @FXML TableColumn<TableSenhasAdmin,Integer > colID;
    @FXML TableColumn<TableSenhasAdmin, DateFormat> colData;
    @FXML TableColumn<TableSenhasAdmin, String> colHorario;
    @FXML TableColumn<TableSenhasAdmin,Integer > colQuantPeixe;
    @FXML TableColumn<TableSenhasAdmin, Integer> colQuantCarne;

    public VerSenhasCompradasAdminControler(PaneOrganizer po){
        this.po=po;
    };
    @FXML
    private void handleSair(ActionEvent action){
        po.getControlador().logout();
        po.setLoginView();
    }
    @FXML
    private void handleVoltar(ActionEvent action) throws IOException {
        po.setMenuAdminVIew();
    }
    public void initialize(){
        tabelarefadmin.setEditable(false);
        colID.setCellValueFactory(new PropertyValueFactory<>("IdRefeicao"));
        colData.setCellValueFactory(new PropertyValueFactory<>("Fase"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("Data"));
        colQuantPeixe.setCellValueFactory(new PropertyValueFactory<>("QuantPeixe"));
        colQuantCarne.setCellValueFactory(new PropertyValueFactory<>("QuantCarne"));
        try {
 refeicoes = po.getControlador().getSenhasCompradasAdmin();
            for ( RefeicaoAdmin ref : refeicoes) {
                TableSenhasAdmin tab =new TableSenhasAdmin(ref.getIdRefeicao(), ref.getFase(), ref.getData(),ref.getQuantPeixe(),ref.getQuantCarne());
                tabelarefadmin.getItems().add(tab);

            }


        }catch (SQLException e) {
                tabelarefadmin.setPlaceholder(new Label(e.getMessage().toString()));
            }
            if (tabelarefadmin.getItems().size()==0) tabelarefadmin.setPlaceholder(new Label("Não existem senhas compradas"));
        }


}
