package modelo;

public class Favoritos {
    private String Prato;
    private int idFavorito;
    private int tipo;

    public Favoritos(int idfavorito, String prato) {
        this.idFavorito=idfavorito;
        this.Prato=prato;
    }

    public Favoritos(int idFavorito, String prato, int tipo) {
        Prato = prato;
        this.idFavorito = idFavorito;
        this.tipo = tipo;
    }

    public String getPrato(){return Prato;}
    public int getIdFavorito(){return idFavorito;}
    public int getTipo() {
        return tipo;
    }
}
