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

    BorderPane consulaEmenta;
    BorderPane gerirSenhas;
    BorderPane gerirFavoritos;

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

        new Scene(menuUser, 600, 400);
        loader=new FXMLLoader(getClass().getResource("consultaEmenta.fxml"));
        loader.setController(new ConsultaEmentaController(this));
        consulaEmenta=loader.load();
        new Scene(consulaEmenta, 600, 400);
        loader=new FXMLLoader(getClass().getResource("GestaoSennhas.fxml"));
        loader.setController(new GerirSenhasController(this));
        gerirSenhas=loader.load();
        new Scene(gerirSenhas, 600, 400);
        loader=new FXMLLoader(getClass().getResource("GestaoFavoritos.fxml"));
        loader.setController(new GerirFavoritosController(this));
        gerirFavoritos=loader.load();
        new Scene(gerirFavoritos, 600, 400);


        primaryStage.setTitle("IsecMeals");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setLoginView(){
        primaryStage.setScene(root.getScene());
    }
    public void setMenuUserView(){
        primaryStage.setScene(menuUser.getScene());
    }
    public void setConsultaEmentaView(){
        primaryStage.setScene(consulaEmenta.getScene());
    }
    public void setGestaoSenhasView(){
        primaryStage.setScene(gerirSenhas.getScene());
    }
    public void setGestaoFavoritosView(){
        primaryStage.setScene(gerirFavoritos.getScene());
    }

}
