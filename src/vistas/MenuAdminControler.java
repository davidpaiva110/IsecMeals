package vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MenuAdminControler {
    PaneOrganizer po;
    public MenuAdminControler(PaneOrganizer po) {
        this.po = po;
    }

    @FXML
    private void handleGerirEmenta(ActionEvent action) throws IOException { po.setGerirEmentaAdminView(); }

    @FXML
    private void handleGerirUtilizadores(ActionEvent action) throws IOException {
        po.setGerirUtilizadoresAdminView();
    }

    @FXML
    private void handleSenhasCompradas(ActionEvent action) throws IOException {
        po.setSenhaaCompradasAdminView();
    }

    @FXML
    private void handleSair(ActionEvent action){
        po.getControlador().logout();
        po.setLoginView();
    }
}
