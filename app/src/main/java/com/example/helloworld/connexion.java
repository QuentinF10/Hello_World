package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.ConnectionEvent;

public class connexion extends AppCompatActivity {

    private static Button btn_send;
    public static EditText pseudo,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        pseudo = (EditText) findViewById(R.id.pseudo);
        pass = (EditText) findViewById(R.id.pass);

        btn_send = (Button) findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick (View view) {
                connexion();
            }
        });
        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public void connexion(){
        try{
            Fonctions fonc = new Fonctions();
            Statement st = fonc.connexionSqlapp();
            String sql = "Select PASS from Utilisateurs WHERE pseudo = '"+pseudo.getText().toString()+"'";

            final ResultSet rs = st.executeQuery(sql);
            rs.next();

            if(rs.getString(1).equals(pass.getText().toString())){
                NextActivity();
                TextView loading = (TextView) findViewById(R.id.loading);
            }

            }catch( Exception e){
            e.printStackTrace();
            TextView loading = (TextView) findViewById(R.id.loading);
            loading.setText("Identifiant ou MDP incorrect");
        }
    }

    private void NextActivity(){
        Intent intent = new Intent(connexion.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}