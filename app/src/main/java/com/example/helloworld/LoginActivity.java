package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout til_pseudo, til_password;
    private Button btn_send;
    private RequestQueue queue;
    private MyRequest  request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        til_pseudo = (TextInputLayout) findViewById(R.id.til_pseudo_log);
        til_password = (TextInputLayout)  findViewById(R.id.til_password_log);
        btn_send = (Button) findViewById(R.id.btn_send);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pseudo = til_pseudo.getEditText().getText().toString().trim();
                String password = til_password.getEditText().getText().toString().trim();
                if (pseudo.length() > 0 && password.length() > 0) {
                    request.connection(pseudo, password, new MyRequest.LoginCallback() {
                        @Override
                        public void onSuccess(String id, String pseudo) {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();

                        }

                        @Override
                        public void onError(String message) {
                            Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();

                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}