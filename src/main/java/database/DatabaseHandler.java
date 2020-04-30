package database;

import java.sql.*;

public class DatabaseHandler {
    private static DatabaseHandler handler;

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
            conn.setAutoCommit(false);
            System.out.println("-- Opened database successfully");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void setupPersonTable() {
        String TABLE_NAME = "PERSON";

        try{
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(),null);
            if (tables.next()){
                System.out.println("Table " + TABLE_NAME + " already exists.");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME +" ("
                        +"id varchar(200) primary key,"
                        +"firstName varchar(200),"
                        +"secondName varchar(200),"
                        +"middleName varchar(200))");

                conn.commit();
            }
        } catch (SQLException e){
            System.err.println(e.getMessage() + " --- setupDatabase");
        } finally {

        }
    }
}
