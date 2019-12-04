package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Senha {
    private int idSenha;
    private String prato;
    private String sombremesa;
    private double preco;
    private int idRefeicao;
    private ArrayList<Complemento> complementos;

    public Senha(int idSenha, String prato, String sombremesa, double preco, int idRef) {
        this.idSenha = idSenha;
        this.prato = prato;
        this.sombremesa = sombremesa;
        this.preco = preco;
        this.idRefeicao=idRef;
        this.complementos = new ArrayList<>();
    }

    public Senha(int idSenha, String prato, String sombremesa, double preco, int idRef, ArrayList<Complemento> comp) {
        this.idSenha = idSenha;
        this.prato = prato;
        this.sombremesa = sombremesa;
        this.preco = preco;
        this.idRefeicao=idRef;
        if(comp!=null)
            this.complementos = comp;
        else
            this.complementos = new ArrayList<>();
    }

    public Senha() {
        this.complementos = new ArrayList<>();
    }

    public int getIdSenha() {
        return idSenha;
    }

    public String getPrato() {
        return prato;
    }

    public String getSombremesa() {
        return sombremesa;
    }

    public double getPreco() {
        return preco;
    }

    public int getIdRefeicao(){
        return idRefeicao;
    }

    public void setPrato(String prato) {
        this.prato = prato;
    }

    public void setSombremesa(String sombremesa) {
        this.sombremesa = sombremesa;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setIdRefeicao(int idRefeicao) {
        this.idRefeicao = idRefeicao;
    }

    public ArrayList<Complemento> getComplementos() {
        return complementos;
    }

    public void addComplemento(Complemento complemento){
        complementos.add(complemento);
    }

    public void removeComplemento(Complemento complemento){
        complementos.remove(complemento);
    }

    public void setComplementos(ArrayList<Complemento> complementos) {
        this.complementos = complementos;
    }
}
