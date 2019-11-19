package modelo;

import controlador.Controlador;

public class mainTeste {

    public static void main(String[] args) {
        Controlador ct = new Controlador();
        boolean loginTeste = ct.testeLogin();
        if(loginTeste)
            System.out.println("Login sucesso chefe!");
        else
            System.out.println("Login falhado");
    }
}
