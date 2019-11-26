package vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Senha;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GerirSenhasController {

    PaneOrganizer po;
    private ArrayList<Senha> senhas;
    @FXML private TableView tableSenhas;

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
        try {
            senhas = po.getControlador().getSenhasCompradas();
            for (Senha senha : senhas) {
                //tableSenhas.getItems().add(senha);
            }
        } catch (SQLException e) {
            tableSenhas.setPlaceholder(new Label(e.getMessage().toString()));
        }
    }
}
