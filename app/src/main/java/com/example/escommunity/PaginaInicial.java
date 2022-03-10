package com.example.escommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

        //Esconder a actionbar(barra com o nome da app no topo)
        getSupportActionBar().hide();

        //Variáveis
        String loginId = getIntent().getStringExtra("loginId");

        //Buttons
        ConstraintLayout btnPostar = findViewById(R.id.btnPostarPerfil);
        ConstraintLayout btnHome = findViewById(R.id.btnHomePerfil);
        ConstraintLayout btnPerfil = findViewById(R.id.btnPerfilPerfil);
        ConstraintLayout btnMessagens = findViewById(R.id.btnMessagensPerfil);
        ConstraintLayout btnProcurar = findViewById(R.id.btnProcurar);

        //Carregar os posts
        setAdapter();

        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(this);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaginaInicial.this,PerfilActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("loginId", loginId);
                intent.putExtra("userProfileLoginId", loginId);
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
                intent.putExtra("loginId", loginId);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });

        btnProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaginaInicial.this, ProcurarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("loginId", loginId);
                startActivity(intent);
            }
        });

    }

    private void setAdapter(){
        //buscar a tabela posts
        PostsDAO postsDAO = new PostsDAO(this);

        //Carregar a recycler view
        recyclerView = findViewById(R.id.rvPosts);
        ArrayList<Posts> listaPosts = postsDAO.getPosts();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        MyAdapter meuAdapter = new MyAdapter(listaPosts);
        recyclerView.setAdapter(meuAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onResume(){
        super.onResume();
        setAdapter();

    }
}