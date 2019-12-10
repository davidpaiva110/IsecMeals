import modelo.*;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModeloTest {

    private Utilizador utilizador;
    private List<Refeicao> ementa1;
    private ComunicacaoBD database;

    ModeloTest() throws SQLException, ClassNotFoundException {
        database = new ComunicacaoBD();
        ementa1 = new ArrayList<>();
        database.connectToDatabase();
    }

    /**
     * Devolve o saldo do utilizador
     * @return true - se o saldo for devolvido com sucesso | false - se o saldo for devolvido sem sucesso
     */
    @Test
    void getSaldoUtilizador() throws SQLException {
        utilizador = database.getUtilizador(21270877);
        double saldo = utilizador.getSaldo();
        assertEquals(2.48,saldo,"getSaldoUtilizador");
    }

    /**
     * Verifica se o login foi efetuado sem sucesso, username e password corretos
     * @return true - se login efetuado com sucesso | false - se login efetuado sem sucesso
     */
    @Test
    void login1() throws Exception {
        boolean resp = database.login(21270564, "1234");
        assertTrue(resp, "login1()");
    }

    /**
     * Verifica se o login foi efetuado com sucesso, password errada
     * @return true - se login efetuado com sucesso | exception - se login efetuado sem sucesso
     */
    @Test
    void login2() throws Exception {
        try
        {
            boolean resp = database.login(21270564, "12345");
        }
        catch( final Exception e )
        {
            final String msg = "Password incorreta!";
            assertEquals(msg, e.getMessage());
        }
    }

    /**
     * Verifica se o login foi efetuado com sucesso, username não registado
     * @return true - se login efetuado com sucesso | exception - se login efetuado sem sucesso
     */
    @Test
    void login3() throws Exception {
        try
        {
            boolean resp = database.login(90000001, "1234");
        }
        catch( final Exception e )
        {
            final String msg = "O número de utilizador não está registado!";
            assertEquals(msg, e.getMessage());
        }
    }

    /**
     * Verifica se o retorno do tipo de utilizador foi efetuado com sucesso
     * @return 0 - se foi retornado com sucesso | 1 - se foi retornado efetuado sem sucesso
     */
    @Test
    void gettipoUtilizador1() throws SQLException {
        utilizador = database.getUtilizador(21270877);
        assertEquals(0, utilizador.geteUtilizador(), "gettipoUtilizador1()");
    }

    /**
     * Verifica se o retorno do tipo de utilizador (administrador) foi efetuado com sucesso
     * @return 1 - se foi retornado com sucesso | 0 - se foi retornado efetuado sem sucesso
     */
    @Test
    void gettipoUtilizador2() throws SQLException {
        utilizador = database.getUtilizador(21270547);
        assertEquals(1, utilizador.geteUtilizador(), "gettipoUtilizador2()");
    }

    /**
     * Verifica se o logout do utilizador foi efetuado com sucesso
     * @return true - se foi efetuado com sucesso | false - se foi efetuado sem sucesso
     */
    @Test
    void logout() throws SQLException {
        //ERRO
        utilizador = database.getUtilizador(21270877);
        logout();//nao devia dar (utilizador ficava = null) ?
        assertNull(utilizador, "logout()");
    }

    /**
     * Verifica se a remoção de favorito está a diminuir o tamanho do array com sucesso
     * @return true - se foi efetuado com sucesso | false - se foi efetuado sem sucesso
     */
    @Test
    void removeFavorito() throws Exception {
        ArrayList<Favoritos> favorito1 = database.getFavoritos(21270877);
        int nf1 = favorito1.size();
        database.removeFavorito(3);
        favorito1 = database.getFavoritos(21270877);
        assertEquals(nf1-1, favorito1.size(), "removeFavorito()");
    }

    /**
     * Verifica se a lista de favoritos está correta
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getFavoritos1() throws SQLException {
        ArrayList<Favoritos> favoritos1 =  database.getFavoritos(21270877);
        boolean isEqual;
        if((favoritos1.get(0).getPrato().equals("Grelhado Misto")) && (favoritos1.get(1).getPrato().equals("Costeletinha de Novilho"))
            && (favoritos1.get(2).getPrato().equals("Picado de Frango")))
            isEqual = true;
        else isEqual = false;
        assertTrue(isEqual, "getFavoritos1()");

    }

    /**
     * Verifica se está a ser returnado o numero correto de favoritos
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getFavoritos2() throws SQLException {
        ArrayList<Favoritos> favoritos1 =  database.getFavoritos(21270877);
        assertEquals(2, favoritos1.size(), "getFavoritos2()");
    }

    /**
     * Verifica se a adição de favoritos está a aumentar o tamanho do array com sucesso
     * @return true - se foi aumentado com sucesso | false - se foi aumentado sem sucesso
     */
    @Test
    void addFavorito1() throws SQLException {
        ArrayList<Favoritos> favoritos1 = database.getFavoritos(21270877);
        int tam1 = favoritos1.size();
        database.addFavorito("Fav1",0,21270877);
        favoritos1 = database.getFavoritos(21270877);
        assertEquals(tam1+1, favoritos1.size(),"addFavorito()");
    }

    /**
     * Verifica se o programa está a adicionar o elemento certo
     * @return true - se foi adicionar com sucesso | false - se foi adicionar sem sucesso
     */
    @Test
    void addFavorito2() throws SQLException {
        boolean isEqual = true;
        ArrayList<Favoritos> favoritos1 = database.getFavoritos(21270877);
        if (!favoritos1.get(favoritos1.size()-1).getPrato().equals("Fav1")) isEqual = false;
        if (favoritos1.get(favoritos1.size()-1).getTipo() != 0) isEqual = false;
        assertTrue(isEqual,"addFavorito()");
    }

    /**
     * Verifica se a lista de senhas compradas está a ser retornada com sucesso, utilizador so tem 1 senha
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getSenhasCompradas1() throws SQLException {
        utilizador = database.getUtilizador(21270877);
        ArrayList<Senha> senhas1 = new ArrayList<>();
        Senha senha1 = new Senha(3,"Picado de Frango","Fruta",2.65,3);
        senhas1.add(senha1);
        boolean isEqual = false;
        if(senhas1.get(0).getPrato().equals("Picado de Frango") && senhas1.get(0).getPreco() == 2.65
                && senhas1.get(0).getSombremesa().equals("Fruta") && senhas1.get(0).getIdRefeicao() == 3)
            isEqual = true;
        assertTrue(isEqual, "getSenhasCompradas1()");
    }

    /**
     * Verifica se a lista de senhas compradas está a ser retornada com sucesso, utilizador tem mais que 1 senha
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getSenhasCompradas2() throws SQLException {
        utilizador = database.getUtilizador(21270564);
        ArrayList<Senha> senhas1 = new ArrayList<>();
        Senha senha1 = database.getSenha(1);
        Senha senha2 = database.getSenha(2);
        senhas1.add(senha1);senhas1.add(senha2);
        boolean isEqual = false;
        if(senhas1.get(0).getPrato().equals("Grelhado Misto") && senhas1.get(0).getPreco() == 2.65
                && senhas1.get(0).getSombremesa().equals("Fruta") && senhas1.get(0).getIdRefeicao() == 1
        && senhas1.get(1).getPrato().equals("Salmao Grelhado") && senhas1.get(1).getPreco() == 2.65
                && senhas1.get(1).getSombremesa().equals("Fruta") && senhas1.get(1).getIdRefeicao() == 1)
            isEqual = true;
        assertTrue(isEqual, "getSenhasCompradas2()");
    }

    /**
     * Verifica se a refeição está ser retornada com sucesso
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getRefeicao1() throws SQLException {
        Refeicao refeicao = database.getRefeicao(2);
        boolean isEqual = false;
        if(refeicao.getIdRefeicao() == 2 && refeicao.getPratoCarne().equals("Costeletinha de Novilho") && refeicao.getPratoPeixe().equals("Truta do Nilo") &&
                refeicao.getAlmocoJantar() == 0 && refeicao.getSopa().equals("Sopa de Feijão") && refeicao.getSombremesa1().equals("Fruta") &&
                refeicao.getSombremesa2().equals("Gelatina") && refeicao.getPreco()==2.65 && refeicao.getData().equals("2019-12-6") /*&& refeicao.getComplementos()*/)
            isEqual = true;
        assertTrue(isEqual, "getRefeicao1()");
    }

    /**
     * Verifica se a senha está a ser comprada com sucesso
     * @return true - se foi comprada com sucesso | false - se foi comprada sem sucesso
     */
    @Test
    void buySenha() throws SQLException {
        utilizador = database.getUtilizador(21270877);
        boolean isEqual = false;
        Senha senha1 = new Senha(4,"Costeletinha de Novilho","Fruta",2.65,2);
        boolean state = database.addSenha(senha1, 21270877);
        if(state == true)
            isEqual = true;
        assertTrue(isEqual, "buySenha()");
    }

    /**
     * Verifica se a senha está ser cancelada com sucesso
     * @return true - se foi cancelada com sucesso | false - se foi cancelada sem sucesso
     */
    @Test
    void cancelSenha1() throws Exception {
        boolean res = database.deleteSenha(4);
        assertTrue(res, "cancelSenha1()");
    }

    /**
     * Verifica se o dinheiro esta a ser devolvido ao utilizador ao cancelar a senha
     * @return true - se foi cancelada com sucesso | false - se foi cancelada sem sucesso
     */
    @Test
    void cancelSenha2() throws Exception {
        utilizador = database.getUtilizador(21270877);
        double saldo1 = utilizador.getSaldo();
        saldo1 = saldo1 + 2.65;
        double precoSenha = database.getPrecoSenhaComprada(4);
        database.addSaldo(utilizador.getNumeroUtilizador(), precoSenha);
        double saldo2 = utilizador.getSaldo();
        System.out.println(saldo2);//nao sei o porque de nao adicionar
        assertEquals(saldo1, saldo2, "cancelSenha2()");
    }

    /**
     * Verifica se a senha está ser alterada com sucesso
     * @return true - se foi alterada com sucesso | false - se foi alterada sem sucesso
     */
    @Test
    void changeSenha() throws Exception {
        Senha senha1 = database.getSenha(4);
        double precoSenhaAntiga = database.changeSenha(senha1);
        Senha senha3 = database.getSenha(3);
        double dif = precoSenhaAntiga - senha3.getPreco();
        //change senha - ainda nao implementada no dia 09/12
        //if(dif>0) database.addSaldo(utilizador.getNumeroUtilizador(), dif);
        //else if(dif<0) database.removeSaldo(utilizador.getNumeroUtilizador(), Math.abs(dif));
    }

    /**
     * Verifica se o saldo do utilizador está a ser alterado com sucesso
     * @return true - se foi alterado com sucesso | false - se foi alterado sem sucesso
     */
    @Test
    void setNovoSaldoUtilizador() {
    }

    /**
     * Verifica se o utilizador está ser adicionado com sucesso
     * @return true - se foi adicionado com sucesso | false - se foi adicionado sem sucesso
     */
    @Test
    void addUtilizador() {
    }

    /**
     * Verifica se o utilizador está ser removido com sucesso
     * @return true - se foi removido com sucesso | false - se foi removido sem sucesso
     */
    @Test
    void removeUtilizador() {
    }

    /**
     * Verifica se a ementa está ser retornada com sucesso
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    //get ementa, 1 dia tem 2 refeições
    void getEmenta() throws SQLException {
        Refeicao refeicao1 = new Refeicao(2,"Sopa de Feijão","Costeletinha de Novilho","Truta do Nilo","Fruta","Gelatina",2.65,0,"2019-12-6");
        Refeicao refeicao2 = new Refeicao(3,"Sopa de Ceneura","Picado de Frango","Polvo Frito","Fruta","Arroz Doce",2.65,1,"2019-12-6");
        ementa1.add(refeicao1);
        ementa1.add(refeicao2);
        ArrayList<Refeicao> ementa2 = new ArrayList<>();
        boolean isEqual = ementa1.equals(ementa2);
        assertTrue(isEqual, "getEmenta()");
    }

    /**
     * Verifica se a refeição está ser adicionada com sucesso
     * @return true - se foi adicionada com sucesso | false - se foi adicionada sem sucesso
     */
    @Test
    void addNovaRefeicao() {
    }

    /**
     * Verifica se a refeição é alterada com sucesso
     * @return true - se foi alterada com sucesso | false - se foi alterada sem sucesso
     */
    @Test
    void changeRefeicao() {
    }

    /**
     * Verifica se a refeição é cancelada com sucesso
     * @return true - se foi cancelada com sucesso | false - se foi cancelada sem sucesso
     */
    @Test
    void cancelRefeicao() {
    }

    /**
     * Verifica se a refeição pode ser alterada (ainda nao passou 48h)
     * @return true - se pode ser alterada | false - se não pode ser alterada
     */
    @Test
    void hasMoreThan48Hours() {
    }

    /**
     * Verifica se as senhas estão a ser retornadas com sucesso
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getSenhasCompradasAdmin() {
    }

    /**
     * Verifica se a senha está ser retornada com sucesso
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getSenha() {
    }

    /**
     * Verifica se o programa esta a verificar se o utilizador prefere pratos de peixe ou carne, prefere carne
     * @return true - se esta a verificar com sucesso | false - se esta a verificar sem sucesso
     */
    @Test
    void getPreferenciaPratoUser1() throws SQLException {
        boolean preferencia = true;
        utilizador = database.getUtilizador(21270877);
        int carne = database.getNumPratosFavCarne(utilizador.getNumeroUtilizador());
        int peixe = database.getNumPratosFavPeixe(utilizador.getNumeroUtilizador());
        if(carne < peixe) preferencia = false;
        assertTrue(preferencia, "getEmenta()");

    }

    /**
     * Verifica se o programa esta a verificar se o utilizador prefere pratos de peixe ou carne, prefere peixe
     * @return true - se esta a verificar com sucesso | false - se esta a verificar sem sucesso
     */
    @Test
    void getPreferenciaPratoUser2() throws SQLException {
        boolean preferencia = false;
        utilizador = database.getUtilizador(21270877);
        int carne = database.getNumPratosFavCarne(utilizador.getNumeroUtilizador());
        int peixe = database.getNumPratosFavPeixe(utilizador.getNumeroUtilizador());
        if(carne < peixe) preferencia = true;
        assertTrue(preferencia, "getEmenta()");
    }

    /**
     * Verifica se o programa esta a adicionar um novo utilizador
     * @return true - se esta a adicionar com sucesso | false - se esta a adicionar sem sucesso
     */
    @Test
    void addNewUser() {
    }

    /**
     * Verifica se o programa esta a retornar todos os complementos
     * @return true - se esta a retornar com sucesso | false - se esta a retornar sem sucesso
     */
    @Test
    void getTodosComplementos() {
    }
}