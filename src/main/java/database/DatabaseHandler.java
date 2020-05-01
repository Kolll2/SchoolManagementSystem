package database;

import helpers.ConsoleHelper;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.*;

public class DatabaseHandler {
    private static DatabaseHandler handler;
    private static Throwable lastException;

    private static final String DB_URL = "jdbc:postgresql://localhost:5435/smsystem";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static String user = "postgres";
    private static String pass = "111";

    public DatabaseHandler() {
        createConnection();
        setupPersonTable();
    }

    void createConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, user, pass);
            System.out.println("--> Opened database successfully");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void setupPersonTable() {
        String TABLE_NAME = "person";

        try{
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME,null);
            if (tables.next()){
                System.out.println("Table " + TABLE_NAME + " already exists.");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME +" ("
                        +"id varchar(200) primary key,"
                        +"firstName varchar(200),"
                        +"secondName varchar(200),"
                        +"middleName varchar(200))");

            }
        } catch (SQLException e){
            lastException = e;
            System.err.println(ConsoleHelper.toUTF8(e.getMessage()) + " --- setupDatabase");
        } finally {

        }
    }

    public ResultSet execQuery (String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException e) {
            lastException = e;
            System.out.println("Exeption at execQuery: dataHandler "
                    + ConsoleHelper.toUTF8(e.getLocalizedMessage()));
            return null;
        } finally {

        }
        return result;
    }

    public boolean execAction (String query) {
        try {
            stmt = conn.createStatement();
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            lastException = e;
            System.out.println("Exception at execQuery:dataHandler "
                    + ConsoleHelper.toUTF8(e.getLocalizedMessage()));
            return false;
        } finally {

        }
    }

    public static Throwable getLastException() {
        return lastException;
    }
}
