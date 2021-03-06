package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/*************************************************************************************************/
/********************************************* ISECMEALS *****************************************/
/* Autor: David Paiva                                                                            */
/* Autor: Rafael Sá                                                                              */
/* Autor: Francisco Silva                                                                        */
/* Autor: Ricardo Roque                                                                          */
/* Data de Edição: 12/05/2019                                                                    */
/* Ficheiro: IEmenta.java                                                                        */
/* Descrição: Protótipos dos métodos relativos a ações sobre a ementa                            */
/*************************************************************************************************/


public interface IEmenta {
    public List getEmenta() throws SQLException;

    // ===== Funcionalidades do Administrador =====
    public List getEmentaToda() throws SQLException;
    public boolean addNovaRefeicao(Refeicao novaRefeicao) throws Exception;
    public boolean changeRefeicao(Refeicao dadosRefeicao) throws Exception;
    public boolean cancelRefeicao(int idRefeicao) throws Exception;
    public ArrayList<Complemento> getComplementos(int idRefeicao) throws SQLException;
}
