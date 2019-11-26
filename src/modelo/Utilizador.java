package modelo;


/**********************************************/
/***************** ISECMEALS ******************/
/* Autor: David Paiva                         */
/* Autor: Rafael Sá                           */
/* Autor: Francisco Silva                     */
/* Autor: Ricardo Roque                       */
/* Data de Edição: 12/05/2019                 */
/* Ficheiro: Utilizador.java                  */
/* Descrição: Representa um utilizador        */
/**********************************************/


public class Utilizador {

    private int numeroUtilizador;
    private String password;
    //Isto que está para baixo, não sei se vai ser usado. dado que vamos recorrer sempre à base de dodos
    private String nome;
    private double saldo; //Este pode não ser necessário
    private int eUtilizador; // 0, se for utilizador| 1 se for administrador
    public Utilizador(int numeroUtilizador, int eUtilizador ) {
        this.numeroUtilizador = numeroUtilizador;
        this.eUtilizador = eUtilizador;
    }

    /****************************************
     *           Getters and Setters
     ****************************************/

    public int getNumeroUtilizador() {
        return numeroUtilizador;
    }

    public void setNumeroUtilizador(int numeroUtilizador) {
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

    public int geteUtilizador() {
        return eUtilizador;
    }

    public void seteUtilizador(int eut){this.eUtilizador=eut;}
}
