package vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class MenuUserController {

    PaneOrganizer po;
    @FXML Label lbSaldo;

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

    public void initialize() {
        try {
            double saldo = po.getControlador().getSaldo();
            DecimalFormat df = new DecimalFormat("0.00");
            String saldoFormatado = df.format(saldo);
            lbSaldo.setText(saldoFormatado + "€");
        } catch (SQLException e) {
            lbSaldo.setText("Indisponível");
        }
    }
}
