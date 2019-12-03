package vistas;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class TableFavoritos {
    private SimpleStringProperty Prato;
    private SimpleIntegerProperty idfavorito;
    private Button btRemover;
public  TableFavoritos(int idfavorito,String prato){
this.idfavorito= new SimpleIntegerProperty(idfavorito);
this.Prato= new SimpleStringProperty(prato);
    btRemover=new Button("Remover");
    btRemover.setId(""+idfavorito);
}

    public int getIdfavorito() {
        return idfavorito.get();
    }
    public  String getPrato(){
    return Prato.get();
    }

    public Button getBtRemover() {
        return btRemover;
    }
}
