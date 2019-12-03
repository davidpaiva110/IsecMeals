import modelo.Utilizador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilizadorTest {

    private Utilizador utilizador;

    @Test
        //get numero utilizador
    void getNumeroUtilizador() {
        utilizador = new Utilizador(90000000,0);
        assertEquals(90000000, utilizador.getNumeroUtilizador(), "getNumeroUtilizador()");
    }

    @Test
        //set numero utilizador
    void setNumeroUtilizador() {
        utilizador = new Utilizador(90000000,0);
        utilizador.setNumeroUtilizador(89999999);
        assertEquals(89999999,utilizador.getNumeroUtilizador(), "setNumeroUtilizador()");
    }

    @Test
        //get password
    void getPassword() {
        utilizador = new Utilizador(90000000,0);
        utilizador.setPassword("HJ1F5MYP");
        assertEquals("HJ1F5MYP", utilizador.getPassword(), "getPassword()");
    }

    @Test
        //set password
    void setPassword() {
        utilizador = new Utilizador(90000000,0);
        utilizador.setPassword("HJ1F5MYP");
        assertEquals("HJ1F5MYP", utilizador.getPassword(), "setPassword()");
    }

    @Test
        //get nome
    void getNome() {
        utilizador = new Utilizador(90000000,0);
        utilizador.setNome("nome de teste");
        assertEquals("nome de teste", utilizador.getNome(), "getNome()");
    }

    @Test
        //set nome
    void setNome() {
        utilizador = new Utilizador(90000000,0);
        utilizador.setNome("nome de teste");
        assertEquals("nome de teste", utilizador.getNome(), "setNome");
    }

    @Test
        //get saldo
    void getSaldo() {
        utilizador = new Utilizador(90000000,0);
        utilizador.setSaldo(5.00);
        assertEquals(5.00, utilizador.getSaldo(), "getSaldo()");
    }

    @Test
        //set saldo
    void setSaldo() {
        utilizador = new Utilizador(90000000,0);
        utilizador.setSaldo(5.00);
        assertEquals(5.00, utilizador.getSaldo(), "setSaldo()");
    }

    @Test
        //get tipo de utilizador
    void geteUtilizador() {
        utilizador = new Utilizador(90000000,0);
        assertEquals(0, utilizador.geteUtilizador(), "geteUtilizador()");
    }

    @Test
        //set tipo de utilizador
    void seteUtilizador() {
        utilizador = new Utilizador(90000000,1);
        utilizador.seteUtilizador(0);
        assertEquals(0, utilizador.geteUtilizador(), "seteUtilizador()");
    }
}