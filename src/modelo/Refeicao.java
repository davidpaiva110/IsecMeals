package modelo;


/**********************************************/
/***************** ISECMEALS ******************/
/* Autor: David Paiva                         */
/* Autor: Rafael Sá                           */
/* Autor: Francisco Silva                     */
/* Autor: Ricardo Roque                       */
/* Data de Edição: 12/05/2019                 */
/* Ficheiro: Refeicao.java                    */
/* Descrição: Representa uma refeição         */

import java.util.Calendar;
import java.util.Date;

/**********************************************/


public class Refeicao {
    private int idRefeicao;
    private String sopa;
    private String pratoCarne;
    private String pratoPeixe;
    private String sombremesa1;
    private String sombremesa2;
    private double preco;
    private int horario;   // 0 - ??? | 1 - ????
    private Date data;

    public Refeicao(int idRefeicao, String sopa, String pratoCarne, String pratoPeixe, String sombremesa1, String sombremesa2, double preco, int almocoJantar, Date data) {
        this.idRefeicao = idRefeicao;
        this.sopa = sopa;
        this.pratoCarne = pratoCarne;
        this.pratoPeixe = pratoPeixe;
        this.sombremesa1 = sombremesa1;
        this.sombremesa2 = sombremesa2;
        this.preco = preco;
        this.horario = almocoJantar;
        this.data = data;
    }

    public int getIdRefeicao() {
        return idRefeicao;
    }

    public String getSopa() {
        return sopa;
    }

    public String getPratoCarne() {
        return pratoCarne;
    }

    public String getPratoPeixe() {
        return pratoPeixe;
    }

    public String getSombremesa1() {
        return sombremesa1;
    }

    public String getSombremesa2() {
        return sombremesa2;
    }

    public double getPreco() {
        return preco;
    }

    public int getHorario() {
        return horario;
    }

    public Date getData() {
        return data;
    }
}
