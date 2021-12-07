package com.example.escommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PaginaInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicial);


        //Não mudar as cores do layout mesmo que o telemovél esteja em darkmode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Variáveis
        String user = getIntent().getStringExtra("user");
        Boolean memorizar = getIntent().getBooleanExtra("memorizar",false);

        //Labels
        TextView lblPosts = findViewById(R.id.lblPosts);
        lblPosts.append("\n");

        //Buttons
        Button btnPostar = findViewById(R.id.btnPostar);
        ConstraintLayout btnHome = findViewById(R.id.btnHome);
        ConstraintLayout btnPerfil = findViewById(R.id.btnPerfil);
        ConstraintLayout btnMessagens = findViewById(R.id.btnMessagens);

        //EditTexts
        EditText txtMsg = findViewById(R.id.txtMsg);


        //Colocar os posts com scroll
        lblPosts.setMovementMethod(new ScrollingMovementMethod());

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
                        lblPosts.setTextSize(20);
                        lblPosts.append(utilizador.getUser() + ":\n");
                        //lblPosts.setTextSize(14);
                        lblPosts.append(txtMsg.getText().toString());
                        //lblPosts.setTextSize(7);
                        lblPosts.append("                               "+ horas);
                        lblPosts.append("\n");
                        txtMsg.setText("");
                    }
                });
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaginaInicial.this,PerfilActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("user", user);
                startActivity(intent);

            }
        });

        btnMessagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Ação indisponível", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Boolean memorizar = getIntent().getBooleanExtra("memorizar",false);
        String user = getIntent().getStringExtra("user");
        if(memorizar){
            Intent intent = new Intent(PaginaInicial.this, PaginaInicial.class);
            intent.putExtra("user", user);
            intent.putExtra("memorizar", memorizar);
            startActivity(intent);
        }
    }
}