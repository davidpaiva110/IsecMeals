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

    private long numeroUtilizador;
    private String password;
    //Isto que está para baixo, não sei se vai ser usado. dado que vamos recorrer sempre à base de dodos
    private String nome;
    private double saldo; //Este pode não ser necessário
    private int eUtilizador; // 0, se for administrador | 1, se for utilizador




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

    public int iseUtilizador() {
        return eUtilizador;
    }
}
