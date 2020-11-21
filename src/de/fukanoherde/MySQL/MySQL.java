/*
 * Erstellt am 21.11.2020 um 21:51
 * Projectname FukanoKBFFA
 * Package de.fukanoherde.MySQL
 * Erstellt durch Fredd_HD
 */


package de.fukanoherde.MySQL;

import de.fukanoherde.FileSystem.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQL {

        private String HOST = (String) Config.getValue("MySQL.Host");
        private String DATABASE = (String) Config.getValue("MySQL.Database");
        private String USER = (String) Config.getValue("MySQL.User");
        private String PASSWORD = (String) Config.getValue("MySQL.Password\"");

        private Connection con;

        public MySQL(String host, String database, String user, String password) {
            this.HOST = host;
            this.DATABASE = database;
            this.USER = user;
            this.PASSWORD = password;

            connect();
        }

        public void connect() {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
                System.out.println("[MySQL] Die Verbindung zur MySQL wurde hergestellt!");
            } catch (SQLException e) {
                System.out.println("[MySQL] Die Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + e.getMessage());
            }
        }

        public void close() {
            try {
                if(con != null) {
                    con.close();
                    System.out.println("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
                }
            } catch (SQLException e) {
                System.out.println("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
            }
        }

        public void update(String qry) {
            try {
                Statement st = con.createStatement();
                st.executeUpdate(qry);
                st.close();
            } catch (SQLException e) {
                connect();
                System.err.println(e);
            }
        }

        public ResultSet query(String qry) {
            ResultSet rs = null;

            try {
                Statement st = con.createStatement();
                rs = st.executeQuery(qry);
            } catch (SQLException e) {
                connect();
                System.err.println(e);
            }
            return rs;
        }

}
