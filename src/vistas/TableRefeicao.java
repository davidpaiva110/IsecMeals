package vistas;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class TableRefeicao {
    private SimpleIntegerProperty idRefeicao;
    private SimpleStringProperty fase;
    private SimpleStringProperty data;
    private Button btalterar;
    private Button btcancelar;

    public TableRefeicao(int idRefeicao, String fase, String data) {
        this.idRefeicao = new SimpleIntegerProperty(idRefeicao);
        this.fase = new  SimpleStringProperty(fase);
        this.data = new SimpleStringProperty(data);
        this.btalterar = new Button("Alterar");
        btalterar.setId(""+idRefeicao);
        this.btcancelar = new Button("Cancelar");
        btcancelar.setId(""+idRefeicao);
    }

    public int getIdRefeicao() {
        return idRefeicao.get();
    }

    public SimpleIntegerProperty idRefeicaoProperty() {
        return idRefeicao;
    }

    public String getFase() {
        return fase.get();
    }

    public SimpleStringProperty faseProperty() {
        return fase;
    }

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public Button getBtalterar() {
        return btalterar;
    }

    public Button getBtcancelar() {
        return btcancelar;
    }
}
