package modelo;

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
    public double getSaldoUtilizador();
    public  int gettipoUtilizador();

    // ==== Pratos Favoritos ====
    public boolean removeFavorito(int idFavorito);
    public List getFavoritos();
    public boolean addFavorito(String descricaoFavorito);

    // ==== Senhas ====
    public List getHistoricoSenhas();
    public boolean buySenha(Refeicao dadosSenha);
    public boolean cancelSenha(int idSenha);
    public boolean changeSenha(Refeicao novosDadosSenha);

    // ===== Funcionalidades do Administrador =====
    public double setNovoSaldoUtilizador();
    public boolean addUtilizador(Utilizador novoUtilizador);
    public boolean removeUtilizador(int numeroUtilizador);


}
