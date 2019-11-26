package vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuAdminControler {
    PaneOrganizer po;
    public MenuAdminControler(PaneOrganizer po) {
        this.po = po;
    }

    @FXML
    private void handleGerirEmenta(ActionEvent action){ po.setGerirEmentaAdminView(); }
/*
    @FXML
    private void handleGerirUtilizadores(ActionEvent action){
        po.setGerirUtilizadoresAdminView();
    }

    @FXML
    private void handleSenhaaCompradas(ActionEvent action){
        po.setSenhaaCompradasAdminView();
    }
*/
    @FXML
    private void handleSair(ActionEvent action){
        po.getControlador().logout();
        po.setLoginView();
    }
}