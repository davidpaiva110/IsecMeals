package modelo;

import modelo.Password.PasswordUtils;

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
        return database.getFavoritos(utilizador.getNumeroUtilizador());
    }

    /**
     * @param descricaoFavorito
     * @return true se adicionado com sucesso | false caso contrário
     */
    @Override
    public boolean addFavorito(String descricaoFavorito, int tipo) throws SQLException {
        return database.addFavorito(descricaoFavorito, tipo, utilizador.getNumeroUtilizador());
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
     * @param novaSenha
     * @return true se alterada com sucesso | false caso contrario
     */
    @Override
    public boolean changeSenha(Senha novaSenha) throws Exception{
        double precoSenhaAntiga=database.changeSenha(novaSenha);
        double dif=precoSenhaAntiga-novaSenha.getPreco();
        if(dif>0) database.addSaldo(utilizador.getNumeroUtilizador(), dif);
        else if(dif<0) database.removeSaldo(utilizador.getNumeroUtilizador(), Math.abs(dif));
        return true;
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
     * @param numeroUtilizador a remover
     * @return true se removido com sucesso | false caso contrário
     */
    @Override
    public boolean removeUtilizador(int numeroUtilizador) throws Exception {
        return  database.removeUtilizador(numeroUtilizador);
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
    public boolean addNovaRefeicao(Refeicao novaRefeicao) throws Exception {
        return database.addRefeicao(novaRefeicao);
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
    public boolean cancelRefeicao(int idRefeicao) throws Exception {
        ArrayList<Integer> senhas = new ArrayList<>();
        senhas= database.getSenhasDaRefeicao(idRefeicao);
        for(Integer i: senhas)
            this.cancelSenha(i);
        return  database.removeRefeicao(idRefeicao) ;
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



    /**
     * Verifica se o utilizador prefere pratos de carne ou peixe
     * @return 0 - Se preferir carne ou for indiferente | 1 - Se preferir peixe
     */
    public int getPreferenciaPratoUser() {
        try {
            int carne=database.getNumPratosFavCarne(utilizador.getNumeroUtilizador());
            int peixe=database.getNumPratosFavPeixe(utilizador.getNumeroUtilizador());
            if(carne>=peixe) return 0;
            else return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    /**
     *
     * @param userNumber : Número do novo utilizador
     * @param nome : Nome do novo utilizador
     * @param saldo : Saldo do novo utilizador
     * @return password do utilizador
     * @throws SQLException
     */
    public String addNewUser(int userNumber, String nome, double saldo) throws SQLException {
        Utilizador uti = new Utilizador(userNumber, nome, saldo);
        String password = uti.getPassword();  // Esta password é para ser enviada ao utilizador por email
        uti.setPassword(PasswordUtils.generateSecurePassword(uti.getPassword(), PasswordUtils.getSalt()));
        boolean resultado = database.addNewUser(uti);
        return password;
    }

    /**
     *
     * @return lsita de todos os complementos existentes
     * @throws SQLException
     */
    public ArrayList<Complemento> getTodosComplementos() throws SQLException {
        return database.getTodosComplementos();
    }

    public ArrayList<Utilizador> getUserAdmin() throws SQLException {
        return database.getUserAdmin();
    }



}
