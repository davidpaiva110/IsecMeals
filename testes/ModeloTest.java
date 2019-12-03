import com.sun.source.tree.CompilationUnitTree;
import modelo.ComunicacaoBD;
import modelo.Refeicao;
import modelo.Senha;
import modelo.Utilizador;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModeloTest {

    private Utilizador utilizador;
    private List<Refeicao> ementa;
    private ComunicacaoBD database;

    @Test
        //obtem saldo
    void getSaldoUtilizador() {
    }

    @Test
        //login com sucesso
    void login1() throws Exception {
        database = new ComunicacaoBD();
        ComunicacaoBD.connectToDatabase();
        utilizador = new Utilizador(90000000,0);
        utilizador.setPassword("HJ1F5MYP");
        boolean resp = ComunicacaoBD.login(utilizador.getNumeroUtilizador(), utilizador.getPassword());
        assertTrue(resp, "login realizado com sucesso!");
    }

    @Test
        //password errada
    void login2() throws Exception {
        database = new ComunicacaoBD();
        ComunicacaoBD.connectToDatabase();
        utilizador = new Utilizador(90000000,0);
        utilizador.setPassword("passworderrada");
        boolean resp = ComunicacaoBD.login(utilizador.getNumeroUtilizador(), utilizador.getPassword());
        assertFalse(resp, "login nao realizado!");
    }

    @Test
        //numero utilizador errado, não existe nenhum utilizador com id 90000001
    void login3() throws Exception {
        database = new ComunicacaoBD();
        ComunicacaoBD.connectToDatabase();
        utilizador = new Utilizador(90000001,0);
        utilizador.setPassword("HJ1F5MYP");
        boolean resp = ComunicacaoBD.login(utilizador.getNumeroUtilizador(), utilizador.getPassword());
        assertFalse(resp, "login nao realizado!");
    }

    @Test
        //numero e password errados, nao existe nenhum utilizador com id 90000001
    void login4() throws Exception {
        utilizador = new Utilizador(90000001,0);
        utilizador.setPassword("passworderrada");
        boolean resp = database.login(utilizador.getNumeroUtilizador(), utilizador.getPassword());
        assertFalse(resp, "login nao realizado!");
    }

    @Test
        //get utilizador utilizador
    void gettipoUtilizador1() {
        utilizador = new Utilizador(90000000,0);
        assertEquals(0, utilizador.geteUtilizador(), "tipo de utilizador nao obtido com sucesso!");
    }

    @Test
        //get utilizador administrador
    void gettipoUtilizador2() {
        utilizador = new Utilizador(90000000,1);
        assertEquals(1, utilizador.geteUtilizador(), "tipo de utilizador nao obtido com sucesso!");
    }

    @Test
    void logout() {
    }

    @Test
    void removeFavorito() {
    }

    @Test
    void getFavoritos() {
    }

    @Test
    void addFavorito() {
    }

    @Test
        //get senhas, utilizador só tem 1 senha
    void getSenhasCompradas1() throws SQLException, ClassNotFoundException {
        ArrayList<Senha> senhas = new ArrayList<>();
        utilizador = new Utilizador(90000000,0);
        database.getSenhas(utilizador.getNumeroUtilizador());
        Senha senha1 = new Senha(90000000,"prato","sobremesa",2.50,999996);
        senhas.add(senha1);
        assertEquals(senhas, database.getSenhas(90000000), "senha obtida com sucesso!");
    }

    @Test
        //get senhas, utilizador tem mais que 1 senha
    void getSenhasCompradas2() throws SQLException {
        ArrayList<Senha> senhas = new ArrayList<>();
        utilizador = new Utilizador(90000000,0);
        database.getSenhas(utilizador.getNumeroUtilizador());
        Senha senha1 = new Senha(90000000,"prato","sobremesa",2.50,999996);
        Senha senha2 = new Senha(90000000,"prato","sobremesa",2.50,999995);
        Senha senha3 = new Senha(90000000,"prato","sobremesa",2.50,999994);
        senhas.add(senha1);
        senhas.add(senha2);
        senhas.add(senha3);
        assertEquals(senhas, database.getSenhas(90000000), "senhas obtidas com sucesso!");
    }

    @Test
        //get refeição com sucesso
    void getRefeicao1() throws SQLException, ClassNotFoundException {
        Refeicao refeicao = new Refeicao(999999,"sopa1","pratoc1","pratop1","sobremesa1","sobremesa2",2.20,0,"2017-24-28");
        boolean resp = refeicao.equals(database.getRefeicao(999999));
        assertTrue(resp, "refeição obtida com sucesso!");
    }

    @Test
        //get refeição com sucesso, nao pode haver nenhuma refeicao com id 999998
    void getRefeicao2() throws SQLException {
        assertNull(database.getRefeicao(999998), "refeição nao obtida com sucesso!");
    }

    @Test
    void buySenha() {
    }

    @Test
    void cancelSenha() {
    }

    @Test
    void changeSenha() {
    }

    @Test
    void setNovoSaldoUtilizador() {
    }

    @Test
    void addUtilizador() {
    }

    @Test
    void removeUtilizador() {
    }

    @Test
        //get ementa, 1 dia tem 2 refeições
    void getEmenta() throws SQLException {
        Refeicao refeicao1 = new Refeicao(999999,"sopa1","pratoc1","pratop1","sobremesa1","sobremesa2",2.20,0,"2017-24-28");
        Refeicao refeicao2 = new Refeicao(999998,"sopa1","pratoc1","pratop1","sobremesa1","sobremesa2",2.20,1,"2017-24-28");
        ementa.add(refeicao1);
        ementa.add(refeicao2);
        assertEquals(ementa, database.getEmenta(21270564));
    }

    @Test
    void addNovaRefeicao() {
    }

    @Test
    void changeRefeicao() {
    }

    @Test
    void cancelRefeicao() {
    }
}