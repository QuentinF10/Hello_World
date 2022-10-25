package com.example.helloworld;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Fonctions {
    public static String url = "jdbc:mysql://mysql-quentinf10.alwaysdata.net:3306/quentinf10_myapp";
    public static final String user = "286638_test";
    public static final String pass = "Aicei3huu9";

    public static Statement connexionSqlapp() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("connected to the server");
            Statement st = conn.createStatement();
            return st;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
