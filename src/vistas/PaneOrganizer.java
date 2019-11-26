package vistas;

import controlador.Controlador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PaneOrganizer {
    Controlador controlador;
    BorderPane root;
    BorderPane menuUser;
    BorderPane menuConsultaEmenta;
    Stage primaryStage;

    public PaneOrganizer(Controlador controlador, Stage stage) throws IOException {
        this.controlador = controlador;
        primaryStage=stage;

        FXMLLoader loader=new FXMLLoader(getClass().getResource("login.fxml"));
        loader.setController(new LoginController(this));
        root=loader.load();

        loader=new FXMLLoader(getClass().getResource("menu.fxml"));
        loader.setController(new MenuUserController(this));
        menuUser=loader.load();

        primaryStage.setTitle("IsecMeals");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setMenuUserView(){
        primaryStage.setScene(new Scene(menuUser, 600, 400));
    }

}
