package vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Refeicao;
import modelo.Senha;
import modelo.Utilizador;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GerirUtilizadoresAdminControler {
   private ArrayList<Utilizador> users;
   @FXML private TableView UTilizadores;
   @FXML TableColumn<TableUsersAdmin, Integer> ColNum;
   @FXML TableColumn<TableUsersAdmin, String> CoLNome;
   @FXML TableColumn<TableUsersAdmin, Float> Colsaldo;
   @FXML TableColumn<TableUsersAdmin, Button> colAlterar;
   @FXML TableColumn<TableUsersAdmin, Button> colCancelar;
   PaneOrganizer po;
   public GerirUtilizadoresAdminControler(PaneOrganizer po){this.po=po;}
   @FXML
   private void handleSair(ActionEvent action){
      po.getControlador().logout();
      po.setLoginView();
   }
   @FXML
   private void handleVoltar(ActionEvent action) throws IOException {
      po.setMenuAdminVIew();
   }

   @FXML
   private  void handleAddNewUser(ActionEvent action) throws IOException {
      po.setAdicionarUtilizadorAdmin();
   }
   public void initialize(){

      UTilizadores.setEditable(false);
      ColNum.setCellValueFactory(new PropertyValueFactory<>("numeroUser"));
      CoLNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
      Colsaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
      colAlterar.setCellValueFactory(new PropertyValueFactory<>("btAlterar"));
      colCancelar.setCellValueFactory(new PropertyValueFactory<>("btCancelar"));

      try {
         users = po.getControlador().getUserAdmin();
         for (Utilizador us : users) {
            DecimalFormat df = new DecimalFormat("0.00");
            String saldo = df.format(us.getSaldo());
            TableUsersAdmin tbuser=new TableUsersAdmin(us.getNumeroUtilizador(),us.getNome(),saldo + "€");
            /*tbuser.getBtAlterar().setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent actionEvent) {
                  /*try {
                     Senha senha = po.getControlador().getSenha(Integer.parseInt(tbSenha.getBtAlterar().getId()));
                     Refeicao ref = po.getControlador().getRefeicao(senha.getIdRefeicao());
                     po.setAlterarSenhaView(ref, senha);
                  } catch (SQLException | IOException e) {
                     Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
                     error.setHeaderText("Erro ao Carregar Vista");
                     error.setTitle("Erro");
                     error.showAndWait();
                  }
               }
            });*/
            tbuser.getBtCancelar().setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent actionEvent) {
                  ButtonType btSim=new ButtonType("Sim");
                  Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover o utilizador com o nº " + tbuser.getBtAlterar().getId() + " ?", btSim, new ButtonType("Não"));
                  confirmation.setHeaderText("Remover o utilizador");
                  confirmation.setTitle("Remover");
                  confirmation.showAndWait();
                  if (confirmation.getResult() == btSim) {
                     try {
                      //  po.getControlador().cancelSenha(Integer.parseInt(tbuser.getBtAlterar().getId()));
                     } catch (Exception e) {
                        Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
                        error.setHeaderText("Erro ao Remover o Utilizador");
                        error.setTitle("Erro");
                        error.showAndWait();
                     }
                     try {
                        po.setGestaoSenhasView();
                     } catch (IOException e) {
                        Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
                        error.setHeaderText("Erro ao Atualizar a Vista");
                        error.setTitle("Erro");
                        error.showAndWait();
                     }
                  }
               }
            });

            UTilizadores.getItems().add(tbuser);

         }
      } catch (SQLException e) {
         UTilizadores.setPlaceholder(new Label(e.getMessage().toString()));
      }
      if (UTilizadores.getItems().size()==0) UTilizadores.setPlaceholder(new Label("Não existem Utilizadores"));
   }


}
