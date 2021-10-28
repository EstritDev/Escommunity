package com.example.escommunity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PaginaInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicial);


        //Variáveis
        String user = getIntent().getStringExtra("user");
        Boolean memorizar = getIntent().getBooleanExtra("memorizar",false);

        //Labels
        TextView lblPosts = findViewById(R.id.lblPosts);

        //Buttons
        Button btnPostar = findViewById(R.id.btnPostar);

        //EditTexts
        EditText txtMsg = findViewById(R.id.txtMsg);

        btnPostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilizador utilizador = new Utilizador(user,memorizar);

                btnPostar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(txtMsg.getText().length() == 0){
                            return;
                        }
                        //Horas
                        Date agora = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("K:mm a");
                        String horas = sdf.format(agora);
                        lblPosts.setTextSize(38);
                        lblPosts.append(utilizador.getUser() + "\n");
                        lblPosts.setTextSize(14);
                        lblPosts.append(txtMsg.getText().toString());
                        lblPosts.setTextSize(7);
                        lblPosts.append("                               "+ horas);
                        lblPosts.append("\n");
                    }
                });
            }
        });

    }
}