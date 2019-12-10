package vistas;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Complemento;
import modelo.Refeicao;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AlterarRefeicaoAdmin {
    PaneOrganizer po;
    private Refeicao refeicao;
    ArrayList<Complemento> complementos;
    ArrayList<Complemento> complementosAdicionados;
    ArrayList<Complemento> complementosAtualizados;
    @FXML private TextField tfSopa;
    @FXML private TextField tfPreco;
    @FXML private TextField tfPeixe;
    @FXML private TextField tfCarne;
    @FXML private TextField tfSob1;
    @FXML private TextField tfSob2;
    @FXML private ChoiceBox cbhorario;
    @FXML private DatePicker datepi;
    @FXML private VBox vbComplementos;
    @FXML private Label lbComplementos;
    @FXML private Button btAlterar;

    public AlterarRefeicaoAdmin(PaneOrganizer po, Refeicao ref) {
        this.po = po;
        this.refeicao=ref;
        try {
            complementos=po.getControlador().getTodosComplementos();
        } catch (SQLException e) {
            complementos=new ArrayList<>();
        }
        try {
            complementosAdicionados=po.getControlador().getComplementos(ref.getIdRefeicao());
        } catch (SQLException e) {
            complementosAdicionados=new ArrayList<>();
        }
        complementosAtualizados=new ArrayList<>();
    }

    @FXML
    private void handleSair(ActionEvent action) {
        po.getControlador().logout();
        po.setLoginView();
    }

    @FXML
    private void handleVoltar(ActionEvent action) throws IOException {
        po.setGerirEmentaAdminView();
    }

    public void initialize() {
        cbhorario.getItems().add("Jantar");
        cbhorario.getItems().add("Almoco");
        btAlterar.setDisable(false);
        //Adicionar os complementos à vista
        vbComplementos.setPadding(new Insets(10));
        vbComplementos.getChildren().clear();
        vbComplementos.getChildren().addAll(lbComplementos);
        if(complementos != null){
            for(Complemento complemento: complementos){
                HBox hb = new HBox();
                Label lbC = new Label();
                lbC.setText(complemento.getNomeComplemento());
                CheckBox cbC = new CheckBox();
                cbC.setId(""+complemento.getIdComplemento());
                if(complementosAdicionados!=null) {
                    for (Complemento c : complementosAdicionados) {
                        if (complemento.getIdComplemento() == c.getIdComplemento()) {
                            complementosAtualizados.add(complemento);
                            cbC.setSelected(true);
                        }
                    }
                }
                cbC.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(!cbC.isSelected()) {
                            complementosAtualizados.remove(complemento);
                        }
                        else {
                            complementosAtualizados.add(complemento);
                        }
                    }
                });
                hb.getChildren().addAll(cbC, lbC);
                vbComplementos.getChildren().addAll(hb);
            }
        }else{
            Label lb=new Label("Sem complementos");
            vbComplementos.getChildren().addAll(lb);
        }
        tfSopa.setText(refeicao.getSopa());
        tfSopa.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldV, String newV) {
                if(newV.length()>0 && tfCarne.getText().length()>0 && tfPeixe.getText().length()>0 && tfPreco.getText().length()>0 &&
                        tfSob1.getText().length()>0 && tfSob2.getText().length()>0 && datepi.getValue()!=null && cbhorario.getValue()!=null)
                    btAlterar.setDisable(false);
                else
                    btAlterar.setDisable(true);
            }
        });
        tfCarne.setText(refeicao.getPratoCarne());
        tfCarne.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newV) {
                if(newV.length()>0 && tfSopa.getText().length()>0 && tfPeixe.getText().length()>0 && tfPreco.getText().length()>0 &&
                        tfSob1.getText().length()>0 && tfSob2.getText().length()>0 && datepi.getValue()!=null && cbhorario.getValue()!=null)
                    btAlterar.setDisable(false);
                else
                    btAlterar.setDisable(true);
            }
        });
        tfPeixe.setText(refeicao.getPratoPeixe());
        tfPeixe.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newV) {
                if(newV.length()>0 && tfSopa.getText().length()>0 && tfCarne.getText().length()>0 && tfPreco.getText().length()>0 &&
                        tfSob1.getText().length()>0 && tfSob2.getText().length()>0 && datepi.getValue()!=null && cbhorario.getValue()!=null)
                    btAlterar.setDisable(false);
                else
                    btAlterar.setDisable(true);
            }
        });
        tfSob1.setText(refeicao.getSombremesa1());
        tfSob1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newV) {
                if(newV.length()>0 && tfSopa.getText().length()>0 && tfCarne.getText().length()>0 && tfPreco.getText().length()>0 &&
                        tfPeixe.getText().length()>0 && tfSob2.getText().length()>0 && datepi.getValue()!=null && cbhorario.getValue()!=null)
                    btAlterar.setDisable(false);
                else
                    btAlterar.setDisable(true);
            }
        });
        tfSob2.setText(refeicao.getSombremesa2());
        tfSob2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newV) {
                if(newV.length()>0 && tfSopa.getText().length()>0 && tfCarne.getText().length()>0 && tfPreco.getText().length()>0 &&
                        tfPeixe.getText().length()>0 && tfSob1.getText().length()>0 && datepi.getValue()!=null && cbhorario.getValue()!=null)
                    btAlterar.setDisable(false);
                else
                    btAlterar.setDisable(true);
            }
        });
        tfPreco.setText(""+refeicao.getPreco());
        tfPreco.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old, String newV) {
                if (newV.length() > 0) {
                    try {
                        Double.parseDouble(newV);
                        tfPreco.setText(newV);
                    } catch (Exception e) {
                        tfPreco.setText(old);
                    }
                }
                if (newV.length() > 0 && tfSopa.getText().length() > 0 && tfCarne.getText().length() > 0 && tfSob2.getText().length() > 0 &&
                        tfPeixe.getText().length() > 0 && tfSob1.getText().length() > 0 && datepi.getValue() != null && cbhorario.getValue() != null)
                    btAlterar.setDisable(false);
                else
                    btAlterar.setDisable(true);
            }
        });
        if(refeicao.getAlmocoJantar()==0)
            cbhorario.getSelectionModel().select("Almoco");
        else
            cbhorario.getSelectionModel().select("Jantar");
        cbhorario.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(tfSopa.getText().length()>0 && tfCarne.getText().length()>0 && tfSob2.getText().length()>0 &&
                        tfPeixe.getText().length()>0 && tfSob1.getText().length()>0 && datepi.getValue()!=null && tfPreco.getText().length()>0)
                    btAlterar.setDisable(false);
                else
                    btAlterar.setDisable(true);
            }
        });
        datepi.setValue(LocalDate.parse(refeicao.getData()));
        datepi.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                if(t1!=null && tfSopa.getText().length()>0 && tfCarne.getText().length()>0 && tfSob2.getText().length()>0 &&
                        tfPeixe.getText().length()>0 && tfSob1.getText().length()>0 && datepi.getValue()!=null && tfPreco.getText().length()>0)
                    btAlterar.setDisable(false);
                else
                    btAlterar.setDisable(true);
            }
        });
    }

    @FXML
    private void handleAlterar(ActionEvent action) {
        LocalDate date =  datepi.getValue();
        String aux=(String) cbhorario.getValue();
        int hora;
        if(aux.equals("Jantar")){
            hora=1;
        }else
            hora=0;

        Refeicao ref= new Refeicao(refeicao.getIdRefeicao(),
                tfSopa.getText(),
                tfCarne.getText(),
                tfPeixe.getText(),
                tfSob1.getText(),
                tfSob2.getText(),
                Double.parseDouble(tfPreco.getText()),
                hora,
                date.toString(),
                complementosAtualizados
        );
        try {
            po.getControlador().alteraRefeicao(ref);
            Alert success = new Alert(Alert.AlertType.INFORMATION, "Refeição alterada com sucesso!", ButtonType.OK);
            success.setHeaderText("Refeição Alterada");
            success.setTitle("Sucesso");
            success.showAndWait();
            po.setGerirEmentaAdminView();
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
            error.setHeaderText("Erro ao Alterar Refeição");
            error.setTitle("Erro");
            error.showAndWait();
        }
    }
}
