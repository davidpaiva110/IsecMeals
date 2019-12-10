package vistas;

import controlador.Controlador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modelo.Refeicao;
import modelo.Senha;
import modelo.Utilizador;

import java.io.IOException;

public class PaneOrganizer {

    public static final int LARGURA = 600;
    public static final int ALTURA = 400;

    Controlador controlador;
    BorderPane root;
    BorderPane menuUser;
    BorderPane consulaEmenta;
    BorderPane gerirSenhas;
    BorderPane gerirFavoritos;
    BorderPane menuAdmin;
    BorderPane gerirEmentaAdmin;
    BorderPane gerirUtilizadoresAdmin;
    BorderPane VerSenhasCompradasAdmin;
    BorderPane AdicionaRefeicaoAdmin;
    BorderPane comprarSenha;
    BorderPane alterarSenha;
    BorderPane adicionarUtilizadorAdmin;
    BorderPane alterarRefeicaoAdmin;
    BorderPane alterarUtilizadorAdmin;
    Stage primaryStage;

    FXMLLoader loader;

    public PaneOrganizer(Controlador controlador, Stage stage) throws IOException {
        this.controlador = controlador;
        primaryStage=stage;

        loader=new FXMLLoader(getClass().getResource("login.fxml"));
        loader.setController(new LoginController(this));
        root=loader.load();

        primaryStage.setTitle("IsecMeals");
        primaryStage.setScene(new Scene(root,  LARGURA, ALTURA));
        primaryStage.show();
    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setLoginView(){
        primaryStage.setScene(root.getScene());
    }
    public void setMenuUserView() throws IOException {
        loader=new FXMLLoader(getClass().getResource("menu.fxml"));
        loader.setController(new MenuUserController(this));
        menuUser=loader.load();
        new Scene(menuUser, LARGURA, ALTURA);
        primaryStage.setScene(menuUser.getScene());
    }
    public void setConsultaEmentaView() throws IOException {
        loader=new FXMLLoader(getClass().getResource("consultaEmenta.fxml"));
        loader.setController(new ConsultaEmentaController(this));
        consulaEmenta=loader.load();
        new Scene(consulaEmenta, LARGURA, ALTURA);
        primaryStage.setScene(consulaEmenta.getScene());
    }
    public  void setMenuAdminVIew() throws IOException {
        loader= new FXMLLoader(getClass().getResource("vistaAdministrador/PaginaInicialAdmin.fxml"));
        loader.setController(new MenuAdminControler(this));
        menuAdmin=loader.load();
        new Scene(menuAdmin, LARGURA, ALTURA);
        primaryStage.setScene(menuAdmin.getScene());
    }

    public void setGestaoSenhasView() throws IOException {
        loader=new FXMLLoader(getClass().getResource("GestaoSennhas.fxml"));
        loader.setController(new GerirSenhasController(this));
        gerirSenhas=loader.load();
        new Scene(gerirSenhas, LARGURA, ALTURA);
        primaryStage.setScene(gerirSenhas.getScene());
    }

    public void setGestaoFavoritosView() throws IOException {
        loader=new FXMLLoader(getClass().getResource("GestaoFavoritos.fxml"));
        loader.setController(new GerirFavoritosController(this));
        gerirFavoritos=loader.load();
        new Scene(gerirFavoritos, LARGURA, ALTURA);
        primaryStage.setScene(gerirFavoritos.getScene());
    }
    public void setGerirEmentaAdminView() throws IOException {
        loader= new FXMLLoader(getClass().getResource("vistaAdministrador/GerirEmentaAdmin.fxml"));
        loader.setController(new GerirEmentaAdminControler(this));
        gerirEmentaAdmin=loader.load();
        new Scene(gerirEmentaAdmin, LARGURA, ALTURA);
        primaryStage.setScene(gerirEmentaAdmin.getScene());
    }
    public void setGerirUtilizadoresAdminView() throws IOException {
        loader= new FXMLLoader(getClass().getResource("vistaAdministrador/GerirUtilizadoresAdmin.fxml"));
        loader.setController(new GerirUtilizadoresAdminControler(this));
        gerirUtilizadoresAdmin=loader.load();
        new Scene( gerirUtilizadoresAdmin, LARGURA, ALTURA);
        primaryStage.setScene(gerirUtilizadoresAdmin.getScene());
    }
    public void setSenhaaCompradasAdminView() throws IOException {
        loader= new FXMLLoader(getClass().getResource("vistaAdministrador/ConsultarSenhasAdmin.fxml"));
        loader.setController(new VerSenhasCompradasAdminControler(this));
        VerSenhasCompradasAdmin=loader.load();
        new Scene( VerSenhasCompradasAdmin, LARGURA, ALTURA);
        primaryStage.setScene(VerSenhasCompradasAdmin.getScene());
    }
    public void setAdicionaRefeicaoView() throws IOException {
        loader= new FXMLLoader(getClass().getResource("vistaAdministrador/AdicionarRefeicaoAdmin.fxml"));
        loader.setController(new AdicionaRefeicaoAdminControler(this));
        AdicionaRefeicaoAdmin=loader.load();
        new Scene( AdicionaRefeicaoAdmin, LARGURA, ALTURA);
        primaryStage.setScene(AdicionaRefeicaoAdmin.getScene());
    }

    public void setComprarSenhaView(Refeicao dadosrefeicao) throws IOException{
        loader= new FXMLLoader(getClass().getResource("compraSenha.fxml"));
        loader.setController(new ComprarSenhaController(this, dadosrefeicao));
        comprarSenha=loader.load();
        new Scene( comprarSenha, LARGURA, ALTURA);
        primaryStage.setScene(comprarSenha.getScene());
    }

    public void setAlterarSenhaView(Refeicao ref, Senha senha) throws IOException{
        loader= new FXMLLoader(getClass().getResource("AlterarSenha.fxml"));
        loader.setController(new AlterarSenhaController(this, ref, senha));
        alterarSenha=loader.load();
        new Scene( alterarSenha, LARGURA, ALTURA);
        primaryStage.setScene(alterarSenha.getScene());
    }

    public  void setAdicionarUtilizadorAdmin() throws IOException {
        loader= new FXMLLoader(getClass().getResource("vistaAdministrador/AdicionarUtilizadorAdmin.fxml"));
        loader.setController(new AdicionarUtilizadorAdminController(this));
        adicionarUtilizadorAdmin=loader.load();
        new Scene( adicionarUtilizadorAdmin, LARGURA, ALTURA);
        primaryStage.setScene(adicionarUtilizadorAdmin.getScene());
    }

    public  void setAlterarRefeicaoAdminView(Refeicao ref) throws IOException {
        loader = new FXMLLoader(getClass().getResource("vistaAdministrador/AlteraRefeicaoAdmin.fxml"));
        loader.setController(new AlterarRefeicaoAdmin(this, ref));
        alterarRefeicaoAdmin = loader.load();
        new Scene(alterarRefeicaoAdmin, 600, 400);
        primaryStage.setScene(alterarRefeicaoAdmin.getScene());
    }
    public void setAlterarUtilizadorAdmin(Utilizador utilizador) throws IOException {
        loader= new FXMLLoader(getClass().getResource("vistaAdministrador/AlterarUtilizadorAdmin.fxml"));
        loader.setController(new AlterarUtilizadorAdminController(this, utilizador));
        alterarUtilizadorAdmin=loader.load();
        new Scene( alterarUtilizadorAdmin, LARGURA, ALTURA);
        primaryStage.setScene(alterarUtilizadorAdmin.getScene());
    }
}
