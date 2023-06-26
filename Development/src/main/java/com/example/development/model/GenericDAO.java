package com.example.development.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class GenericDAO {

    // path della cartella con il database
    protected static String databasePath = "/opt/Apache/db-derby/lib/testDB";
    protected Connection conn;

    public static void closeConnection(Connection connection){
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }catch(SQLException e){
            System.out.println("Errore chiudendo connessione: " + e);
            e.printStackTrace();
        }
    }

    // ritorna una Connection al database
    public static Connection getConnection(){
        Connection ret = null;

        try{
            // carica il driver JDBC e si connette al db
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            ret = DriverManager.getConnection("jdbc:derby:" + databasePath);

            if(ret == null){
                throw new SQLException();
            }
        }
        catch(ClassNotFoundException |SQLException e){
            System.out.println("Errore connessione al database: " + e.toString());
            e.printStackTrace();
        }

        return ret;
    }
}
