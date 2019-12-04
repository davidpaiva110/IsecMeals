package vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Favoritos;
import modelo.Senha;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GerirFavoritosController {
    PaneOrganizer po;
    private ArrayList<Favoritos> favoritos;
    @FXML private TableView TabelaFavoritos;

    @FXML TableColumn<TableFavoritos, String> ColPrato;
    @FXML TableColumn<TableFavoritos, Button> ColOpcao;
    @FXML Label lbSaldo;

    public GerirFavoritosController(PaneOrganizer po) {
        this.po=po;
    }
    @FXML
    private void handleSair(ActionEvent action){
        po.getControlador().logout();
        po.setLoginView();
    }
    @FXML
    private void handleVoltar(ActionEvent action) throws IOException {
        po.setMenuUserView();
    }
     public void initialize()  {
         try {
             double saldo = po.getControlador().getSaldo();
             DecimalFormat df = new DecimalFormat("0.00");
             String saldoFormatado = df.format(saldo);
             lbSaldo.setText(saldoFormatado + "€");
         } catch (SQLException e) {
             lbSaldo.setText("Indisponível");
         }
         TabelaFavoritos.setEditable(false);
         ColOpcao.setCellValueFactory(new PropertyValueFactory<>("BtRemover"));
         ColPrato.setCellValueFactory(new PropertyValueFactory<>("Prato"));
         try {
             favoritos = po.getControlador().getFavoritos();
         if(favoritos!=null) {
             for (Favoritos fav : favoritos) {
                 TableFavoritos tbFav = new TableFavoritos(fav.getIdFavorito(), fav.getPrato());
                 tbFav.getBtRemover().setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent actionEvent) {
                         ButtonType btSim=new ButtonType("Sim");
                         Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover o item "+fav.getPrato()+" dos favoritos? ", btSim, new ButtonType("Não"));
                         confirmation.setHeaderText("Remover dos favoritos");
                         confirmation.setTitle("Remover");
                         confirmation.showAndWait();
                         if (confirmation.getResult() == btSim) {
                             try {
                                 po.getControlador().RemoveFavorito(Integer.parseInt(tbFav.getBtRemover().getId()));
                             } catch (Exception e) {
                                 Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
                                 error.setHeaderText("Erro ao Remover o Favorito");
                                 error.setTitle("Erro");
                                 error.showAndWait();
                             }
                             try {
                                 po.setGestaoFavoritosView();
                             } catch (IOException e) {
                                 Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
                                 error.setHeaderText("Erro ao Atualizar a Vista");
                                 error.setTitle("Erro");
                                 error.showAndWait();
                             }
                         }
                     }
                 });
                 TabelaFavoritos.getItems().add(tbFav);

             }
         }
         } catch (SQLException e) {
             TabelaFavoritos.setPlaceholder(new Label(e.getMessage().toString()));

         }
         if (TabelaFavoritos.getItems().size()==0) TabelaFavoritos.setPlaceholder(new Label("Não existem Favoritos"));


     }

}
