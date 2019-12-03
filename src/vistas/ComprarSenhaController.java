package vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Complemento;
import modelo.Refeicao;
import modelo.Senha;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
    @FXML Label lbSaldo;
    @FXML Label lbTotalPagar;
    @FXML Button btnComprar;



    public ComprarSenhaController(PaneOrganizer paneOrganizer, Refeicao dadosrefeicao) {
        this.po = paneOrganizer;
        this.dadosRefeicao = dadosrefeicao;
        this.senha = new Senha();
        senha.setPreco(dadosrefeicao.getPreco());
    }

    public void initialize() {
        senha.setIdRefeicao(dadosRefeicao.getIdRefeicao());
        this.complementos = dadosRefeicao.getComplementos();
        lbTotalPagar.setText(""+senha.getPreco());
        try {
            this.saldo = po.getControlador().getSaldo();
            DecimalFormat df = new DecimalFormat("0.00");
            String saldoFormatado = df.format(saldo);
            lbSaldo.setText(saldoFormatado + "€");
        } catch (SQLException e) {
            lbSaldo.setText("Indisponível");
        }

        lbHorario.setText((dadosRefeicao.getAlmocoJantar() == 0) ? "Almoço" : "Jantar");
        lbData.setText(dadosRefeicao.getData());
        lbSopaDesc.setText(dadosRefeicao.getSopa());
        lbPratoCarneDesc.setText(dadosRefeicao.getPratoCarne());
        lbPratoPeixeDesc.setText(dadosRefeicao.getPratoPeixe());
        cbPC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cbPC.setSelected(true);
                cbPP.setSelected(false);
                senha.setPrato(dadosRefeicao.getPratoCarne());
            }
        });
        cbPP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cbPP.setSelected(true);
                cbPC.setSelected(false);
                senha.setPrato(dadosRefeicao.getPratoPeixe());
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
        vbComplementos.getChildren().addAll(lbComplementos);
        if(complementos != null){
            for(Complemento complemento: complementos){
                HBox hb = new HBox();
                Label lbC = new Label();
                lbC.setText(complemento.getNomeComplemento());
                CheckBox cbC = new CheckBox();
                cbC.setId(""+complemento.getIdComplemento());
                cbC.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(!cbC.isSelected()) {
                            senha.setPreco(senha.getPreco() - complemento.getPreco());
                            senha.removeComplemento(complemento);
                        }
                        else {
                            senha.setPreco(senha.getPreco() + complemento.getPreco());
                            senha.addComplemento(complemento);
                        }
                        DecimalFormat df = new DecimalFormat("0.00");
                        String totalPagar = df.format(senha.getPreco());
                        lbTotalPagar.setText(totalPagar + "€");
                    }
                });
                hb.getChildren().addAll(cbC, lbC);
                vbComplementos.getChildren().addAll(hb);
            }
        }

        //Botão comprar
        btnComprar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    System.out.println("i am aqui");
                    po.getControlador().buySenha(senha);
                    po.setMenuUserView();
                } catch (SQLException e) { }
                catch (IOException e) { }
            }
        });


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
