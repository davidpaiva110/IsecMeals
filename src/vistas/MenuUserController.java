package vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuUserController {

    PaneOrganizer po;

    public MenuUserController(PaneOrganizer po) {
        this.po = po;
    }

    @FXML
    private void handleConsultarEmenta(ActionEvent action){
        po.setConsultaEmentaView();
    }

    @FXML
    private void handleGerirSenhas(ActionEvent action){
        po.setGestaoSenhasView();
    }

    @FXML
    private void handleGerirFavoritos(ActionEvent action){
        po.setGestaoFavoritosView();
    }

    @FXML
    private void handleSair(ActionEvent action){
        po.getControlador().logout();
        po.setLoginView();
    }

    public void initialize() {}
}
