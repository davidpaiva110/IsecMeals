package modelo;

import java.util.ArrayList;
import java.util.List;

public class Modelo  implements IUtilizador, IEmenta{

    private Utilizador utilizador;
    private List<Refeicao> ementa;

    /**
     * Construtor
     */
    public Modelo(){
        ementa = new ArrayList<>();
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
    public boolean login(String user, String password){
        //Implementar a chamada à base de dados para realizar o login
        //if(/*a flag for qualquer coisa é utilizador*/)
            //this.utilizador = new Utilizador(user, flag); //recebe o username e a flag para sabermos se é ou não administrador
        return true;
    }

    /**
     * @param idFavorito do favorito que se pretende remover da lista de favoritos
     * @return true se removido com sucesso | false se não foi possível remover
     */
    @Override
    public boolean removerFavorito(int idFavorito) {
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
    public boolean adicianoFavorito(String descricaoFavorito) {
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
    public boolean comprarSenha(Refeicao dadosSenha) {
        return false;
    }

    /**
     * @param idSenha a cancelar
     * @return true se cancelada com sucesso | false caso contrário
     */
    @Override
    public boolean cancelarSenha(int idSenha) {
        return false;
    }

    /**
     * @param novosDadosSenha
     * @return true se alterada com sucesso | false caso contrario
     */
    @Override
    public boolean alterarSenha(Refeicao novosDadosSenha) {
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
    public boolean adicionaUtilizador(Utilizador novoUtilizador) {
        return false;
    }

    /**
     * Funcionalidade do Administrador
     * @param numeroUtilizador a remover
     * @return true se removido com sucesso | false caso contrário
     */
    @Override
    public boolean removeUtilizador(long numeroUtilizador) {
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
    public boolean adicionaNovaRefeicao(Refeicao novaRefeicao) {
        return false;
    }

    /**
     * @param dadosRefeicao
     * @return true se alterado com sucesso | false caso contrário
     */
    @Override
    public boolean alteraRefeicao(Refeicao dadosRefeicao) {
        return false;
    }

    /**
     *
     * @param idRefeicao
     * @return true se cancelado com sucesso | false caso contrário
     */
    @Override
    public boolean cancelarRefeicao(int idRefeicao) {
        return false;
    }


}