package vistas;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import modelo.Utilizador;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class AlterarUtilizadorAdminController {

    private  PaneOrganizer po;
    private Utilizador utilizador;
    @FXML private TextField tfUserId;
    @FXML private TextField tfNome;
    @FXML private TextField tfSaldo;
    @FXML private Button btnAlterar;

    private double saldo;
    private int oldUserNumber;

    public AlterarUtilizadorAdminController(PaneOrganizer paneOrganizer, Utilizador utilizador) {
        this.po = paneOrganizer;
        this.utilizador = utilizador;
        oldUserNumber = utilizador.getNumeroUtilizador();
        try {
            saldo = po.getControlador().getSaldo(utilizador.getNumeroUtilizador());
        } catch (SQLException e) {
            saldo = 0.0;
        }
    }

    public void initialize() {
        tfUserId.setText("" + utilizador.getNumeroUtilizador());
        tfNome.setText(utilizador.getNome());
        DecimalFormat df = new DecimalFormat("0.00");
        String saldoFormatado = df.format(saldo);
        tfSaldo.setText(saldoFormatado);
        tfSaldo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.length() > 0) {
                    if(tfNome.getText().length() > 0)
                        btnAlterar.setDisable(false);
                    try {
                        Double.parseDouble(newValue);
                        tfSaldo.setText(newValue);
                    } catch (Exception e) {
                        tfSaldo.setText(oldValue);
                    }
                }else
                    btnAlterar.setDisable(true);
            }
        });

        tfNome.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() > 0) {
                    if (tfSaldo.getText().length() > 0)
                        btnAlterar.setDisable(false);
                }else
                    btnAlterar.setDisable(true);
            }
        });

        tfUserId.setEditable(false);
    }

    @FXML private void onClickVoltar(ActionEvent action){
        try {
            po.setGerirUtilizadoresAdminView();
        } catch (IOException e) {

        }
    }

    @FXML private void onClickSair(ActionEvent action){
        po.getControlador().logout();
        po.setLoginView();
    }

    @FXML private void onClickAlterar(ActionEvent action){
        utilizador.setSaldo(Double.parseDouble(tfSaldo.getText()));
        utilizador.setNome(tfNome.getText());
        try {
            po.getControlador().updateUser(utilizador, oldUserNumber);
        } catch (SQLException e) {
            System.out.println(e);
        }
        try {
            po.setGerirUtilizadoresAdminView();
        } catch (IOException e) {

        }
    }

    @FXML private void onClickGerarPassword(ActionEvent action){
        String password = null;
        try {
            password = po.getControlador().setNewPassword(utilizador.getNumeroUtilizador());
        } catch (SQLException e) {
            System.out.println(e);
        }
        Alert error = new Alert(Alert.AlertType.INFORMATION, "A password deveria ser enviada por email ao utilizador, mas de acordo com o Change Request #1 a funcionalidade não foi implementada.", ButtonType.OK);
        error.setTitle("Password");
        error.setHeaderText("Password Gerada: " + password);
        error.showAndWait();
    }
}
