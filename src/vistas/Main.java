package vistas;

import controlador.Controlador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controlador controlador = new Controlador();
        PaneOrganizer paneOrg=new PaneOrganizer(controlador, primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
