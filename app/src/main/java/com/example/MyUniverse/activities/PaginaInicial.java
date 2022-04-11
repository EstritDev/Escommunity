package com.example.MyUniverse.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.MyUniverse.R;
import com.example.MyUniverse.fragmentos.NovoPost;
import com.example.MyUniverse.fragmentos.PaginaInicialFragmento;
import com.example.MyUniverse.fragmentos.Perfil;
import com.example.MyUniverse.fragmentos.Procurar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PaginaInicial extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicial);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        //get loginId
        String loginId = getIntent().getStringExtra("loginId");

        //Não mudar as cores do layout mesmo que o telemovél esteja em darkmode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Esconder a actionbar(barra com o nome da app no topo)
        getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = findViewById(R.id.menuBottom);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();

                switch (item.getItemId()){
                    case R.id.pagina_inicial:
                        PaginaInicialFragmento paginaInicialFragmento = new PaginaInicialFragmento();
                        transaction.replace(R.id.fragment,paginaInicialFragmento);
                        transaction.commit();
                        return true;

                    case R.id.procurar:
                        Procurar procurar = new Procurar();
                        bundle.putString("loginId", loginId);
                        procurar.setArguments(bundle);
                        transaction.replace(R.id.fragment, procurar);
                        transaction.commit();
                        return true;

                    case R.id.novoPost:
                        NovoPost novoPost = new NovoPost();
                        bundle.putString("loginId", loginId);
                        novoPost.setArguments(bundle);
                        transaction.replace(R.id.fragment,novoPost);
                        transaction.commit();
                        return true;

/*                    case R.id.mensagens:
                        transaction.replace(R.id.fragment,paginaInicialFragmento);
                        transaction.commit();
                        return true;*/

                    case R.id.perfil:
                        Perfil perfil = new Perfil();
                        bundle.putString("loginId", loginId);
                        bundle.putString("userProfileId", loginId);
                        perfil.setArguments(bundle);
                        transaction.replace(R.id.fragment,perfil);
                        transaction.commit();
                        return true;
                }
                return true;
            }
        });
    }

}