package com.example.helloworld;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Fonctions extends AppCompatActivity {
    public static String url = "jdbc:mysql://mysql-quentinf10.alwaysdata.net:3306/quentinf10_myapp";
    public static final String user = "286638_test";
    public static final String pass = "Aicei3huu9";

    public static Statement connexionSqlapp() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
             System.out.println("hello");
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
