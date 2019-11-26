package vistas;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    PaneOrganizer po;

    @FXML private TextField tfUser;
    @FXML private PasswordField tfPassword;
    @FXML private Button btLogin;
    @FXML private Label lbErroLogin;

    @FXML
    private void handleBtEntrar(ActionEvent action){
        String user=tfUser.getText();
        String password=tfPassword.getText();
        try {
            po.getControlador().login(Integer.parseInt(user), password);
            tfUser.setText("");
            tfPassword.setText("");
            lbErroLogin.setText("");
            if (po.getControlador().geteutilizador()==0){
                po.setMenuUserView();
            }else{
                po.setMenuAdminVIew();
            }

        } catch (Exception e) {
            lbErroLogin.setText(e.getMessage());
        }

    }

    public LoginController(PaneOrganizer po) {
        this.po = po;
    }

    public void initialize() {
        btLogin.setDisable(true);
        tfUser.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                btLogin.setDisable(true);
                if(newValue.length()>0 && newValue.length()<=8) {
                    if(newValue.length()==8 && tfPassword.getText().length()>0)
                        btLogin.setDisable(false);
                    try {
                        Integer.parseInt(newValue);
                        tfUser.setText(newValue);
                    } catch (Exception e) {
                        tfUser.setText(oldValue);
                    }
                }
                if(newValue.length()>8){
                    tfUser.setText(oldValue);
                }
            }
        });

        tfPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                btLogin.setDisable(true);
                if(newValue.length()>0 && tfUser.getText().length()==8) {
                        btLogin.setDisable(false);
                }
            }
        });
    }
}
