package modelo;

import java.util.Date;

public class Senha {
    private int idSenha;
    private String prato;
    private String sombremesa;
    private double preco;
    private int idRefeicao;

    public Senha(int idSenha, String prato, String sombremesa, double preco, int idRef) {
        this.idSenha = idSenha;
        this.prato = prato;
        this.sombremesa = sombremesa;
        this.preco = preco;
        this.idRefeicao=idRef;
    }

    public Senha() {
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
}
