package modelo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        while(rs.next())
            saldo=rs.getDouble("saldo");
        return saldo;
    }

    /**
     * Devolve a Ementa
     * @return ementa
     * @throws SQLException
     */
    public List<Refeicao> getEmenta() throws SQLException {
        List<Refeicao> ementa = new ArrayList<>();
        String currentDate = this.getCurrentDate();
        String endDate = this.getEndDate(currentDate);
        String sql = "SELECT * FROM refeicoes WHERE date>" + currentDate + " and date<" + endDate;
        ResultSet rs = executeQuery(sql);
        while (rs.next()){
            Refeicao newRefeicao = new Refeicao(rs.getInt("ifrefeicao"),
                    rs.getString("sopa"),
                    rs.getString("pratocarne"),
                    rs.getString("pratopeixe"),
                    rs.getString("sobremesa1"),
                    rs.getString("sobremesa2"),
                    rs.getDouble("preco"),
                    rs.getInt("horario"),
                    rs.getDate("data"));
            ementa.add(newRefeicao);
        }
        return ementa;
    }

    /**
     * Coloca a data atual mais um dia no formato "YYYY/MM/DD"
     * @return data atual
     */
    private String getCurrentDate(){
        Calendar calender = Calendar.getInstance();
        String currentDate = LocalDate.now().toString();
        String[] result  = currentDate.split("-");
        for(int i=0; i< result.length; i++){
            currentDate = result[i];
            if(i != 2)
                currentDate += "/";
        }
        Date d = new Date(currentDate);
        calender.setTime(d);
        calender.add(Calendar.DAY_OF_MONTH, 2);
        currentDate = calender.get(Calendar.YEAR) + "/" + calender.get(Calendar.MONTH) + "/" + calender.get(Calendar.DAY_OF_MONTH);
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
        Date d = new Date(currentDate);
        calender.setTime(d);

        int dayOfWeek = calender.get(Calendar.DAY_OF_WEEK);
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
        date = calender.get(Calendar.YEAR) + "/" + calender.get(Calendar.MONTH) + "/" + calender.get(Calendar.DAY_OF_MONTH);
        return date;
    }
    
}

