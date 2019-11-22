package modelo;

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
/* Ficheiro: Modelo.java                      */
/* Descrição: Lógica da Aplicação             */
/**********************************************/



public class Modelo  implements IUtilizador, IEmenta{

    private Utilizador utilizador;
    private List<Refeicao> ementa;
    private ComunicacaoBD database;

    /**
     * Construtor
     */
    public Modelo() throws SQLException, ClassNotFoundException {
        database=new ComunicacaoBD();
        ementa = new ArrayList<>();
        database.connectToDatabase();
    }

    /**
     * @return Saldo do utilizador
     */
    @Override
    public double getSaldoUtilizador(){
        //Implementar a chamada à base de dados para receber o saldo do utilizador
        return 0.0;
    }

    /**
     * @param user - número de utilizador
     * @param password - password do utilizador
     * @return
     */
    @Override
    public boolean login(int user, String password) throws Exception{
        boolean resp=database.login(user, password);
        if(resp){
            //this.utilizador = new Utilizador(user, flag);
        }
        return resp;
    }

    /**
     * @param idFavorito do favorito que se pretende remover da lista de favoritos
     * @return true se removido com sucesso | false se não foi possível remover
     */
    @Override
    public boolean removeFavorito(int idFavorito) {
        return false;
    }

    /**
     * @return lista com os favoritos do utilizador
     */
    @Override
    public List getFavoritos() {
        return null;
    }

    /**
     * @param descricaoFavorito
     * @return true se adicionado com sucesso | false caso contrário
     */
    @Override
    public boolean addFavorito(String descricaoFavorito) {
        return false;
    }

    /**
     * @return lista com o histórico de senhas compradas do utilizador
     */
    @Override
    public List getHistoricoSenhas() {
        return null;
    }

    /**
     * @param dadosSenha
     * @return true se adicionado com sucesso | false caso contrário
     */
    @Override
    public boolean buySenha(Refeicao dadosSenha) {
        return false;
    }

    /**
     * @param idSenha a cancelar
     * @return true se cancelada com sucesso | false caso contrário
     */
    @Override
    public boolean cancelSenha(int idSenha) {
        return false;
    }

    /**
     * @param novosDadosSenha
     * @return true se alterada com sucesso | false caso contrario
     */
    @Override
    public boolean changeSenha(Refeicao novosDadosSenha) {
        return false;
    }


    /**
     * Funcionalidade do Administrador
     * @return o valor do novo Saldo do Utilizador
     */
    @Override
    public double setNovoSaldoUtilizador() {
        return 0;
    }

    /**
     * Funcionalidade do Administrador
     * @param novoUtilizador dados do novo utilizador
     * @return true se foi adicionado com sucesso | false caso contrário
     */
    @Override
    public boolean addUtilizador(Utilizador novoUtilizador) {
        return false;
    }

    /**
     * Funcionalidade do Administrador
     * @param numeroUtilizador a remover
     * @return true se removido com sucesso | false caso contrário
     */
    @Override
    public boolean removeUtilizador(int numeroUtilizador) {
        return false;
    }

    /**
     * @return uma lista com a ementa para os dias disponíveis
     */
    @Override
    public List getEmenta() {
        return null;
    }

    /**
     * @param novaRefeicao
     * @return true se foi adicionado com sucesso | false caso contrário
     */
    @Override
    public boolean addNovaRefeicao(Refeicao novaRefeicao) {
        return false;
    }

    /**
     * @param dadosRefeicao
     * @return true se alterado com sucesso | false caso contrário
     */
    @Override
    public boolean changeRefeicao(Refeicao dadosRefeicao) {
        return false;
    }

    /**
     *
     * @param idRefeicao
     * @return true se cancelado com sucesso | false caso contrário
     */
    @Override
    public boolean cancelRefeicao(int idRefeicao) {
        return false;
    }


}
