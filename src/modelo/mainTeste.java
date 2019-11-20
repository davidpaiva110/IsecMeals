package modelo;

import controlador.Controlador;
import modelo.Password.PasswordUtils;

import java.sql.SQLException;

public class mainTeste {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Controlador ct = new Controlador();
        System.out.println(PasswordUtils.generateSecurePassword("1234", PasswordUtils.getSalt()));
        boolean loginTeste = ct.testeLogin();
        if(loginTeste)
            System.out.println("Login sucesso chefe!");
        else
            System.out.println("Login falhado");
    }
}
