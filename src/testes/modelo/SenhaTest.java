package modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SenhaTest {

    private Senha senha;

    @Test
        //get id da senha
    void getIdSenha() {
        senha = new Senha(90000000,"pratocp", "sobremesa", 2.50, 99999999);
        assertEquals(90000000,senha.getIdSenha(), "id da senha inserido com sucesso!");
    }

    @Test
        //get prato principal da senha
    void getPrato() {
        senha = new Senha(90000000,"pratocp", "sobremesa", 2.50, 99999999);
        assertEquals("pratocp",senha.getPrato(), "prato da senha inserido com sucesso!");
    }

    @Test
        //get sobremesa da senha
    void getSombremesa() {
        senha = new Senha(90000000,"pratocp", "sobremesa", 2.50, 99999999);
        assertEquals("sobremesa",senha.getSombremesa(), "sobremesa da senha inserido com sucesso!");
    }

    @Test
        //get preço da senha
    void getPreco() {
        senha = new Senha(90000000,"pratocp", "sobremesa", 2.50, 99999999);
        assertEquals(2.50,senha.getPreco(), "preço da senha obtido com sucesso!");
    }

    @Test
        //get id da refeição que a senha está associada
    void getIdRefeicao() {
        senha = new Senha(90000000,"pratocp", "sobremesa", 2.50, 99999999);
        assertEquals(99999999,senha.getIdRefeicao(), "id da refeicao obtido com sucesso!");
    }
}