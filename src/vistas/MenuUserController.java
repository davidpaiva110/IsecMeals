package vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuUserController {

    PaneOrganizer po;

    public MenuUserController(PaneOrganizer po) {
        this.po = po;
    }

    @FXML
    private void handleConsultarEmenta(ActionEvent action) throws IOException {
        po.setConsultaEmentaView();
    }

    @FXML
    private void handleGerirSenhas(ActionEvent action) throws IOException {
        po.setGestaoSenhasView();
    }

    @FXML
    private void handleGerirFavoritos(ActionEvent action) throws IOException {
        po.setGestaoFavoritosView();
    }

    @FXML
    private void handleSair(ActionEvent action){
        po.getControlador().logout();
        po.setLoginView();
    }

    public void initialize() {}
}
