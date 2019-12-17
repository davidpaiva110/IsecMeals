package vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Complemento;
import modelo.Refeicao;
import modelo.Senha;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class AlterarSenhaController {

    private PaneOrganizer po;
    private Refeicao dadosRefeicao;
    private Senha senha;
    private ArrayList<Complemento> complementos;
    private ArrayList<Complemento> complementosSenha;
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
    @FXML Button btnAlterar;



    public AlterarSenhaController(PaneOrganizer paneOrganizer, Refeicao dadosrefeicao, Senha senha) {
        this.po = paneOrganizer;
        this.dadosRefeicao = dadosrefeicao;
        this.senha = senha;
        complementosSenha=senha.getComplementos();
    }

    public void initialize() {
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
        if(senha.getPrato().equalsIgnoreCase(dadosRefeicao.getPratoCarne())) {
            cbPC.setSelected(true);
            cbPP.setSelected(false);
        }else{
            cbPP.setSelected(true);
            cbPC.setSelected(false);
        }
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
        DecimalFormat df = new DecimalFormat("0.00");
        String totalPagar = df.format(senha.getPreco());
        lbTotalPagar.setText(totalPagar+"€");
        this.complementos = dadosRefeicao.getComplementos();
        lbSobremesa1.setText(dadosRefeicao.getSombremesa1());
        lbSobremesa2.setText(dadosRefeicao.getSombremesa2());
        if(senha.getSombremesa().equalsIgnoreCase(dadosRefeicao.getSombremesa1())) {
            cbSobremesa1.setSelected(true);
            cbSobremesa2.setSelected(false);
        }else{
            cbSobremesa2.setSelected(true);
            cbSobremesa1.setSelected(false);
        }
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
                for (Complemento comp: senha.getComplementos()) {
                    if(comp.getIdComplemento()==complemento.getIdComplemento()){
                        cbC.setSelected(true);
                        break;
                    }
                }
                cbC.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(!cbC.isSelected()) {
                            senha.setPreco(senha.getPreco() - complemento.getPreco());
                            for (Complemento co: complementosSenha) {
                                if(co.getIdComplemento()==Integer.parseInt(cbC.getId())) {
                                    complementosSenha.remove(co);
                                    break;
                                }
                            }
                        }
                        else {
                            try {
                                if (complemento.getPreco() <= po.getControlador().getSaldo()) {
                                    senha.setPreco(senha.getPreco() + complemento.getPreco());
                                    senha.addComplemento(complemento);
                                } else {
                                    cbC.setSelected(false);
                                }
                            } catch (SQLException e) { }
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

        //Botão alterar
        btnAlterar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    senha.setComplementos(complementosSenha);
                    po.getControlador().updateSenha(senha);
                    Alert success = new Alert(Alert.AlertType.INFORMATION, "Senha alterada com sucesso!", ButtonType.OK);
                    success.setHeaderText("Senha Alterada");
                    success.setTitle("Sucesso");
                    success.showAndWait();
                    po.setGestaoSenhasView();
                } catch (Exception e) {
                    Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
                    error.setHeaderText("Erro ao Alterar a Senha");
                    error.setTitle("Erro");
                    error.showAndWait();
                }
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
        po.setGestaoSenhasView();
    }
}

