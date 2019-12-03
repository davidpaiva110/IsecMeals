package vistas;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

import java.text.SimpleDateFormat;

public class TableSenhasAdmin {
    private SimpleIntegerProperty idSenhaAdmin;
    private SimpleStringProperty  fase;
    private SimpleStringProperty dat;
    private SimpleIntegerProperty quantPeixe;
    private SimpleIntegerProperty quantCarne;

    public TableSenhasAdmin(int idSenhaAdmin, String fase, String dat, int quantPeixe, int quantCarne) {
        this.idSenhaAdmin = new SimpleIntegerProperty(idSenhaAdmin);
        this.fase = new SimpleStringProperty(fase);
        this.dat = new SimpleStringProperty(dat);
        this.quantPeixe = new SimpleIntegerProperty(quantPeixe);
        this.quantCarne =  new SimpleIntegerProperty(quantCarne);
    }



    public int getIdSenhaAdmin() {
        return idSenhaAdmin.get();
    }

    public SimpleIntegerProperty idSenhaAdminProperty() {
        return idSenhaAdmin;
    }

    public String getFase() {
        return fase.get();
    }

    public String getDat() {
        return dat.get();
    }

    public SimpleStringProperty datProperty() {
        return dat;
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
