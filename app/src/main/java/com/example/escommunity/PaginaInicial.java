package com.example.escommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.view.WindowCompat;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PaginaInicial extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adaptador;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicial);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        //Não mudar as cores do layout mesmo que o telemovél esteja em darkmode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Variáveis
        String loginId = getIntent().getStringExtra("loginId");


        //Buttons
        ConstraintLayout btnPostar = findViewById(R.id.btnPostar);
        ConstraintLayout btnHome = findViewById(R.id.btnHome);
        ConstraintLayout btnPerfil = findViewById(R.id.btnPerfil);
        ConstraintLayout btnMessagens = findViewById(R.id.btnMessagens);

        //Carregar a recycler view
        recyclerView = findViewById(R.id.rvPosts);
        Bundle args = getIntent().getBundleExtra("ArrayJogadores");
        ArrayList<Posts> listaPosts = new ArrayList<Posts>();
        MyAdapter meuAdapter = new MyAdapter(listaPosts);
        recyclerView.setAdapter(meuAdapter);
        recyclerView.setLayoutManager(layoutManager);
        setAdapter(listaPosts);

        btnPostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPostar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Ação indisponivel", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaginaInicial.this,PerfilActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("loginId", loginId);
                startActivity(intent);

            }
        });

        btnMessagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Ação indisponível", Toast.LENGTH_LONG).show();
            }
        });

        btnPostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaginaInicial.this,NovoPostActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }

    private void setAdapter(ArrayList<Posts> listaPosts){
        MyAdapter adapter = new MyAdapter(listaPosts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}