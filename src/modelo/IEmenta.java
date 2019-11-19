package modelo;

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
    public List getEmenta();

    // ===== Funcionalidades do Administrador =====
    public boolean addNovaRefeicao(Refeicao novaRefeicao);
    public boolean changeRefeicao(Refeicao dadosRefeicao);
    public boolean cancelRefeicao(int idRefeicao);
}
