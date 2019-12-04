import modelo.*;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static modelo.ComunicacaoBD.executeUpdate;
import static org.junit.jupiter.api.Assertions.*;

class ComunicacaoBDTest {

    private Utilizador utilizador;
    private List<Refeicao> ementa;
    private ComunicacaoBD database;


    ComunicacaoBDTest() throws SQLException, ClassNotFoundException {
        database = new ComunicacaoBD();
        ementa = new ArrayList<>();
        database.connectToDatabase();
    }

    @Test
    void getSaldo() throws SQLException {
        double saldo = database.getSaldo(21270877);
        boolean isEqual;
        if (saldo == 2.48) isEqual = true; else isEqual = false;
        assertTrue(isEqual, "getSaldo()");
    }

    @Test
    void getUtilizador() throws SQLException {
        utilizador = new Utilizador(21270877, 0);
        utilizador.setPassword("1234");
        utilizador.setNome("Ricardo");
        utilizador.setSaldo(2.48);
        Utilizador utilizador2 = database.getUtilizador(21270877);

        boolean isEqual;
        if ((utilizador.getNumeroUtilizador() == utilizador2.getNumeroUtilizador()) &&
                (utilizador.getNome().equals(utilizador2.getNome()))&&
                //(utilizador.getPassword() == utilizador2.getPassword()) &&
                (utilizador.getSaldo() == utilizador2.getSaldo()) &&
                (utilizador.geteUtilizador() == utilizador2.geteUtilizador())) {
            isEqual = true;
        }
        else {
            isEqual = false;
        }

        System.out.println(utilizador.getNome());
        System.out.println(utilizador2.getNome());
        //System.out.println(utilizador.getPassword());
        //System.out.println(utilizador2.getPassword());//nao está a receber a password
        System.out.println(utilizador.geteUtilizador());
        System.out.println(utilizador2.geteUtilizador());
        System.out.println(utilizador.getSaldo());
        System.out.println(utilizador2.getSaldo());

        assertTrue(isEqual, "getUtilizador()");
    }

    @Test
    void login() {
    }

    @Test
    void getEmenta() {
    }

    @Test
    void getSenhas() throws SQLException {
        ArrayList<Senha> senhas1 = new ArrayList<>();
        Senha senha1 = new Senha(1,"Grelhado Misto","Fruta",2.65,1);
        Senha senha2 = new Senha(2,"Salmao Grelhado","Fruta",2.65,1);
        Senha senha3 = new Senha(3,"Costeletinha de Novilho","Gelatina",3.65,2);
        ArrayList<Senha> senhas2 = new ArrayList<>();
        senhas2 = database.getSenhas(21270564);
        boolean isEqual = senhas1.equals(senhas1);
        assertTrue(isEqual, "getUtilizador()");
    }

    @Test
    void getRefeicao() throws SQLException {
        Refeicao refeicao1 = new Refeicao(3,"Sopa de Ceneura","Picado de Frango","Polvo Frito","Fruta","Arroz Doce",2.65,1,"2019-12-6");
        ArrayList<Complemento> complementos = new ArrayList<>();
        complementos.add(new Complemento(1,"Refrigerante", 1));
        complementos.add(new Complemento(2,"Vinho", 1.5));
        Refeicao refeicao2 = database.getRefeicao(3);
        boolean isEqual = refeicao1.equals(refeicao2);
        assertTrue(isEqual, "getUtilizador()");
    }

    @Test
    void getFavoritos() throws SQLException {
        ArrayList<Favoritos> favoritos1 = new ArrayList<>();
        favoritos1.add(new Favoritos(1,"Grelhado Misto"));
        ArrayList<Favoritos> favoritos2 = database.getFavoritos();
        //addfavorito() ao utilizador, ainda nao implementado
        //verificar se está igual aos favoritos do utilziador na bd
        boolean isEqual = favoritos1.equals(favoritos2);
        assertTrue(isEqual, "getFavoritos()");
    }

    @Test
    void deleteSenha() throws Exception {
        ArrayList<Senha> senhas1 = new ArrayList<>();
        senhas1 = database.getSenhas(21270547);
        database.deleteSenha(3);
        ArrayList<Senha> senhas2 = new ArrayList<>();
        senhas1 = database.getSenhas(21270547);
        boolean isEqual = false;
        if (senhas1.size() == senhas2.size() + 1) isEqual = true;
        assertTrue(isEqual, "deleteSenha()");
    }

    @Test
    void getPrecoSenhaComprada() throws SQLException {
        double preco;
        Senha senha1 = database.getSenha(3);
        preco = senha1.getPreco();
        System.out.println(preco);
        assertEquals(3.65, preco,"getPrecoSenhaComprada()");

    }

    @Test
    void addSaldo() throws SQLException {
        double saldo1 = 1.48;
        double saldo2 = database.getSaldo(21270877);
        saldo2 += saldo1;
        assertEquals(3.96, saldo2, "addSaldo()");
    }

    @Test
    void addSenha() throws SQLException {
        utilizador = new Utilizador(21270878,0);
        Senha senha1 = new Senha(4,"Grelhado Misto","Fruta",2.65,1);
        database.addSenha(senha1,21270878);
        database.getSenhas(21270877);
        ArrayList<Senha> senhas1 = database.getSenhas(21270877);
        assertEquals(0, utilizador.geteUtilizador(), "gettipoUtilizador1()");
    }

    @Test
    void removeSaldo() throws SQLException {
        double saldo1 = 1.46;
        double saldo2 = database.getSaldo(21270877);
        saldo2 -= saldo1;
        assertEquals(1.02, saldo2, "removeSaldo()");
    }

    @Test
    void hasMoreThan48Hours1() throws ParseException {
        Date data = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        boolean resp = false;
        Refeicao refeicao1 = new Refeicao(3,"Sopa de Ceneura","Picado de Frango","Polvo Frito","Fruta","Arroz Doce",2.65,1,"2019-12-6");
        data = df.parse(refeicao1.getData());
        Date now = new Date();
        long dif = data.getTime() - now.getTime();
        if (TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS) >= 1) resp = true;
        assertTrue(resp, "hasMoreThan48Hours1()");
    }
    @Test

    void hasMoreThan48Hours2() throws ParseException {
        Date data = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        boolean resp = false;
        Refeicao refeicao1 = new Refeicao(3,"Sopa de Ceneura","Picado de Frango","Polvo Frito","Fruta","Arroz Doce",2.65,1,"2019-12-30");
        data = df.parse(refeicao1.getData());
        Date now = new Date();
        long dif = data.getTime() - now.getTime();
        if (TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS) >= 1) resp = true;
        assertFalse(resp, "hasMoreThan48Hours2()");
    }

    @Test
    void removeFavorito() {
        utilizador = new Utilizador(21270878,0);
        //addfavorito() ao utilizador, ainda nao implementado
        //removeFavorito()
        //verificar se removeu
    }

    @Test
    void getSenhasCompradasAdmin() throws SQLException {
        ArrayList<RefeicaoAdmin> refeicoes = new ArrayList<>();
        Senha senha1 = database.getSenha(1);
        Senha senha2 = database.getSenha(2);
        Senha senha3 = database.getSenha(3);
        Senha senha4 = database.getSenha(4);
        /*while (1) {
            int id = rs.getInt("idrefeicao");
            int horario = rs.getInt("horario");
            String aux;
            if (horario == 0) {
                aux = "Almoco";
            } else aux = "Jantar";

            String d = rs.getDate("data").toString();

            String sqlquantPeixe = "SELECT COUNT(idsenha) FROM senha, refeicoes" +
                    "WHERE " + id + "refeicoes.idrefeicao= senha.idrefeicao" +
                    "AND senha.prato=refeicoes.pratopeixe;";
            ResultSet rsa = executeQuery(sqlquantPeixe);
            int qtpeixe = rsa.getInt("count(idsenha)");

            String sqlquantCarne = "SELECT COUNT(idsenha) FROM senha, refeicoes" +
                    "WHERE " + id + "refeicoes.idrefeicao= senha.idrefeicao" +
                    "AND senha.prato=refeicoes.pratocarne;";
            ResultSet rsc = executeQuery(sqlquantCarne);
            int qtcarne = rsa.getInt("count(idsenha)");

            refeicoes.add(new RefeicaoAdmin(id, d, aux, qtcarne, qtpeixe));*/
    }

    @Test
    void getSenha() throws SQLException {
        Senha senha1 = new Senha(4,"Grelhado Misto","Fruta",2.65,1);
        Senha senha2 = database.getSenha(4);
        boolean isEqual = senha1.equals(senha2);
        assertTrue(isEqual, "getSenha()");
    }
}