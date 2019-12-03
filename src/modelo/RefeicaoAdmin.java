package modelo;

import java.util.Date;

public class RefeicaoAdmin {

    private int idRefeicao;


    private String data;
    private String fase;
    private  int quantCarne;
    private int quantPeixe;

    public int getIdRefeicao() {
        return idRefeicao;
    }

    public String getFase() {
        return fase;
    }

    public int getQuantCarne() {
        return quantCarne;
    }

    public String getData() {
        return data;
    }
    public int getQuantPeixe() {
        return quantPeixe;
    }

    public RefeicaoAdmin(int idRefeicao, String data, String fase, int quantCarne, int quantPeixe) {
        this.idRefeicao = idRefeicao;
        this.data = data;
        this.fase=fase;
        this.quantCarne = quantCarne;
        this.quantPeixe = quantPeixe;
    }
}
