import modelo.Refeicao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefeicaoTest {

    private Refeicao refeicao;

    @Test
    void getIdRefeicao() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        assertEquals(90000000,refeicao.getIdRefeicao(), "getIdRefeicao()");
    }

    @Test
    void setIdRefeicao() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        refeicao.setIdRefeicao(90000001);
        assertEquals(90000001,refeicao.getIdRefeicao(), "setIdRefeicao()");
    }

    @Test
    void getSopa() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        assertEquals("sopa",refeicao.getSopa(), "getSopa()");
    }

    @Test
    void setSopa() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        refeicao.setSopa("soopa");
        assertEquals("soopa",refeicao.getSopa(), "setSopa()");
    }

    @Test
    void getPratoCarne() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        assertEquals("pratoc",refeicao.getPratoCarne(), "getPratoCarne()");
    }

    @Test
    void setPratoCarne() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        refeicao.setPratoCarne("praatoc");
        assertEquals("praatoc",refeicao.getPratoCarne(), "setPratoCarne()");
    }

    @Test
    void getPratoPeixe() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        assertEquals("pratop",refeicao.getPratoPeixe(), "getPratoPeixe()");
    }

    @Test
    void setPratoPeixe() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        refeicao.setPratoPeixe("praatop");
        assertEquals("praatop",refeicao.getPratoPeixe(), "setPratoPeixe()");
    }

    @Test
    void getSombremesa1() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        assertEquals("sobremesa1",refeicao.getSombremesa1(), "getSobremesa1()");
    }

    @Test
    void setSombremesa1() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        refeicao.setSombremesa1("soobremesa1");
        assertEquals("soobremesa1",refeicao.getSombremesa1(), "setSombremesa1()");
    }

    @Test
    void getSombremesa2() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        assertEquals("sobremesa2",refeicao.getSombremesa2(), "getSobremesa2()");
    }

    @Test
    void setSombremesa2() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        refeicao.setSombremesa2("soobremesa2");
        assertEquals("soobremesa2",refeicao.getSombremesa2(), "setSombremesa2()");
    }

    @Test
    void getPreco() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        assertEquals(2.50,refeicao.getPreco(), "getPreco()");
    }

    @Test
    void setPreco() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        refeicao.setPreco(2.75);
        assertEquals(2.75,refeicao.getPreco(), "setPreco()");
    }

    @Test
    void getAlmocoJantar() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        assertEquals(1,refeicao.getAlmocoJantar(), "getAlmocoJantar()");
    }

    @Test
    void setAlmocoJantar() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        refeicao.setAlmocoJantar(0);
        assertEquals(0,refeicao.getAlmocoJantar(), "setAlmocoJantar()");
    }

    @Test
    void getData() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        assertEquals("2017-06-28",refeicao.getData(), "getData()");
    }

    @Test
    void setData() {
        refeicao = new Refeicao(90000000,"sopa","pratoc","pratop","sobremesa1","sobremesa2",2.50,1,"2017-06-28");
        refeicao.setData("2017-06-29");
        assertEquals("2017-06-29",refeicao.getAlmocoJantar(), "setData()");
    }
}