package modelo;

import java.util.List;

public interface IEmenta {
    public List getEmenta();

    // ===== Funcionalidades do Administrador =====
    public boolean adicionaNovaRefeicao(Refeicao novaRefeicao);
    public boolean alteraRefeicao(Refeicao dadosRefeicao);
    public boolean cancelarRefeicao(int idRefeicao);
}
