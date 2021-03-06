package modelo;

import modelo.Password.PasswordUtils;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ComunicacaoBD {

    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:isecmeals.db";

    private static Connection conn = null;
    private static Statement stmt = null;

    /**
     * Regista o driver JDBC e abre uma ligação à base de dados
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL);
    }

    /**
     * Encerra a ligação à base de dados
     *
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        conn.close();
    }

    /**
     * Executa uma consulta à base de dados
     *
     * @param sql Query a executar
     * @return O resultado da consulta
     * @throws SQLException
     */
    public static ResultSet executeQuery(String sql) throws SQLException {
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    /**
     * Executa um update à base de dados
     *
     * @param sql Query a executar
     * @return Número de linhas afetadas pela query
     * @throws SQLException
     */
    public static int executeUpdate(String sql) throws SQLException {
        stmt = conn.createStatement();
        int resp = stmt.executeUpdate(sql);
        return resp;
    }

    /**
     * Devolve o saldo de um utilizador específico
     *
     * @param number número do utilizador a pesquisar
     * @return saldo do utilizador
     * @throws SQLException
     */
    public double getSaldo(int number) throws SQLException {
        double saldo = -1;
        String sql = "SELECT saldo FROM utilizador WHERE numero=" + number;
        ResultSet rs = executeQuery(sql);
        while (rs.next()) {
            saldo = rs.getDouble("saldo");
        }
        return saldo;
    }

    public Utilizador getUtilizador(int number) throws SQLException {
        Utilizador usr = new Utilizador(number, -1);
        String sql = "SELECT *FROM utilizador WHERE numero=" + number;
        ResultSet rs = executeQuery(sql);
        while (rs.next()) {
            usr.setNome(rs.getString("nome"));
            usr.seteUtilizador(rs.getInt("permissao"));
            usr.setSaldo(getSaldo(number));
        }
        return usr;
    }

    /**
     * Efetua o login na aplicação
     *
     * @param number   número do utilizador inserido
     * @param password password do utilizador inserida
     * @return true SE o login for bem sucedido
     * @throws Exception SE o login não for bem sucedido
     */
    public static boolean login(int number, String password) throws Exception {
        String passwordDB;
        String sql = "SELECT password FROM utilizador WHERE numero=" + number;
        ResultSet rs = executeQuery(sql);
        if (rs.next()) {
            passwordDB = rs.getString("password");
        } else {
            throw new Exception("O número de utilizador não está registado!");
        }
        if (!PasswordUtils.verifyUserPassword(password, passwordDB, PasswordUtils.getSalt())) {
            throw new Exception("Password incorreta!");
        }
        return true;
    }

    /**
     * Devolve a Ementa
     *
     * @return ementa
     * @throws SQLException
     */
    public ArrayList<Refeicao> getEmenta(int userNumber) throws SQLException {
        ArrayList<Refeicao> ementa = new ArrayList<>();
        ArrayList<Integer> auxInt = new ArrayList<>();
        String currentDate = this.getCurrentDate();
        String endDate = this.getEndDate(currentDate);

        String sql2 = "SELECT idrefeicao FROM senha WHERE numero=" + userNumber;
        ResultSet rs2 = executeQuery(sql2);
        while (rs2.next()) {
            int idRef = rs2.getInt("idrefeicao");
            auxInt.add(idRef);
        }

        String sql = "SELECT * FROM refeicoes WHERE data>'" + currentDate + "' and data<='" + endDate + "'";
        ResultSet rs = executeQuery(sql);
        while (rs.next()) {
            int contador = 0;
            int idRefeicao = rs.getInt("idrefeicao");
            for (Integer elem : auxInt) {
                if (elem != idRefeicao)
                    contador++;
            }
            if (contador == auxInt.size()) {
                ArrayList<Complemento> complementos = this.getComplementos(idRefeicao);
                Refeicao newRefeicao = new Refeicao(idRefeicao,
                        rs.getString("sopa"),
                        rs.getString("pratocarne"),
                        rs.getString("pratopeixe"),
                        rs.getString("sobremesa1"),
                        rs.getString("sobremesa2"),
                        rs.getDouble("preco"),
                        rs.getInt("horario"),
                        rs.getString("data"),
                        complementos, false);
                ementa.add(newRefeicao);
            }else{
                ArrayList<Complemento> complementos = this.getComplementos(idRefeicao);
                Refeicao newRefeicao = new Refeicao(idRefeicao,
                        rs.getString("sopa"),
                        rs.getString("pratocarne"),
                        rs.getString("pratopeixe"),
                        rs.getString("sobremesa1"),
                        rs.getString("sobremesa2"),
                        rs.getDouble("preco"),
                        rs.getInt("horario"),
                        rs.getString("data"),
                        complementos, true);
                ementa.add(newRefeicao);
            }
        }
        return ementa;
    }

    /**
     * Devolve a todos os pratos da ementa
     *
     * @return ementa
     * @throws SQLException
     */
    public ArrayList<Refeicao> getEmentaToda() throws SQLException {
        ArrayList<Refeicao> ementa = new ArrayList<>();

        String sql = "SELECT * FROM refeicoes ORDER BY idrefeicao DESC";
        ResultSet rs = executeQuery(sql);
        while (rs.next()) {
            int contador = 0;
            int idRefeicao = rs.getInt("idrefeicao");

                ArrayList<Complemento> complementos = this.getComplementos(idRefeicao);
                Refeicao newRefeicao = new Refeicao(idRefeicao,
                        rs.getString("sopa"),
                        rs.getString("pratocarne"),
                        rs.getString("pratopeixe"),
                        rs.getString("sobremesa1"),
                        rs.getString("sobremesa2"),
                        rs.getDouble("preco"),
                        rs.getInt("horario"),
                        rs.getString("data"));
                ementa.add(newRefeicao);
        }
        return ementa;
    }


    /**
     * @param idUser identificador do utilizador
     * @return lista de senhas compradas pelo utilizador
     * @throws SQLException
     */
    public ArrayList<Senha> getSenhas(double idUser) throws SQLException {
        ArrayList<Senha> senhas = new ArrayList<>();
        String sql = "SELECT idsenha,prato,sobremensa,precototal,idrefeicao FROM senha WHERE numero=" + idUser + " ORDER BY idsenha desc";
        ResultSet rs = executeQuery(sql);
        while (rs.next()) {
            Senha senha = new Senha(rs.getInt("idsenha"),
                    rs.getString("prato"),
                    rs.getString("sobremensa"),
                    rs.getDouble("precototal"),
                    rs.getInt("idrefeicao"));
            senhas.add(senha);
        }
        return senhas;
    }

    /**
     *
     * @param id de uma determinada Refeição
     * @return Objeto Refeição, com toda a informação a si associada
     * @throws SQLException
     */
    public Refeicao getRefeicao(int id) throws SQLException {
        Refeicao refeicao = null;
        String sql = "SELECT * FROM refeicoes WHERE idrefeicao=" + id;
        ResultSet rs = executeQuery(sql);
        ArrayList<Complemento> complementos = this.getComplementos(id);
        while (rs.next()) {
            refeicao = new Refeicao(rs.getInt("idrefeicao"),
                    rs.getString("sopa"),
                    rs.getString("pratocarne"),
                    rs.getString("pratopeixe"),
                    rs.getString("sobremesa1"),
                    rs.getString("sobremesa2"),
                    rs.getDouble("preco"),
                    rs.getInt("horario"),
                    rs.getString("data"),
                    complementos);
        }
        return refeicao;
    }

    /**
     *
     * @param idRefeicao : id da refeição que se pretende consultar os complementos
     * @return uma lista de complementos refentes aquela refeição
     * @throws SQLException
     */
    public ArrayList<Complemento> getComplementos(int idRefeicao) throws SQLException {
        ArrayList<Complemento> complementos = new ArrayList<>();
        String sql = "SELECT * FROM complementorefeicao WHERE idrefeicao=" + idRefeicao;
        ResultSet rs = executeQuery(sql);
        while (rs.next()) {
            int idComplemento = rs.getInt("idcomplemento");
            String sqlC = "SELECT * FROM complemento WHERE idcomplemento=" + idComplemento;
            ResultSet rsC = executeQuery(sqlC);
            while (rsC.next()) {
                complementos.add(new Complemento(idComplemento,
                        rsC.getString("nome"),
                        rsC.getFloat("preco")));
            }
        }
        if (complementos.size() <= 0)
            complementos = null;
        return complementos;
    }

    /**
     * Coloca a data atual mais um dia no formato "YYYY/MM/DD"
     *
     * @return data atual
     */
    private String getCurrentDate() {
        Calendar calender = Calendar.getInstance();
        String currentDate = LocalDate.now().toString();
        return currentDate;
    }

    /**
     * Encontra a data de fim para o intervalo em que seram mostradas as senhas
     *
     * @param currentDate Data de inicio
     * @return data de fim
     */
    private String getEndDate(String currentDate) {
        String date = null;
        Calendar calender = Calendar.getInstance();
        String[] result = currentDate.split("-");
        calender.set(Calendar.YEAR, Integer.parseInt(result[0]));
        int mes = Integer.parseInt(result[1])-1;
        calender.set(Calendar.MONTH, mes);
        calender.set(Calendar.DAY_OF_MONTH, Integer.parseInt(result[2]));
        int dayOfWeek = calender.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                calender.add(Calendar.DAY_OF_MONTH, 4);
                break;
            case Calendar.TUESDAY:
                calender.add(Calendar.DAY_OF_MONTH, 3);
                break;
            case Calendar.WEDNESDAY:
                calender.add(Calendar.DAY_OF_MONTH, 2);
                break;
            case Calendar.THURSDAY:
                calender.add(Calendar.DAY_OF_MONTH, 1);
                break;
            case Calendar.FRIDAY:
                calender.add(Calendar.DAY_OF_MONTH, 7);
            case Calendar.SATURDAY:
                calender.add(Calendar.DAY_OF_MONTH, 6);
                break;
            case Calendar.SUNDAY:
                calender.add(Calendar.DAY_OF_MONTH, 5);
                break;
        }
        mes =  calender.get(Calendar.MONTH) +1;
        date = calender.get(Calendar.YEAR) + "-" + mes + "-" + calender.get(Calendar.DAY_OF_MONTH);
        return date;
    }

    /**
     *
     * @param userNumber : número do utilizador
     * @return lista de favoritos do utilizador
     * @throws SQLException
     */
    public ArrayList<Favoritos> getFavoritos(int userNumber) throws SQLException {
        ArrayList<Favoritos> favoritos = new ArrayList<>();
        ArrayList<Integer> favIds = new ArrayList<>();
        String sqlId = "SELECT idfavorito FROM favoritoutilizador WHERE numero=" + userNumber;
        ResultSet rsId = executeQuery(sqlId);
        while (rsId.next()){
            int favid = rsId.getInt("idfavorito");
            favIds.add(favid);
        }
        String sql = "SELECT * FROM favorito";
        ResultSet rs = executeQuery(sql);
        while (rs.next()){
            int conta = 0;
            int idfav = rs.getInt("idfavorito");
            for(int fav : favIds){
                if(fav == idfav){
                    Favoritos favorito = new Favoritos(rs.getInt("idfavorito"),
                            rs.getString("prato"),
                            rs.getInt("tipo"));
                    favoritos.add(favorito);
                    break;
                }
            }
        }
        return favoritos;
    }

    /**
     *
     * @param id da senha a elminar
     * @return true se bem sucedido
     * @throws Exception caso tenha ocorrido um erro ao eliminar a senha
     */
    public boolean deleteSenha(int id) throws Exception {
        String sql = "DELETE FROM Senha WHERE idsenha=" + id;
        int rs = executeUpdate(sql);
        if (rs != 1) {
            throw new Exception("Erro ao apagar a senha!");
        }
        sql = "DELETE FROM ComplementoSenha WHERE idsenha=" + id;
        rs = executeUpdate(sql);
        return true;
    }

    public boolean removeUtilizador( int numeroUtilizador) throws Exception {

        ArrayList<Senha> senhas = new ArrayList<>();
        senhas=getSenhas(numeroUtilizador);
        if(senhas.size()!=0){
            for(Senha se : senhas) {
                this.deleteSenha(se.getIdSenha());
            }
        }
        String sql=  "DELETE FROM utilizador WHERE numero=" + numeroUtilizador;
    int rs= executeUpdate(sql);
         if(rs != 1){
             throw  new  Exception("Erro ao remover o utilizador");
         }
    return true;
    }

    /**
     *
     * @param id da senha a consultar o preço
     * @return preço da senha
     * @throws Exception caso não exista uma senha com o ID indicado
     */
    public double getPrecoSenhaComprada(int id) throws Exception {
        double preco;
        String sql = "SELECT precototal FROM senha WHERE idsenha=" + id;
        ResultSet rs = executeQuery(sql);
        if (rs.next()) {
            preco = rs.getDouble("precototal");
        } else {
            throw new Exception("Não existe senha com o ID indicado!");
        }
        return preco;
    }

    /**
     *
     * @param number : número do utilizador a retirar o saldo
     * @param saldo : saldo a adicionar ao já existente
     * @throws SQLException
     */
    public void addSaldo(int number, double saldo) throws SQLException {
        double saldoAtual = getSaldo(number);
        saldoAtual += saldo;
        String sql = "UPDATE utilizador SET saldo=" + saldoAtual + " WHERE numero=" + number;
        int rs = executeUpdate(sql);
    }

    /**
     *
     * @param senha : Dados da nova Senha
     * @param user : número do utilziador
     * @return true se operação executada com sucesso| false se operação falhou
     * @throws SQLException
     */
    public boolean addSenha(Senha senha, int user) throws SQLException {
        String sql = "INSERT INTO senha (numero, idrefeicao, prato, sobremensa, precototal) VALUES ('" + user +
                "', '" + senha.getIdRefeicao() + "', '" +
                senha.getPrato() + "', '"
                + senha.getSombremesa() + "', '" +
                senha.getPreco() + "')";
        int rs = executeUpdate(sql);
        sql = "SELECT idsenha FROM senha ORDER BY idsenha DESC";
        ResultSet rsID = executeQuery(sql);
        int idSenha = 0;
        if (rsID.next())
            idSenha = rsID.getInt("idsenha");
        else
            return false;
        for (Complemento complemento : senha.getComplementos()) {
            sql = "INSERT INTO complementosenha (idsenha, idcomplemento) VALUES ('"
                    + idSenha + "', '" + complemento.getIdComplemento() + "')";
            int rs1 = executeUpdate(sql);
        }
        return true;
    }

    /**
     *
     * @param number : número do utilizador a remover saldo
     * @param saldo : valor a retirar do saldo
     * @throws SQLException
     */
    public void removeSaldo(int number, double saldo) throws SQLException {
        double saldoAtual = getSaldo(number);
        saldoAtual -= saldo;
        String sql = "UPDATE utilizador SET saldo=" + saldoAtual + " WHERE numero=" + number;
        int rs = executeUpdate(sql);
    }

    /**
     *  Verifica se ainda falta mais de 48 horas para uma refeição
     * @param idRefeicao : id da refeição
     * @return true - se faltar mais de 48 horas | false - se faltar menos de 48 horas
     */
    public boolean hasMoreThan48Hours(int idRefeicao) {
        Date data = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        boolean resp = false;
        String sql = "SELECT DATA FROM REFEICOES WHERE IDREFEICAO=" + idRefeicao;
        try {
            ResultSet rs = executeQuery(sql);
            if (rs.next()) {
                data = df.parse(rs.getString("DATA"));
            }
        } catch (SQLException | ParseException e) {
            return false;
        }
        Date now = new Date();
        long dif = data.getTime() - now.getTime();
        if (TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS) >= 1) resp = true;
        return resp;
    }

    /**
     *
     * @param id do favorito a remover
     * @return true se removido com sucesso
     * @throws Exception no caso de ter ocorrido um erro
     */
    public boolean removeFavorito(int id) throws Exception {
        String sql = "DELETE FROM favorito WHERE idfavorito=" + id;
        int rs = executeUpdate(sql);
        if (rs != 1) {
            throw new Exception("Erro ao apagar o favorito!");
        }
        String sql2 = "DELETE FROM favoritoutilizador WHERE idfavorito="+ id;
        int rs2 = executeUpdate(sql2);
        if (rs2!=1) {
            throw new Exception("Erro ao apagar o favorito!");
        }
       return true;
    }

    /**
     *
     * @return lista com senhas adquiridas pelos utilizadores do sistema
     * @throws SQLException
     */
    public ArrayList<RefeicaoAdmin> getSenhasCompradasAdmin() throws SQLException {
        ArrayList<RefeicaoAdmin> refeicoes = new ArrayList<>();
        String sql = "SELECT idrefeicao,horario,data FROM refeicoes ORDER BY idrefeicao DESC";
        ResultSet rs = executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("idrefeicao");
            int horario = rs.getInt("horario");
            String aux;
            if (horario == 0) {
                aux = "Almoco";
            } else aux = "Jantar";

            String d = rs.getString("data");

            String sqlquantPeixe = "SELECT COUNT(idsenha) FROM senha, refeicoes WHERE senha.idrefeicao=" + id + " AND senha.prato=refeicoes.pratopeixe;";
            ResultSet rsa = executeQuery(sqlquantPeixe);
            int qtpeixe = rsa.getInt("count(idsenha)");

            String sqlquantCarne = "SELECT COUNT(idsenha) FROM senha, refeicoes WHERE senha.idrefeicao=" + id + " AND senha.prato=refeicoes.pratocarne;";
            ResultSet rsc = executeQuery(sqlquantCarne);
            int qtcarne = rsc.getInt("count(idsenha)");
            refeicoes.add(new RefeicaoAdmin(id, d, aux, qtcarne, qtpeixe));

        }

        return refeicoes;
    }

    /**
     *
     * @param idSenha da senha a consultar
     * @return objeto Senha com os dados de uma senha
     * @throws SQLException
     */
    public Senha getSenha(int idSenha) throws SQLException {
        Senha senha = null;
        String sql = "SELECT idrefeicao, prato, sobremensa, precototal FROM senha WHERE idsenha=" + idSenha;
        ResultSet rs = executeQuery(sql);
        ArrayList<Complemento> complementos = this.getComplementosSenha(idSenha);
        while (rs.next()) {
            senha = new Senha(idSenha,
                    rs.getString("prato"),
                    rs.getString("sobremensa"),
                    rs.getDouble("precototal"),
                    rs.getInt("idrefeicao"),
                    complementos);
        }
        return senha;
    }

    /**
     *
     * @param idSenha da senha que se pretende obter os complementos
     * @return Lista de complementos da senha com o id = ideSenha
     * @throws SQLException
     */
    private ArrayList<Complemento> getComplementosSenha(int idSenha) throws SQLException {
        ArrayList<Complemento> complementos = new ArrayList<>();
        String sql = "SELECT * FROM complementosenha WHERE idsenha=" + idSenha;
        ResultSet rs = executeQuery(sql);
        while (rs.next()) {
            int idComplemento = rs.getInt("idcomplemento");
            String sqlC = "SELECT * FROM complemento WHERE idcomplemento=" + idComplemento;
            ResultSet rsC = executeQuery(sqlC);
            while (rsC.next()) {
                complementos.add(new Complemento(idComplemento,
                        rsC.getString("nome"),
                        rsC.getFloat("preco")));
            }
        }
        if (complementos.size() <= 0)
            complementos = null;
        return complementos;
    }

    /**
     *
     * @param prato descrição do prato favorito
     * @param tipo tipo do prato: carne | peixe
     * @param userNumber : número do utilizador
     * @return true se novo favorito adicionado com sucesso
     * @throws SQLException
     */
    public boolean addFavorito(String prato, int tipo, int userNumber) throws SQLException {
        String sql = "INSERT INTO favorito (prato, tipo) VALUES ('" + prato +"','" + tipo + "')";
        int rs = executeUpdate(sql);
        String sql2 = "SELECT idfavorito FROM favorito ORDER BY idfavorito DESC";
        int idFavorito = 0;
        ResultSet rsID = executeQuery(sql2);
        if(rsID.next())
            idFavorito = rsID.getInt("idfavorito");
        else
            return  false;
        String sql3 = "INSERT INTO favoritoutilizador (numero, idfavorito) VALUES ('" + userNumber +"','" + idFavorito + "')";
        int rs3 = executeUpdate(sql3);
        return true;
    }

    /**
     *
     * @param utilizador dados do novo utilizador
     * @return true se o novo utilizador foi adicionado com sucesso
     * @throws SQLException
     */
    public boolean addNewUser(Utilizador utilizador) throws SQLException {
        String sql = "INSERT INTO utilizador VALUES ('" + utilizador.getNumeroUtilizador() + "', '"
                                                + utilizador.getNome() + "', '"
                                                + utilizador.getPassword() + "', '"
                                                + utilizador.getSaldo() + "', '"
                                                + utilizador.geteUtilizador() + "')";
        int rs = executeUpdate(sql);
        return rs == 1;
    }


    /**
     *
     * @param novaSenha : Novos dados da Senha
     * @return preço antigo da senha
     * @throws Exception
     */
    public double changeSenha(Senha novaSenha) throws Exception {
        double precototalAntigo=-1;
        String sql = "SELECT precototal FROM senha WHERE idsenha=" + novaSenha.getIdSenha();
        ResultSet rs = executeQuery(sql);
        if(rs.next())
            precototalAntigo=rs.getDouble("precototal");
        else
            throw new Exception("Erro ao atualizar a senha!");
        sql="UPDATE senha SET prato='" + novaSenha.getPrato() + "', sobremensa='" + novaSenha.getSombremesa() + "', precototal=" + novaSenha.getPreco() + " WHERE idsenha=" + novaSenha.getIdSenha();
        int r=executeUpdate(sql);
        if(r!=1) throw new Exception("Erro ao atualizar a senha!");

        sql="DELETE FROM ComplementoSenha WHERE idsenha=" + novaSenha.getIdSenha();
        r = executeUpdate(sql);
        for(Complemento complemento : novaSenha.getComplementos()) {
            sql = "INSERT INTO complementosenha (idsenha, idcomplemento) VALUES ('"
                    + novaSenha.getIdSenha() + "', '" + complemento.getIdComplemento() + "')";
            r = executeUpdate(sql);
        }

        return precototalAntigo;
    }

    /**
     *
     * @param userNumber : número do utilizador
     * @return o número de favoritos que são pratos de carne
     * @throws SQLException
     */
    public int getNumPratosFavCarne(int userNumber) throws SQLException {
        int count=0;
        String sql="SELECT COUNT(numero) FROM favoritoutilizador, favorito WHERE favoritoutilizador.idfavorito=favorito.idfavorito AND favorito.tipo=0 AND favoritoutilizador.numero=" + userNumber;
        ResultSet rs = executeQuery(sql);
        if(!rs.next())
            throw new SQLException();
        count=rs.getInt("COUNT(numero)");
        return count;
    }

    /**
     *
     * @param userNumber : número do utilizador
     * @return o número de favoritos que são pratos de peixe
     * @throws SQLException
     */
    public int getNumPratosFavPeixe(int userNumber) throws SQLException {
        int count=0;
        String sql="SELECT COUNT(numero) FROM favoritoutilizador, favorito WHERE favoritoutilizador.idfavorito=favorito.idfavorito AND favorito.tipo=1 AND favoritoutilizador.numero=" + userNumber;
        ResultSet rs = executeQuery(sql);
        if(!rs.next())
            throw new SQLException();
        count=rs.getInt("COUNT(numero)");
        return count;
    }

    /**
     *
     * @param id de uma refeição
     * @return Lista de senhas que foram compradas que sejam de uma refeição com o idreeicao = id
     * @throws SQLException
     */
    public ArrayList<Integer> getSenhasDaRefeicao(int id) throws SQLException {
        ArrayList<Integer> senhas = new ArrayList<>();
        String sql ="SELECT idsenha FROM senha WHERE senha.idrefeicao = " +id;
        ResultSet rs = executeQuery(sql);
        while (rs.next()) {
            int idsenha = rs.getInt("idsenha");
                    senhas.add(idsenha);
        }
        return senhas;
    }

    /**
     *
     * @param idRefeicao : id da refeição a remover
     * @return true se a refeição foi removida com sucesso
     * @throws Exception caso tenha ocorrido um erro
     */
    public boolean removeRefeicao(int idRefeicao) throws Exception {
        String sql =" DELETE FROM refeicoes WHERE idrefeicao= " +idRefeicao;
        int rs = executeUpdate(sql);
        if (rs != 1) {
            throw new Exception("Erro ao apagar a refeicao");
        }
        sql = "DELETE FROM ComplementoRefeicao WHERE idrefeicao=" + idRefeicao;
        rs = executeUpdate(sql);
        return true;
    }

    /**
     *
     * @param ref dados da nova refeição
     * @return true se a nova refeição foi adicionada com sucesso
     * @throws Exception
     */
    public boolean addRefeicao(Refeicao ref) throws Exception {
        String sql ="INSERT  INTO refeicoes (sopa, pratocarne, pratopeixe, sobremesa1, sobremesa2, preco, horario, data)  VALUES('"+ref.getSopa()+"','" +ref.getPratoCarne() +"','"+ref.getPratoPeixe()+"','" +ref.getSombremesa1()+
               "','"+ref.getSombremesa2()+"',"+ ref.getPreco()+","+ref.getAlmocoJantar() + ",'"+ref.getData() +"')";
        int rs = executeUpdate(sql);
        if (rs != 1) {
            throw new Exception("Erro ao adicionar a refeição!");
        }
        sql = "SELECT idrefeicao FROM refeicoes ORDER BY idrefeicao DESC";
        ResultSet rsID = executeQuery(sql);
        int idRef = 0;
        if (rsID.next())
            idRef = rsID.getInt("idrefeicao");
        else
            throw new Exception("Erro ao adicionar os complementos da refeição!");
        for (Complemento complemento : ref.getComplementos()) {
            sql = "INSERT INTO complementorefeicao (idrefeicao, idcomplemento) VALUES ('"
                    + idRef + "', '" + complemento.getIdComplemento() + "')";
            int rs1 = executeUpdate(sql);
        }
        return true;
    }

    /**
     *
     * @return lista de todos os complementos existentes
     * @throws SQLException
     */
    public ArrayList<Complemento> getTodosComplementos() throws SQLException {
        ArrayList<Complemento> comp = new ArrayList<>();
        String sql="SELECT * FROM complemento";
        ResultSet rs = executeQuery(sql);
        while (rs.next()) {
            Complemento c = new Complemento(rs.getInt("idcomplemento"), rs.getString("nome"), rs.getDouble("preco"));
            comp.add(c);
        }
        return comp;
    }

    public ArrayList<Utilizador> getUserAdmin() throws SQLException {
            ArrayList<Utilizador> uti = new ArrayList<>();
            String sql = "SELECT numero,nome,saldo FROM UTILIZADOR WHERE PERMISSAO = " + 0;
            ResultSet rs = executeQuery(sql);
            while (rs.next()) {
                Utilizador us = new Utilizador(rs.getInt("numero"), rs.getString("nome"), rs.getFloat("saldo"));
                uti.add(us);
            }
            return uti;
    }

    public boolean changeRefeicao(Refeicao dadosRefeicao) throws Exception{
        String sql="UPDATE refeicoes SET sopa='" + dadosRefeicao.getSopa() + "', pratocarne='" + dadosRefeicao.getPratoCarne() + "', pratopeixe='" + dadosRefeicao.getPratoPeixe() +"', sobremesa1='" + dadosRefeicao.getSombremesa1() + "', sobremesa2='" + dadosRefeicao.getSombremesa2() +"', horario=" + dadosRefeicao.getAlmocoJantar() +", data='" + dadosRefeicao.getData() +"', preco=" + dadosRefeicao.getPreco() + " WHERE idrefeicao=" + dadosRefeicao.getIdRefeicao();
        int r=executeUpdate(sql);
        if(r!=1) throw new Exception("Erro ao atualizar a refeição!");

        sql="DELETE FROM ComplementoRefeicao WHERE idrefeicao=" + dadosRefeicao.getIdRefeicao();
        r = executeUpdate(sql);
        for(Complemento complemento : dadosRefeicao.getComplementos()) {
            sql = "INSERT INTO complementoRefeicao (idrefeicao, idcomplemento) VALUES ('"
                    + dadosRefeicao.getIdRefeicao() + "', '" + complemento.getIdComplemento() + "')";
            r = executeUpdate(sql);
        }
        return true;
    }

    /**
     * Altera a password de um utilizador
     * @param numeroUtilizador número do utilizador
     * @param securePassword nova password
     * @return true - se for bem sucedido
     * @throws SQLException
     */
    public boolean setNewPassword(int numeroUtilizador, String securePassword) throws SQLException {
        String sql = "UPDATE utilizador SET password='" + securePassword + "' WHERE numero =" + numeroUtilizador;
        int rs = executeUpdate(sql);
        return true;
    }

    /**
     *
     * @param utilizador Dados do utilizador atualizados
     * @param oldNumber Número do utilizador
     * @return true - se a atualização for bem sucedida
     * @throws SQLException
     */
    public boolean updateUser(Utilizador utilizador, int oldNumber) throws SQLException {
        String sql = "UPDATE utilizador SET nome='" + utilizador.getNome() + "', saldo='" + utilizador.getSaldo() + "' WHERE numero =" + oldNumber;
        int rs = executeUpdate(sql);
        return true;
    }

}


