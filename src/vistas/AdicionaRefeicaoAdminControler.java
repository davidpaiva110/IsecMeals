package vistas;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import modelo.Refeicao;

import java.io.IOException;
import java.time.LocalDate;

public class AdicionaRefeicaoAdminControler {
    PaneOrganizer po;
    @FXML private TextField tfSopa;
    @FXML private   TextField tfPreco;
    @FXML private TextField tfPeixe;
    @FXML private TextField tfCarne;
    @FXML private TextField tfSob1;
    @FXML private TextField tfSob2;
    @FXML private ChoiceBox cbhorario;
    @FXML private DatePicker datepi;




    public AdicionaRefeicaoAdminControler(PaneOrganizer po) {
    this.po=po;
    }



       @FXML
    private void handleSair(ActionEvent action){
        po.getControlador().logout();
        po.setLoginView();
    }
    @FXML
    private void handleVoltar(ActionEvent action) throws IOException {
        po.setGerirEmentaAdminView();
    }

    public void initialize(){
        cbhorario.getItems().add("Jantar");
        cbhorario.getItems().add("Almoco");
        /*VBox comp= new VBox();
        comp.setAlignment(Pos.TOP_LEFT);
        comp.setPrefHeight(200.0);
        comp.setPrefWidth(200.0);*/
    }
    @FXML
    private void handleAdicionar(ActionEvent action) throws Exception {
        LocalDate date =  datepi.getValue();
        String aux=(String) cbhorario.getValue();
        int hora;
        if(aux.equals("Jantar")){
            hora=1;
        }else
            hora=0;

       Refeicao ref= new Refeicao(
                tfSopa.getText(),
                tfCarne.getText(),
                tfPeixe.getText(),
                tfSob1.getText(),
                tfSob2.getText(),
                Double.parseDouble(tfPreco.getText()),
                hora,
                date.toString()
               );
       Boolean res= po.getControlador().adicionaRefeicao(ref);
    if(res==true) {
        po.setGerirEmentaAdminView();
    }
    }
}
