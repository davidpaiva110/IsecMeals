package vistas;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class TableUsersAdmin {
    private SimpleIntegerProperty numeroUser;
    private SimpleStringProperty nome;
    private SimpleStringProperty saldo;
    private Button btAlterar;
    private Button btCancelar;

    public int getNumeroUser() {
        return numeroUser.get();
    }

    public SimpleIntegerProperty numeroUserProperty() {
        return numeroUser;
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public String getSaldo() {
        return saldo.get();
    }

    public SimpleStringProperty saldoProperty() {
        return saldo;
    }

    public Button getBtAlterar() {
        return btAlterar;
    }

    public Button getBtCancelar() {
        return btCancelar;
    }

    public TableUsersAdmin(int numeroUser, String nome, String saldo) {
        this.numeroUser =  new SimpleIntegerProperty(numeroUser);
        this.nome =  new SimpleStringProperty(nome);
        this.saldo = new SimpleStringProperty(saldo);
        this.btAlterar = new Button("Alterar");
        btAlterar.setId(""+numeroUser);
        this.btCancelar = new Button("Cancelar");
        btCancelar.setId(""+numeroUser);
    }
    public void noBtCancelar(){
        btCancelar=null;
    }

    public void noBtAlterar(){
        btAlterar=null;
    }
}
