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
import java.util.ArrayList;

public class GerirFavoritosController {
    PaneOrganizer po;
    private ArrayList<Favoritos> favoritos;
    @FXML private TableView TabelaFavoritos;

    @FXML TableColumn<TableFavoritos, String> ColPrato;
    @FXML TableColumn<TableFavoritos, Button> ColOpcao;

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
         TabelaFavoritos.setEditable(false);
         ColOpcao.setCellValueFactory(new PropertyValueFactory<>("BtRemover"));
         ColPrato.setCellValueFactory(new PropertyValueFactory<>("Prato"));
         try {
             favoritos = po.getControlador().getFavoritos();
         if(favoritos!=null) {
             for (Favoritos fav : favoritos) {

                 System.out.println(fav.getPrato());
                 TableFavoritos tbFav = new TableFavoritos(fav.getIdFavorito(), fav.getPrato());
                 tbFav.getBtRemover().setOnAction(new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent actionEvent) {
                         System.out.println("Remover" + tbFav.getBtRemover().getId());
                     }
                 });
                 TabelaFavoritos.getItems().add(tbFav);

             }
         }
         } catch (SQLException e) {
             TabelaFavoritos.setPlaceholder(new Label(e.getMessage().toString()));

         }

     }

}
