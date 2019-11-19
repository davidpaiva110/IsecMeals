package controlador;

import modelo.Modelo;
import modelo.Refeicao;
import modelo.Utilizador;

import java.util.ArrayList;
import java.util.List;

public class Controlador {

    private Modelo modelo;


    public Controlador() {
        this.modelo = new Modelo();
    }

    public boolean testeLogin(){
        return modelo.login("ola", "adeus");
    }

}
