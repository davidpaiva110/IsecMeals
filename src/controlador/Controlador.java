package controlador;

import modelo.Modelo;
import modelo.Refeicao;
import modelo.Utilizador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**********************************************/
/***************** ISECMEALS ******************/
/* Autor: David Paiva                         */
/* Autor: Rafael Sá                           */
/* Autor: Francisco Silva                     */
/* Autor: Ricardo Roque                       */
/* Data de Edição: 12/05/2019                 */
/* Ficheiro: Controlador.java                 */
/* Descrição: Delega os métodos do modelo     */
/**********************************************/


public class Controlador {

    private Modelo modelo;


    public Controlador() throws ClassNotFoundException, SQLException {
        this.modelo = new Modelo();
    }

    public boolean testeLogin(){
        return modelo.login(1234, "adeus");
    }

}
