package modelo;

public class Utilizador {

    private long numeroUtilizador;
    private String password;
    //Isto que está para baixo, não sei se vai ser usado. dado que vamos recorrer sempre à base de dodos
    private String nome;
    private Double saldo; //Este pode não ser necessário
    private boolean eUtilizador; // true, se for administrador | false, se for utilizador




    /****************************************
     *           Getters and Setters
     ****************************************/

    public long getNumeroUtilizador() {
        return numeroUtilizador;
    }

    public void setNumeroUtilizador(long numeroUtilizador) {
        this.numeroUtilizador = numeroUtilizador;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public boolean iseUtilizador() {
        return eUtilizador;
    }
}
