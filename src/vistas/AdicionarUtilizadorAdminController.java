package vistas;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import vistas.PaneOrganizer;

import java.io.IOException;

public class AdicionarUtilizadorAdminController {

    private PaneOrganizer po;
    @FXML private TextField tfUserNumber;
    @FXML private TextField tfUserName;
    @FXML private TextField tfUserSaldo;

    public AdicionarUtilizadorAdminController(PaneOrganizer po) {
        this.po = po;
    }


    public void initialize(){
       tfUserNumber.textProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
               if(newValue.length()>0 && newValue.length()<=8) {
                   try {
                       Integer.parseInt(newValue);
                       tfUserNumber.setText(newValue);
                   } catch (Exception e) {
                       tfUserNumber.setText(oldValue);
                   }
               }
               if(newValue.length()>8){
                   tfUserNumber.setText(oldValue);
               }
           }
       });

       tfUserSaldo.textProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
               if(newValue.length()>0) {
                   try {
                       Double.parseDouble(newValue);
                       tfUserSaldo.setText(newValue);
                   } catch (Exception e) {
                       tfUserSaldo.setText(oldValue);
                   }
               }
           }
       });
    }

    @FXML
    public void handleSair(ActionEvent action){
        po.getControlador().logout();
        po.setLoginView();
    }

    @FXML
    public void handleVoltar(ActionEvent action) throws IOException {
        po.setGerirUtilizadoresAdminView();
    }
    @FXML
    public void handleAdicionar(ActionEvent action) {
        String userName = tfUserName.getText();
        int userID = Integer.parseInt(tfUserNumber.getText());
        double userSaldo = Double.parseDouble(tfUserSaldo.getText());
    }


}
