package modelo;

import modelo.Password.PasswordUtils;

import java.sql.*;

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
    public static double getSaldo(int number) throws SQLException {
        double saldo=-1;
        String sql="SELECT saldo FROM utilizador WHERE numero=" + number;
        ResultSet rs=executeQuery(sql);
        while(rs.next()) {
            saldo = rs.getDouble("saldo");
        }
        return saldo;
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
        if(rs.next()) {
            passwordDB=rs.getString("password");
        }else{
            throw new Exception("O número de utilizador não está registado!");
        }
        if(!PasswordUtils.verifyUserPassword(password, passwordDB, PasswordUtils.getSalt())){
            throw new Exception("Password incorreta!");
        }
        return true;
    }
    
}

