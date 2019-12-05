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

public class AdicionaRefeicaoAdminControler {
    PaneOrganizer po;
    ArrayList<Complemento> complementos;
    ArrayList<Complemento> complementosAdicionados;
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
    @FXML private Button btAdicionar;

    public AdicionaRefeicaoAdminControler(PaneOrganizer po) {
        this.po=po;
        try {
            complementos=po.getControlador().getTodosComplementos();
        } catch (SQLException e) {
            complementos=new ArrayList<>();
        }
        complementosAdicionados=new ArrayList<>();
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
        btAdicionar.setDisable(true);
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
                cbC.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(!cbC.isSelected()) {
                            complementosAdicionados.remove(complemento);
                        }
                        else {
                            complementosAdicionados.add(complemento);
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

        tfSopa.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldV, String newV) {
                if(newV.length()>0 && tfCarne.getText().length()>0 && tfPeixe.getText().length()>0 && tfPreco.getText().length()>0 &&
                        tfSob1.getText().length()>0 && tfSob2.getText().length()>0 && datepi.getValue()!=null && cbhorario.getValue()!=null)
                    btAdicionar.setDisable(false);
                else
                    btAdicionar.setDisable(true);
            }
        });

        tfCarne.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newV) {
                if(newV.length()>0 && tfSopa.getText().length()>0 && tfPeixe.getText().length()>0 && tfPreco.getText().length()>0 &&
                        tfSob1.getText().length()>0 && tfSob2.getText().length()>0 && datepi.getValue()!=null && cbhorario.getValue()!=null)
                    btAdicionar.setDisable(false);
                else
                    btAdicionar.setDisable(true);
            }
        });

        tfPeixe.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newV) {
                if(newV.length()>0 && tfSopa.getText().length()>0 && tfCarne.getText().length()>0 && tfPreco.getText().length()>0 &&
                        tfSob1.getText().length()>0 && tfSob2.getText().length()>0 && datepi.getValue()!=null && cbhorario.getValue()!=null)
                    btAdicionar.setDisable(false);
                else
                    btAdicionar.setDisable(true);
            }
        });

        tfSob1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newV) {
                if(newV.length()>0 && tfSopa.getText().length()>0 && tfCarne.getText().length()>0 && tfPreco.getText().length()>0 &&
                        tfPeixe.getText().length()>0 && tfSob2.getText().length()>0 && datepi.getValue()!=null && cbhorario.getValue()!=null)
                    btAdicionar.setDisable(false);
                else
                    btAdicionar.setDisable(true);
            }
        });

        tfSob2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newV) {
                if(newV.length()>0 && tfSopa.getText().length()>0 && tfCarne.getText().length()>0 && tfPreco.getText().length()>0 &&
                        tfPeixe.getText().length()>0 && tfSob1.getText().length()>0 && datepi.getValue()!=null && cbhorario.getValue()!=null)
                    btAdicionar.setDisable(false);
                else
                    btAdicionar.setDisable(true);
            }
        });

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
                    btAdicionar.setDisable(false);
                else
                    btAdicionar.setDisable(true);
            }
        });

        cbhorario.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(tfSopa.getText().length()>0 && tfCarne.getText().length()>0 && tfSob2.getText().length()>0 &&
                        tfPeixe.getText().length()>0 && tfSob1.getText().length()>0 && datepi.getValue()!=null && tfPreco.getText().length()>0)
                    btAdicionar.setDisable(false);
                else
                    btAdicionar.setDisable(true);
            }
        });

        datepi.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                if(t1!=null && tfSopa.getText().length()>0 && tfCarne.getText().length()>0 && tfSob2.getText().length()>0 &&
                        tfPeixe.getText().length()>0 && tfSob1.getText().length()>0 && datepi.getValue()!=null && tfPreco.getText().length()>0)
                    btAdicionar.setDisable(false);
                else
                    btAdicionar.setDisable(true);
            }
        });
    }
    @FXML
    private void handleAdicionar(ActionEvent action) {
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
                date.toString(),
               complementosAdicionados
               );
        try {
            po.getControlador().adicionaRefeicao(ref);
            po.setGerirEmentaAdminView();
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage().toString(), ButtonType.OK);
            error.setHeaderText("Erro ao Criar Refeição");
            error.setTitle("Erro");
            error.showAndWait();
        }
    }
}
