package modelo;

import java.util.List;

public interface IUtilizador {

    public boolean login(String user, String password);
    public double getSaldoUtilizador();

    // ==== Pratos Favoritos ====
    public boolean removerFavorito(int idFavorito);
    public List getFavoritos();
    public boolean adicianoFavorito(String descricaoFavorito);

    // ==== Senhas ====
    public List getHistoricoSenhas();
    public boolean comprarSenha(Refeicao dadosSenha);
    public boolean cancelarSenha(int idSenha);
    public boolean alterarSenha(Refeicao novosDadosSenha);

    // ===== Funcionalidades do Administrador =====
    public double setNovoSaldoUtilizador();
    public boolean adicionaUtilizador(Utilizador novoUtilizador);
    public boolean removeUtilizador(long numeroUtilizador);


}
