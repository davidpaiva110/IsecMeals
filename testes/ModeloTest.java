import modelo.*;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
     * Verifica se o programa devolve o saldo do utilizador com sucesso
     * @return true - se o saldo for devolvido com sucesso | false - se o saldo for devolvido sem sucesso
     */
    @Test
    void getSaldoUtilizador() throws SQLException {
        utilizador = database.getUtilizador(21270877);
        assertEquals(2.65, utilizador.getSaldo() ,"getSaldoUtilizador");
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
     * Verifica se o login foi efetuado com insucesso, password errada
     * @return true - se login efetuado com insucesso | exception
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
     * Verifica se o login foi efetuado com insucesso, username não registado
     * @return true - se login efetuado com insucesso | exception
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
     * Verifica se a remoção de favorito está a diminuir o tamanho do array com sucesso
     * @return true - se foi efetuado com sucesso | false - se foi efetuado sem sucesso
     */
    @Test
    void removeFavorito() throws Exception {
        ArrayList<Favoritos> favorito1 = database.getFavoritos(21270877);
        int nf1 = favorito1.size();
        database.removeFavorito(favorito1.size()+5);
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
        boolean isEqual = false;
        System.out.println(favoritos1.get(0).getPrato());
        System.out.println(favoritos1.get(1).getPrato());
        if(favoritos1.get(0).getPrato().equals("Grelhado Misto") && favoritos1.get(1).getPrato().equals("Costeletinha de Novilho"))
            isEqual = true;
        assertTrue(isEqual, "getFavoritos1()");

    }

    /**
     * Verifica se está a ser returnado o numero correto de favoritos
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getFavoritos2() throws SQLException {
        ArrayList<Favoritos> favoritos1 =  database.getFavoritos(21270547);
        assertEquals(2, favoritos1.size(), "getFavoritos2()");
    }

    /**
     * Verifica se a adição de favoritos está a aumentar o tamanho do array com sucesso
     * @return true - se foi aumentado com sucesso | false - se foi aumentado sem sucesso
     */
    @Test
    void addFavorito1() throws Exception {
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
    void addFavorito2() throws Exception {
        boolean isEqual = false;
        database.addFavorito("Fav1",0,21270877);
        ArrayList<Favoritos> favoritos1 = database.getFavoritos(21270877);
        if (favoritos1.get(favoritos1.size()-1).getPrato().equals("Fav1") && favoritos1.get(favoritos1.size()-1).getTipo() == 0)
            isEqual = true;
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
        && senhas1.get(1).getPrato().equals("Picado de Frango") && senhas1.get(1).getPreco() == 2.65
                && senhas1.get(1).getSombremesa().equals("Fruta") && senhas1.get(1).getIdRefeicao() == 3)
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
                refeicao.getSombremesa2().equals("Gelatina") && refeicao.getPreco()==2.65 && refeicao.getData().equals("2019-12-12"))
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
        Senha senha1 = new Senha(6,"Costeletinha de Novilho","Fruta",2.65,2);
        database.addSenha(senha1,21270877);
        boolean res = database.deleteSenha(7);
        assertTrue(res, "cancelSenha1()");
    }

    /**
     * Verifica se o dinheiro esta a ser devolvido ao utilizador ao cancelar a senha
     * @return true - se foi cancelada com sucesso | false - se foi cancelada sem sucesso
     */
    @Test
    void cancelSenha2() throws Exception {
        utilizador = database.getUtilizador(21270877);
        DecimalFormat df = new DecimalFormat("0.00");
        String saldo1 = df.format(utilizador.getSaldo() + 2.65);
        double precoSenha = database.getPrecoSenhaComprada(3);
        database.addSaldo(21270877, precoSenha);
        utilizador = database.getUtilizador(21270877);
        String saldo2 = df.format(utilizador.getSaldo());
        boolean isEqual = false;
        if(saldo1.equals(saldo2))
            isEqual = true;
        assertTrue(isEqual, "cancelSenha2()");
        utilizador.setSaldo(2.65);
    }

    /**
     * Verifica se a senha está ser alterada com sucesso
     * @return true - se foi alterada com sucesso | false - se foi alterada sem sucesso
     */
    @Test
    void changeSenha() throws Exception {
        utilizador = database.getUtilizador(21270564);
        Senha senha1 = database.getSenha(1);
        double dif = database.changeSenha(senha1);
        boolean isEqual = false;
        if(dif != -1) isEqual = true;
        assertTrue(isEqual, "changeSenha()");
    }

    /**
     * Verifica se o utilizador está ser removido com sucesso
     * @return true - se foi removido com sucesso | false - se foi removido sem sucesso
     */
    @Test
    void removeUtilizador() throws Exception {
        utilizador = new Utilizador(21270811,0);
        database.addNewUser(utilizador);
        boolean isEqual = false;
        if(database.removeUtilizador(utilizador.getNumeroUtilizador()) == true)
            isEqual = true;
        assertTrue(isEqual, "removeUtilizador()");
    }

    /**
     * Verifica se a ementa está ser retornada com sucesso
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    //get ementa, 1 dia tem 2 refeições
    void getEmenta() throws SQLException {
        utilizador = database.getUtilizador(21270564);
        ementa1 = database.getEmenta(utilizador.getNumeroUtilizador());
        boolean isEqual = false;
        System.out.println(ementa1.get(1).getSombremesa2());
        if(ementa1.get(0).getIdRefeicao() == 1 && ementa1.get(0).getPratoCarne().equals("Grelhado Misto") && ementa1.get(0).getPratoPeixe().equals("Salmao grelhado") &&
                ementa1.get(0).getAlmocoJantar() == 0 && ementa1.get(0).getSopa().equals("Sopa de legumes") && ementa1.get(0).getSombremesa1().equals("Gelatina") &&
                ementa1.get(0).getSombremesa2().equals("Fruta") && ementa1.get(0).getPreco()==2.65 && ementa1.get(0).getData().equals("2019-12-12") &&
                ementa1.get(1).getIdRefeicao() == 2 && ementa1.get(1).getPratoCarne().equals("Costeletinha de Novilho") && ementa1.get(1).getPratoPeixe().equals("Truta do Nilo") &&
                ementa1.get(1).getAlmocoJantar() == 0 && ementa1.get(1).getSopa().equals("Sopa de Feijão") && ementa1.get(1).getSombremesa1().equals("Fruta") &&
                ementa1.get(1).getSombremesa2().equals("Gelatina") && ementa1.get(1).getPreco()==2.65 && ementa1.get(1).getData().equals("2019-12-12"))
            isEqual = true;
        assertTrue(isEqual, "getEmenta()");
    }

    /**
     * Verifica se a refeição está ser adicionada com sucesso
     * @return true - se foi adicionada com sucesso | false - se foi adicionada sem sucesso
     */
    @Test
    void addNovaRefeicao() throws Exception {
        Refeicao refeicao = new Refeicao(6,"sopa3","carne3","peixe3","sobremesa31","sobremesa32",2.65,0,"2019-12-8");
        database.addRefeicao(refeicao);
        boolean isEqual = false;
        Refeicao refeicaoteste = database.getRefeicao(6);
        if(refeicaoteste.getSombremesa2().equals("sobremesa32") && refeicaoteste.getPreco() == 2.65 && refeicaoteste.getData().equals("2019-12-8"))
            isEqual= true;
        assertTrue(isEqual, "addNovaRefeicao()");
        database.removeRefeicao(6);
    }

    /**
     * Verifica se a refeição é cancelada com sucesso
     * @return true - se foi cancelada com sucesso | false - se foi cancelada sem sucesso
     */
    @Test
    void cancelRefeicao() throws Exception {
        ArrayList<Integer> senhas =  database.getSenhasDaRefeicao(5);
        boolean isEqual = false;
        if(database.removeRefeicao(5) && senhas.size() > 0)
            isEqual = true;
        assertTrue(isEqual, "cancelRefeicao()");
        Refeicao refeicao = new Refeicao(5,"sopa2","carne2","peixe2","sobremesa21","sobremesa12",2.65,1,"2019-12-7");
        database.addRefeicao(refeicao);
    }

    /**
     * Verifica se a refeição pode ser alterada (ainda nao passou 48h)
     * @return true - se pode ser alterada | false - se não pode ser alterada
     */
    @Test
    void hasMoreThan48Hours() throws Exception {
        Date data1 = null, data2 = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        boolean resp = false;
        Refeicao refeicao = new Refeicao(6,"sopa3","carne3","peixe3","sobremesa31","sobremesa32",2.65,0,"2019-12-12");
        data1 = df.parse(refeicao.getData());
        LocalDate now = LocalDate.now();
        data2 = df.parse(String.valueOf(now));
        long dif = data1.getTime() - data2.getTime();
        if (TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS) >= 1) resp = true;
        assertTrue(resp, "hasMoreThan48Hours1()");
    }

    /**
     * Verifica se as senhas estão a ser retornadas com sucesso
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getSenhasCompradasAdmin() throws SQLException {
        ArrayList<RefeicaoAdmin> refeicoes = database.getSenhasCompradasAdmin();
        boolean isEqual = false;
        if(refeicoes.get(0).getData().equals("2019-12-7") && refeicoes.get(0).getIdRefeicao() == 5 && refeicoes.get(0).getQuantCarne() > 0 &&
                refeicoes.get(1).getData().equals("2019-12-7") && refeicoes.get(1).getIdRefeicao() == 4 )
            isEqual = true;
        assertTrue(isEqual, "getSenhasCompradasAdmin()");
    }

    /**
     * Verifica se a senha está ser retornada com sucesso
     * @return true - se foi retornada com sucesso | false - se foi retornada sem sucesso
     */
    @Test
    void getSenha() throws SQLException {
        Senha senha = database.getSenha(5);
        boolean isEqual = false;
        if(senha.getIdRefeicao() == 5 && senha.getPreco() == 2.65 && senha.getPrato().equals("carne2") && senha.getSombremesa().equals("sobremesa22"))
            isEqual = true;
        assertTrue(isEqual, "getSenha()");
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
     * Verifica se o programa esta a adicionar um novo utilizador
     * @return true - se esta a adicionar com sucesso | false - se esta a adicionar sem sucesso
     */
    @Test
    void addNewUser() throws SQLException {
        utilizador = new Utilizador(21270811,0);
        boolean isEqual = false;
        isEqual = database.addNewUser(utilizador);
        assertTrue(isEqual, "addNewUser()");
    }

    /**
     * Verifica se o programa esta a retornar todos os complementos
     * @return true - se esta a retornar com sucesso | false - se esta a retornar sem sucesso
     */
    @Test
    void getTodosComplementos() throws SQLException {
        ArrayList<Complemento> complementos = database.getTodosComplementos();
        boolean isEqual = false;
        if(complementos.get(0).getNomeComplemento().equals("Refrigerante") && complementos.get(0).getPreco() == 1 && complementos.get(0).getIdComplemento() == 1 &&
                complementos.get(1).getNomeComplemento().equals("Vinho") && complementos.get(1).getPreco() == 1.5 && complementos.get(1).getIdComplemento() == 2)
            isEqual = true;
        assertTrue(isEqual, "getTodosComplementos()");
    }

    /**
     * Verifica se o programa esta a retornar todos os complementos
     * @return true - se esta a retornar com sucesso | false - se esta a retornar sem sucesso
     */
    @Test
    void getUserAdmin() throws SQLException {
        ArrayList<Utilizador> useradmin1 = database.getUserAdmin();
        int tam = useradmin1.size();
        boolean isEqual = false;
        DecimalFormat df = new DecimalFormat("0.00");
        String saldo1 = df.format(useradmin1.get(0).getSaldo());
        String saldo2 = df.format(useradmin1.get(1).getSaldo());
        if(useradmin1.get(0).getNome().equals("Rafael") && saldo1.equals("28,50") &&
                useradmin1.get(1).getNome().equals("Ricardo") && saldo2.equals("5,30") && tam > 2)
            isEqual = true;
        assertTrue(isEqual, "getUserAdmin()");
    }

    /**
     * Verifica se o programa esta a retornar todos os complementos
     * @return true - se esta a retornar com sucesso | false - se esta a retornar sem sucesso
     */
    @Test
    void getComplementos() throws SQLException {
        ArrayList<Complemento> complementos1 = database.getComplementos(2);
        boolean isEqual = false;
        if(complementos1.get(0).getIdComplemento() == 1 && complementos1.get(0).getNomeComplemento().equals("Refrigerante") &&
                complementos1.get(0).getPreco()==1 && complementos1.get(1).getIdComplemento() == 2 &&
                complementos1.get(1).getNomeComplemento().equals("Vinho") && complementos1.get(1).getPreco()==1.5)
            isEqual=true;
        assertTrue(isEqual, "getComplementos()");
    }

    /**
     * Verifica se o programa esta a retornar todos os complementos
     * @return true - se esta a retornar com sucesso | false - se esta a retornar sem sucesso
     */
    @Test
    void getUtilizador() throws SQLException {
        utilizador = database.getUtilizador(21270877);
        boolean isEqual = false;
        if(utilizador.getNome().equals("Ricardo") && utilizador.getSaldo() == 2.65 && utilizador.getNumeroUtilizador()==21270877
                && utilizador.geteUtilizador()==0)
            isEqual=true;
        assertTrue(isEqual, "getUtilizador()");
    }

    /**
     * Verifica se o programa esta alterar os dados do utilizador com sucesso
     * @return true - se esta a alterar com sucesso | false - se esta a alterar sem sucesso
     */
    @Test
    void updateUser() throws Exception {
        utilizador = database.getUtilizador(21272727);
        utilizador.setSaldo(2.75);
        utilizador.setNome("RicardoR");
        database.updateUser(utilizador, 21270877);
        boolean isEqual = false;
        utilizador = database.getUtilizador(21270877);
        if (utilizador.getNome().equals("RicardoR") && utilizador.getSaldo() == 2.75)
            isEqual = true;
        assertTrue(isEqual, "updateUser()");
        utilizador.setSaldo(2.65);
        utilizador.setNome("teste");
        database.updateUser(utilizador, 21270877);
    }
}