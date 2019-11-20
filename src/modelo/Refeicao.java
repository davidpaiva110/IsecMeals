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
    private int almocoJantar;   // 0 - ??? | 1 - ????
    private Date data;

    public Refeicao(int idRefeicao, String sopa, String pratoCarne, String pratoPeixe, String sombremesa1, String sombremesa2, double preco, int almocoJantar, Date data) {
        this.idRefeicao = idRefeicao;
        this.sopa = sopa;
        this.pratoCarne = pratoCarne;
        this.pratoPeixe = pratoPeixe;
        this.sombremesa1 = sombremesa1;
        this.sombremesa2 = sombremesa2;
        this.preco = preco;
        this.almocoJantar = almocoJantar;
        this.data = data;
    }
}
