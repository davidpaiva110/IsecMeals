package vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.Complemento;
import modelo.Refeicao;
import modelo.Senha;

import java.io.IOException;
import java.util.ArrayList;

public class ComprarSenhaController {

    private PaneOrganizer po;
    private Refeicao dadosRefeicao;
    private ArrayList<Complemento> complementos;
    private Senha senha;
    private double saldo;
    @FXML Label lbSopaDesc;
    @FXML Label lbPratoCarneDesc;
    @FXML Label lbPratoPeixeDesc;
    @FXML CheckBox cbPC;
    @FXML CheckBox cbPP;
    @FXML Label lbSobremesa1;
    @FXML Label lbSobremesa2;
    @FXML Label lbHorario;
    @FXML Label lbData;
    @FXML CheckBox cbSobremesa1;
    @FXML CheckBox cbSobremesa2;
    @FXML VBox vbComplementos;
    @FXML Label lbComplementos;



    public ComprarSenhaController(PaneOrganizer paneOrganizer, Refeicao dadosrefeicao) {
        this.po = paneOrganizer;
        this.dadosRefeicao = dadosrefeicao;
        this.complementos = dadosRefeicao.getComplementos();
        this.senha = new Senha();
       // saldo = paneOrganizer.getControlador().getSaldo();
       // System.out.println(saldo);
    }

    public void initialize() {
        lbHorario.setText((dadosRefeicao.getAlmocoJantar() == 1) ? "Almoço" : "Jantar");
        lbData.setText(dadosRefeicao.getData());
        lbSopaDesc.setText(dadosRefeicao.getSopa());
        lbPratoCarneDesc.setText(dadosRefeicao.getPratoCarne());
        lbPratoPeixeDesc.setText(dadosRefeicao.getPratoPeixe());
        cbPC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cbPC.setSelected(true);
                cbPP.setSelected(false);
            }
        });
        cbPP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cbPP.setSelected(true);
                cbPC.setSelected(false);
            }
        });
        lbSobremesa1.setText(dadosRefeicao.getSombremesa1());
        lbSobremesa2.setText(dadosRefeicao.getSombremesa2());
        cbSobremesa1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cbSobremesa1.setSelected(true);
                cbSobremesa2.setSelected(false);
                senha.setSombremesa(dadosRefeicao.getSombremesa1());
            }
        });
        cbSobremesa2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cbSobremesa2.setSelected(true);
                cbSobremesa1.setSelected(false);
                senha.setSombremesa(dadosRefeicao.getSombremesa2());
            }
        });

        //Adicionar os complementos à vista
        vbComplementos.getChildren().clear();
        vbComplementos.getChildren().add(lbComplementos);
        if(complementos != null){
            for(Complemento complemento: complementos){
                Label lbC = new Label();
                lbC.setText(complemento.getNomeComplemento());
                CheckBox cbC = new CheckBox();
                cbC.setId(""+complemento.getIdComplemento());
                cbC.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        senha.setPreco(senha.getPreco()+complemento.getPreco());
                    }
                });
            }
        }




    }

    @FXML
    private void handleSair(ActionEvent action){
        po.getControlador().logout();
        po.setLoginView();
    }
    @FXML
    private void handleVoltar(ActionEvent action) throws IOException {
        po.setConsultaEmentaView();
    }
}
