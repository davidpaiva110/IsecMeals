package vistas;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class TableSenha {
    private SimpleIntegerProperty idSenha;
    private SimpleStringProperty prato;
    private SimpleStringProperty sobremesa;
    private SimpleDoubleProperty preco;
    private SimpleIntegerProperty idRefeicao;
    private Button btAlterar;
    private Button btCancelar;

    public TableSenha(int idSenha, String prato, String sombremesa, double preco, int idRef) {
        this.idSenha = new SimpleIntegerProperty(idSenha);
        this.prato = new SimpleStringProperty(prato);
        this.sobremesa = new SimpleStringProperty(sombremesa);
        this.preco = new SimpleDoubleProperty(preco);
        this.idRefeicao=new SimpleIntegerProperty(idRef);
        btAlterar=new Button("Alterar");
        btAlterar.setId(""+idSenha);
        btCancelar=new Button("Cancelar");
        btCancelar.setId(""+idSenha);
    }

    public int getIdSenha() {
        return idSenha.get();
    }

    public String getPrato() {
        return prato.get();
    }

    public String getSobremesa() {
        return sobremesa.get();
    }

    public double getPreco() {
        return preco.get();
    }

    public int getIdRefeicao(){
        return idRefeicao.get();
    }

    public Button getBtAlterar() {
        return btAlterar;
    }

    public Button getBtCancelar() {
        return btCancelar;
    }


}
