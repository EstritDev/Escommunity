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
        ConstraintLayout btnPostar = findViewById(R.id.btnPostar);
        ConstraintLayout btnHome = findViewById(R.id.btnHome);
        ConstraintLayout btnPerfil = findViewById(R.id.btnPerfil);
        ConstraintLayout btnMessagens = findViewById(R.id.btnMessagens);

        //Carregar os posts
        carregarPosts();

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
                intent.putExtra("loginId", loginId);
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

    private void carregarPosts(){
        //buscar a tabela posts
        PostsDAO postsDAO = new PostsDAO(this);

        //Carregar a recycler view
        recyclerView = findViewById(R.id.rvPosts);
        ArrayList<Posts> listaPosts = postsDAO.getPosts();
        MyAdapter meuAdapter = new MyAdapter(listaPosts);
        recyclerView.setAdapter(meuAdapter);
        recyclerView.setLayoutManager(layoutManager);
        setAdapter(listaPosts);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarPosts();
    }
}