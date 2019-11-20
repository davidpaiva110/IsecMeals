package modelo;

import controlador.Controlador;

import java.sql.SQLException;

public class mainTeste {

    public static void main(String[] args) {
        Controlador ct = null;
        try {
            ct = new Controlador();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean loginTeste = ct.testeLogin();
        if(loginTeste)
            System.out.println("Login sucesso chefe!");
        else
            System.out.println("Login falhado");

        //System.out.println(ComunicacaoBD.getEndDate("2019/11/22"));
    }
}
