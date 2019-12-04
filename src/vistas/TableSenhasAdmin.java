package vistas;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableSenhasAdmin {
    private SimpleIntegerProperty idRefeicao;
    private SimpleStringProperty  fase;
    private SimpleStringProperty data;
    private SimpleIntegerProperty quantPeixe;
    private SimpleIntegerProperty quantCarne;

    public TableSenhasAdmin(int idRefeicao, String fase, String data, int quantPeixe, int quantCarne) {
        this.idRefeicao = new SimpleIntegerProperty(idRefeicao);
        this.fase = new SimpleStringProperty(fase);
        this.data = new SimpleStringProperty(data);
        this.quantPeixe = new SimpleIntegerProperty(quantPeixe);
        this.quantCarne =  new SimpleIntegerProperty(quantCarne);
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

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public SimpleStringProperty faseProperty() {
        return fase;
    }

    public int getQuantPeixe() {
        return quantPeixe.get();
    }

    public SimpleIntegerProperty quantPeixeProperty() {
        return quantPeixe;
    }

    public int getQuantCarne() {
        return quantCarne.get();
    }

    public SimpleIntegerProperty quantCarneProperty() {
        return quantCarne;
    }
}
