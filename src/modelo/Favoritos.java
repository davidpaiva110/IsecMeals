package modelo;

public class Favoritos {
    private String Prato;
    private int idFavorito;

    public Favoritos(int idfavorito, String prato) {
        this.idFavorito=idfavorito;
        this.Prato=prato;
    }

    public String getPrato(){return Prato;}
    public int getIdFavorito(){return idFavorito;}
}
