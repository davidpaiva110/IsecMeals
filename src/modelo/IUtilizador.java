package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/*************************************************************************************************/
/********************************************* ISECMEALS *****************************************/
/* Autor: David Paiva                                                                            */
/* Autor: Rafael Sá                                                                              */
/* Autor: Francisco Silva                                                                        */
/* Autor: Ricardo Roque                                                                          */
/* Data de Edição: 12/05/2019                                                                    */
/* Ficheiro: IUtilizador.java                                                                    */
/* Descrição: Protótipos dos métodos relativos a ações a fazer pelo utilizador ou administrador  */
/*************************************************************************************************/

public interface IUtilizador {

    public boolean login(int user, String password) throws Exception;
    public void logout();
    public double getSaldoUtilizador() throws SQLException;
    public  int gettipoUtilizador();

    // ==== Pratos Favoritos ====
    public boolean removeFavorito(int idFavorito) throws Exception;
    public List getFavoritos() throws SQLException;
    public boolean addFavorito(String descricaoFavorito);

    // ==== Senhas ====
    public ArrayList<Senha> getSenhasCompradas() throws SQLException;
    public Refeicao getRefeicao(int id) throws SQLException;
    public boolean buySenha(Refeicao dadosSenha);
    public boolean cancelSenha(int idSenha) throws Exception;
    public boolean changeSenha(Refeicao novosDadosSenha);

    // ===== Funcionalidades do Administrador =====
    public double setNovoSaldoUtilizador();
    public boolean addUtilizador(Utilizador novoUtilizador);
    public boolean removeUtilizador(int numeroUtilizador);


}
