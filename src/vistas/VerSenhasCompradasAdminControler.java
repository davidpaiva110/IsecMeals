package vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class VerSenhasCompradasAdminControler {
    PaneOrganizer po;
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
}