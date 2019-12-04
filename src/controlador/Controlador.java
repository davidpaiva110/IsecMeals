package controlador;

import modelo.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**********************************************/
/***************** ISECMEALS ******************/
/* Autor: David Paiva                         */
/* Autor: Rafael Sá                           */
/* Autor: Francisco Silva                     */
/* Autor: Ricardo Roque                       */
/* Data de Edição: 12/05/2019                 */
/* Ficheiro: Controlador.java                 */
/* Descrição: Delega os métodos do modelo     */
/**********************************************/


public class Controlador {

    private Modelo modelo;



    public Controlador() throws ClassNotFoundException, SQLException {

        this.modelo = new Modelo();
    }

    public boolean login(int number, String password) throws Exception{
        return modelo.login(number, password);
    }
    public int geteutilizador(){return  modelo.gettipoUtilizador();}

    public void logout() {
        modelo.logout();
    }

    public ArrayList<Refeicao> getEmenta() throws SQLException {
        return modelo.getEmenta();
    }
    public ArrayList<Senha> getSenhasCompradas() throws SQLException {
        return modelo.getSenhasCompradas();
    }
    public ArrayList<RefeicaoAdmin> getSenhasCompradasAdmin() throws SQLException {
        return modelo.getSenhasCompradasAdmin();
    }
    public ArrayList<Favoritos> getFavoritos() throws SQLException {
        return modelo.getFavoritos();
    }

    public Refeicao getRefeicao(int id) throws SQLException {
        return modelo.getRefeicao(id);
    }


    public boolean cancelSenha(int id) throws Exception{
        return modelo.cancelSenha(id);
    }

    public double getSaldo() throws SQLException {
        return modelo.getSaldoUtilizador();
    }

    public boolean buySenha(Senha senha) throws SQLException {
        return modelo.buySenha(senha);
    }

    public boolean hasMoreThan48Hours(int idRefeicao) {
        return modelo.hasMoreThan48Hours(idRefeicao);
    }

    public boolean RemoveFavorito(int id) throws Exception {
        return modelo.removeFavorito(id);
    }

    public Senha getSenha(int idSenha) throws SQLException {
        return modelo.getSenha(idSenha);
    }

    public boolean addFavorito(String prato, int tipo) throws SQLException {
        return modelo.addFavorito(prato, tipo);
    }

    public boolean updateSenha(Senha senha) throws Exception {
        return modelo.changeSenha(senha);
    }

    public int getPreferenciaPratoUser() {
        return modelo.getPreferenciaPratoUser();
    }
}
