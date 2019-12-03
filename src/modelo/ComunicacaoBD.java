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
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void connectToDatabase() throws ClassNotFoundException, SQLException{
        Class.forName(JDBC_DRIVER);
        conn=DriverManager.getConnection(DB_URL);
    }

    /**
     * Encerra a ligação à base de dados
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException{
        conn.close();
    }

    /**
     * Executa uma consulta à base de dados
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
     * @param number número do utilizador a pesquisar
     * @return saldo do utilizador
     * @throws SQLException
     */
    public double getSaldo(int number) throws SQLException {
        double saldo=-1;
        String sql="SELECT saldo FROM utilizador WHERE numero=" + number;
        ResultSet rs=executeQuery(sql);
        while(rs.next()) {
            saldo = rs.getDouble("saldo");
        }
        return saldo;
    }

    public Utilizador  getUtilizador(int number ) throws  SQLException{
       Utilizador usr= new Utilizador(number,-1);
        String sql="SELECT *FROM utilizador WHERE numero=" + number;
        ResultSet rs=executeQuery(sql);
        while(rs.next()) {
            usr.setNome(rs.getString("nome"));
            usr.seteUtilizador(rs.getInt("permissao"));
            usr.setSaldo(getSaldo(number));
        }
        return  usr;
    }

    /**
     * Efetua o login na aplicação
     * @param number número do utilizador inserido
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

     /** Devolve a Ementa
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
        while(rs2.next()){
            int idRef = rs2.getInt("idrefeicao");
            auxInt.add(idRef);
        }
        
        String sql = "SELECT * FROM refeicoes WHERE data>'" + currentDate + "' and data<='" + endDate +"'";
        ResultSet rs = executeQuery(sql);
        while (rs.next()){
            int contador = 0;
            int idRefeicao = rs.getInt("idrefeicao");
            for(Integer elem : auxInt){
                if(elem != idRefeicao)
                    contador++;
            }
            if(contador == auxInt.size()){
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
                        complementos);
                ementa.add(newRefeicao);
            }
        }
        return ementa;
    }

    /**
     *
     * @param idUser identificador do utilizador
     * @return lista de senhas compradas pelo utilizador
     * @throws SQLException
     */
    public ArrayList<Senha> getSenhas(double idUser) throws SQLException {
        ArrayList<Senha> senhas = new ArrayList<>();
        String sql = "SELECT idsenha,prato,sobremensa,precototal,idrefeicao FROM senha WHERE numero=" + idUser + " ORDER BY idsenha desc";
        ResultSet rs = executeQuery(sql);
        while (rs.next()){
            Senha senha = new Senha(rs.getInt("idsenha"),
                    rs.getString("prato"),
                    rs.getString("sobremensa"),
                    rs.getDouble("precototal"),
                    rs.getInt("idrefeicao"));
            senhas.add(senha);
        }
        return senhas;
    }

    public Refeicao getRefeicao(int id) throws SQLException {
        Refeicao refeicao=null;
        String sql = "SELECT * FROM refeicoes WHERE idrefeicao=" + id;
        ResultSet rs = executeQuery(sql);
        ArrayList<Complemento> complementos = this.getComplementos(id);
        while (rs.next()){
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

    private ArrayList<Complemento> getComplementos(int idRefeicao) throws SQLException {
        ArrayList<Complemento> complementos = new ArrayList<>();
        String sql = "SELECT * FROM complementorefeicao WHERE idrefeicao=" + idRefeicao;
        ResultSet rs = executeQuery(sql);
        while (rs.next()){
            int idComplemento = rs.getInt("idcomplemento");
            String sqlC = "SELECT * FROM complemento WHERE idcomplemento=" + idComplemento;
            ResultSet rsC = executeQuery(sqlC);
            while(rsC.next()){
                complementos.add(new Complemento(idComplemento,
                        rsC.getString("nome"),
                        rsC.getFloat("preco")));
            }
        }
        if(complementos.size() <= 0)
            complementos = null;
        return complementos;
    }

    /**
     * Coloca a data atual mais um dia no formato "YYYY/MM/DD"
     * @return data atual
     */
    private String getCurrentDate(){
        Calendar calender = Calendar.getInstance();
        String currentDate = LocalDate.now().toString();
        return  currentDate;
    }

    /**
     * Encontra a data de fim para o intervalo em que seram mostradas as senhas
     * @param currentDate Data de inicio
     * @return data de fim
     */
    private String getEndDate(String currentDate){
        String date = null;

        Calendar calender = Calendar.getInstance();
        String[] result  = currentDate.split("-");
        calender.set(Calendar.YEAR, Integer.parseInt(result[0]));
        calender.set(Calendar.MONTH, Integer.parseInt(result[1]));
        calender.set(Calendar.DAY_OF_MONTH, Integer.parseInt(result[2]));  //

        int dayOfWeek = calender.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek -2;
        switch (dayOfWeek){
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
        date =calender.get(Calendar.YEAR) + "-" + calender.get(Calendar.MONTH) + "-" + calender.get(Calendar.DAY_OF_MONTH);
        return date;
    }


    public ArrayList<Favoritos> getFavoritos() throws SQLException {
        ArrayList<Favoritos> favoritos = new ArrayList<>();
        String sql = "SELECT idfavorito,prato FROM favorito";
        ResultSet rs = executeQuery(sql);
        while (rs.next()){
            Favoritos favorito = new Favoritos(rs.getInt("idfavorito"),
                                                rs.getString("prato"));
            favoritos.add(favorito);
        }
        return favoritos;
    }


    public boolean deleteSenha(int id) throws Exception {
        String sql = "DELETE FROM Senha WHERE idsenha=" + id;
        int rs = executeUpdate(sql);
        if (rs!=1) {
            throw new Exception("Erro ao apagar a senha!");
        }
        sql="DELETE FROM ComplementoSenha WHERE idsenha=" + id;
        rs = executeUpdate(sql);
        return true;
    }

    public double getPrecoSenhaComprada(int id) throws Exception{
        double preco;
        String sql="SELECT precototal FROM senha WHERE idsenha=" + id;
        ResultSet rs=executeQuery(sql);
        if (rs.next()) {
            preco=rs.getDouble("precototal");
        }else{
            throw new Exception("Não existe senha com o ID indicado!");
        }
        return preco;
    }

    public void addSaldo(int number, double saldo) throws SQLException{
        double saldoAtual=getSaldo(number);
        saldoAtual+=saldo;
        String sql="UPDATE utilizador SET saldo=" + saldoAtual + " WHERE numero=" + number;
        int rs=executeUpdate(sql);
    }

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
        if(rsID.next())
            idSenha = rsID.getInt("idsenha");
        else
            return  false;
        for(Complemento complemento : senha.getComplementos()) {
            sql = "INSERT INTO complementosenha (idsenha, idcomplemento) VALUES ('"
                    + idSenha + "', '" + complemento.getIdComplemento() + "')";
            int rs1 = executeUpdate(sql);
        }
        return  true;
    }

    public void removeSaldo(int number, double saldo) throws SQLException {
        double saldoAtual = getSaldo(number);
        saldoAtual -= saldo;
        String sql = "UPDATE utilizador SET saldo=" + saldoAtual + " WHERE numero=" + number;
        int rs = executeUpdate(sql);
    }

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

    public boolean removeFavorito(int id) throws Exception {
        String sql = "DELETE FROM favorito WHERE idfavorito=" + id;
        int rs = executeUpdate(sql);
        if (rs!=1) {
            throw new Exception("Erro ao apagar o favorito!");
        }
       return true;
    }

    public ArrayList<RefeicaoAdmin> getSenhasCompradasAdmin() throws SQLException {
        ArrayList<RefeicaoAdmin> refeicoes = new ArrayList<>();
        String sql = "SELECT idrefeicao,horario,data FROM refeicoes";
        ResultSet rs = executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("idrefeicao");
            int horario = rs.getInt("horario");
            String aux;
            if (horario == 0) {
                aux = "Almoco";
            } else aux = "Jantar";

            String d = rs.getDate("data").toString();

            String sqlquantPeixe = "SELECT COUNT(idsenha) FROM senha, refeicoes" +
                    "WHERE " + id + "refeicoes.idrefeicao= senha.idrefeicao" +
                    "AND senha.prato=refeicoes.pratopeixe;";
            ResultSet rsa = executeQuery(sqlquantPeixe);
            int qtpeixe = rsa.getInt("count(idsenha)");

            String sqlquantCarne = "SELECT COUNT(idsenha) FROM senha, refeicoes" +
                    "WHERE " + id + "refeicoes.idrefeicao= senha.idrefeicao" +
                    "AND senha.prato=refeicoes.pratocarne;";
            ResultSet rsc = executeQuery(sqlquantCarne);
            int qtcarne = rsa.getInt("count(idsenha)");

            refeicoes.add(new RefeicaoAdmin(id, d, aux, qtcarne, qtpeixe));

        }

        return refeicoes;
    }

    public Senha getSenha(int idSenha) throws SQLException {
        Senha senha=null;
        String sql = "SELECT idrefeicao, prato, sobremensa, precototal FROM senha WHERE idsenha=" + idSenha;
        ResultSet rs = executeQuery(sql);
        ArrayList<Complemento> complementos = this.getComplementosSenha(idSenha);
        while (rs.next()){
            senha = new Senha(idSenha,
                    rs.getString("prato"),
                    rs.getString("sobremensa"),
                    rs.getDouble("precototal"),
                    rs.getInt("idrefeicao"),
                    complementos);
        }
        return senha;
    }

    private ArrayList<Complemento> getComplementosSenha(int idSenha) throws SQLException {
        ArrayList<Complemento> complementos = new ArrayList<>();
        String sql = "SELECT * FROM complementosenha WHERE idsenha=" + idSenha;
        ResultSet rs = executeQuery(sql);
        while (rs.next()){
            int idComplemento = rs.getInt("idcomplemento");
            String sqlC = "SELECT * FROM complemento WHERE idcomplemento=" + idComplemento;
            ResultSet rsC = executeQuery(sqlC);
            while(rsC.next()){
                complementos.add(new Complemento(idComplemento,
                        rsC.getString("nome"),
                        rsC.getFloat("preco")));
            }
        }
        if(complementos.size() <= 0)
            complementos = null;
        return complementos;
    }
}

