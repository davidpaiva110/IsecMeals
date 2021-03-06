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

import java.util.ArrayList;
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
    private int horario;   // 1 - Jantar | 0 - Almoço
    private String data;
    private ArrayList<Complemento> complementos;
    private boolean jaComprada=false;

    public Refeicao(int idRefeicao, String sopa, String pratoCarne, String pratoPeixe, String sombremesa1, String sombremesa2, double preco, int almocoJantar, String data, ArrayList<Complemento> complementos) {
        this.idRefeicao = idRefeicao;
        this.sopa = sopa;
        this.pratoCarne = pratoCarne;
        this.pratoPeixe = pratoPeixe;
        this.sombremesa1 = sombremesa1;
        this.sombremesa2 = sombremesa2;
        this.preco = preco;
        this.horario = almocoJantar;
        this.data = data;
        this.complementos = complementos;
    }
    public Refeicao( String sopa, String pratoCarne, String pratoPeixe, String sombremesa1, String sombremesa2, double preco, int almocoJantar, String data, ArrayList<Complemento> comp) {
        this.idRefeicao = idRefeicao;
        this.sopa = sopa;
        this.pratoCarne = pratoCarne;
        this.pratoPeixe = pratoPeixe;
        this.sombremesa1 = sombremesa1;
        this.sombremesa2 = sombremesa2;
        this.preco = preco;
        this.horario = almocoJantar;
        this.data = data;
        this.complementos=comp;
    }

    public Refeicao(int idRefeicao, String sopa, String pratoCarne, String pratoPeixe, String sombremesa1, String sombremesa2, double preco, int almocoJantar, String data, ArrayList<Complemento> complementos, boolean comp) {
        this.idRefeicao = idRefeicao;
        this.sopa = sopa;
        this.pratoCarne = pratoCarne;
        this.pratoPeixe = pratoPeixe;
        this.sombremesa1 = sombremesa1;
        this.sombremesa2 = sombremesa2;
        this.preco = preco;
        this.horario = almocoJantar;
        this.data = data;
        this.complementos = complementos;
        this.jaComprada=comp;
    }

    public Refeicao(int idRefeicao, String sopa, String pratoCarne, String pratoPeixe, String sombremesa1, String sombremesa2, double preco, int horario, String data) {
        this.idRefeicao = idRefeicao;
        this.sopa = sopa;
        this.pratoCarne = pratoCarne;
        this.pratoPeixe = pratoPeixe;
        this.sombremesa1 = sombremesa1;
        this.sombremesa2 = sombremesa2;
        this.preco = preco;
        this.horario = horario;
        this.data = data;
        this.complementos = new ArrayList<>();
    }

    public Refeicao() {
    }

    public int getIdRefeicao() {
        return idRefeicao;
    }

    public void setIdRefeicao(int idRefeicao) {
        this.idRefeicao = idRefeicao;
    }

    public String getSopa() {
        return sopa;
    }

    public void setSopa(String sopa) {
        this.sopa = sopa;
    }


    public String getPratoCarne() {
        return pratoCarne;
    }

    public void setPratoCarne(String pratoCarne) {
        this.pratoCarne = pratoCarne;
    }

    public String getPratoPeixe() {
        return pratoPeixe;
    }

    public void setPratoPeixe(String pratoPeixe) {
        this.pratoPeixe = pratoPeixe;
    }

    public String getSombremesa1() {
        return sombremesa1;
    }

    public void setSombremesa1(String sombremesa1) {
        this.sombremesa1 = sombremesa1;
    }

    public String getSombremesa2() {
        return sombremesa2;
    }

    public void setSombremesa2(String sombremesa2) {
        this.sombremesa2 = sombremesa2;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getAlmocoJantar() {
        return horario;
    }

    public void setAlmocoJantar(int almocoJantar) {
        this.horario = almocoJantar;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<Complemento> getComplementos() {
        return complementos;
    }

    public boolean isJaComprada() {
        return jaComprada;
    }
}
