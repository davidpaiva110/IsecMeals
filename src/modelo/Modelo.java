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
    public double getSaldoUtilizador() throws SQLException {
        return database.getSaldo(utilizador.getNumeroUtilizador());
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
            this.utilizador = database.getUtilizador(user);
        }
        return resp;
    }
    public  int gettipoUtilizador(){
    return  utilizador.geteUtilizador();
}
    @Override
    public void logout() {
        utilizador=null;
    }

    /**
     * @param idFavorito do favorito que se pretende remover da lista de favoritos
     * @return true se removido com sucesso | false se não foi possível remover
     */
    @Override
    public boolean removeFavorito(int idFavorito) throws Exception {

            return database.removeFavorito(idFavorito);

    }

    /**
     * @return lista com os favoritos do utilizador
     */
    public ArrayList<Favoritos> getFavoritos() throws SQLException {
        return database.getFavoritos();
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
     * @return lista com as senhas compradas do utilizador
     */
    public ArrayList<Senha> getSenhasCompradas() throws SQLException {
        return database.getSenhas(utilizador.getNumeroUtilizador());
    }

    /**
     *
     * @param id identificador da refeição
     * @return refeição pretendida
     */
    @Override
    public Refeicao getRefeicao(int id) throws SQLException {
        return database.getRefeicao(id);
    }

    /**
     * @param dadosSenha
     * @return true se adicionado com sucesso | false caso contrário
     */
    @Override
    public boolean buySenha(Senha dadosSenha) throws SQLException {
        boolean state = database.addSenha(dadosSenha, utilizador.getNumeroUtilizador());
        if(state == true)
            database.removeSaldo(utilizador.getNumeroUtilizador(), dadosSenha.getPreco());
        return  state;
    }

    /**
     * Efetua o cancelamento de uma senha de refeição, atualizando também o saldo do utilizador
     * @param idSenha ID da senha a cancelar
     * @return true - se o cancelamento for efetuado com sucesso
     * @throws Exception em caso de Erro
     */
    @Override
    public boolean cancelSenha(int idSenha) throws Exception{
        double precoSenha=database.getPrecoSenhaComprada(idSenha);
        boolean res=database.deleteSenha(idSenha);
        database.addSaldo(utilizador.getNumeroUtilizador(), precoSenha);
        return res;
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
    public ArrayList<Refeicao> getEmenta() throws SQLException {
        return database.getEmenta(utilizador.getNumeroUtilizador());
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
    public boolean cancelRefeicao(int idRefeicao){
        return false;
    }

    /**
     * Verifica se ainda falta mais de 48 horas para uma refeição
     * @param idRefeicao Refeição a verificar
     * @return true - se faltar mais de 48 horas | false - se faltar menos de 48 horas
     */
    public boolean hasMoreThan48Hours(int idRefeicao) {
        return database.hasMoreThan48Hours(idRefeicao);
    }

    public ArrayList<RefeicaoAdmin> getSenhasCompradasAdmin() throws SQLException {
        return database.getSenhasCompradasAdmin();
    }

    /**
     * Devolve uma Senha
     * @param idSenha ID da senha a devolver
     * @return objeto do tipo Senha
     */
    public Senha getSenha(int idSenha) throws SQLException {
        return database.getSenha(idSenha);
    }
}
