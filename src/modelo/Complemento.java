package modelo;

public class Complemento {
    private int idComplemento;
    private String nomeComplemento;
    private  double preco;

    public Complemento(int idComplemento, String nomeComplemento, double preco) {
        this.idComplemento = idComplemento;
        this.nomeComplemento = nomeComplemento;
        this.preco = preco;
    }

    public int getIdComplemento() {
        return idComplemento;
    }

    public String getNomeComplemento() {
        return nomeComplemento;
    }

    public double getPreco() {
        return preco;
    }
}
