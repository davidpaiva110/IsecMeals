import com.sun.source.tree.CompilationUnitTree;
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
    void logout() {
        //testar o logout
    }

    /**
     * Verifica se um favorito foi removido com sucesso
     * @return true - se foi efetuado com sucesso | false - se foi efetuado sem sucesso
     */
    @Test
    void removeFavorito() throws Exception {
        ArrayList<Favoritos> favorito1 = database.getFavoritos();
        int nf1 = favorito1.size();
        database.removeFavorito(1);
        favorito1 = database.getFavoritos();
        assertEquals(nf1-1, favorito1.size(), "removeFavorito()");
    }

    /**
     * Verifica se a lista de favoritos está a ser retornados com sucesso
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getFavoritos() throws SQLException {
        ArrayList<Favoritos> favoritos1 = new ArrayList<>();
        favoritos1.add(new Favoritos(1,"Grelhado Misto"));
        ArrayList<Favoritos> favoritos2 = database.getFavoritos();
        boolean isEqual = favoritos1.equals(favoritos2);
        assertTrue(isEqual, "getFavoritos()");

    }

    /**
     * Verifica se a adição de favoritos está a ser efetuada com sucesso
     * @return true - se foi efetuada com sucesso | false - se foi efetuada sem sucesso
     */
    @Test
    void addFavorito(String descricaoFavorito) {
        //addFavorito do modelo ainda não foi implementada
    }

    /**
     * Verifica se a lista de senhas compradas está a ser retornada com sucesso, utilizador so tem 1 senha
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getSenhasCompradas1() throws SQLException {
        utilizador = database.getUtilizador(21270877);
        ArrayList<Senha> senhas1 = new ArrayList<>();
        Senha senha1 = new Senha(4,"Grelhado Misto","Fruta",2.65,1);
        senhas1.add(senha1);
        //falta relacionar a senhas1 ao utilizador
        ArrayList<Senha> senhas2 = new ArrayList<>();
        senhas2 = database.getSenhas(21270877);
        boolean isEqual; //= senhas1.equals(senhas2);
        if(senhas1 == senhas2) isEqual = true; else isEqual = false;
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
        Senha senha3 = database.getSenha(3);
        senhas1.add(senha1);senhas1.add(senha2);senhas1.add(senha3);
        //falta relacionar a senhas1 ao utilizador
        ArrayList<Senha> senhas2 = database.getSenhas(21270564);
        boolean isEqual = senhas1.equals(senhas2);
        assertTrue(isEqual, "getSenhasCompradas2()");
    }

    /**
     * Verifica se a refeição está ser retornada com sucesso
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getRefeicao1() throws SQLException {
        Refeicao refeicao = new Refeicao(1,"Sopa de legumes","Grelhado Misto","Salmao grelhado","Gelatina","Fruta",2.65,0,"2019-11-29");
        boolean resp = refeicao.equals(database.getRefeicao(1));
        assertTrue(resp, "getRefeicao1()");
    }

    @Test
        //get refeição com sucesso, nao pode haver nenhuma refeicao com id 999998
    void getRefeicao2() throws SQLException {
        assertNull(database.getRefeicao(999998), "getRefeicao2()");
    }

    /**
     * Verifica se a senha está a ser comprada com sucesso
     * @return true - se foi comprada com sucesso | false - se foi comprada sem sucesso
     */
    @Test
    void buySenha() {
        /*boolean state = database.addSenha(dadosSenha, utilizador.getNumeroUtilizador());
        if(state == true)
            database.removeSaldo(utilizador.getNumeroUtilizador(), dadosSenha.getPreco());
        return  state;*/
    }

    /**
     * Verifica se a senha está ser cancelada com sucesso
     * @return true - se foi cancelada com sucesso | false - se foi cancelada sem sucesso
     */
    @Test
    void cancelSenha() {
    }

    /**
     * Verifica se a senha está ser alterada com sucesso
     * @return true - se foi alterada com sucesso | false - se foi alterada sem sucesso
     */
    @Test
    void changeSenha() {
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
}