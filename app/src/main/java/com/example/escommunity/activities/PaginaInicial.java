package com.example.escommunity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.escommunity.R;
import com.example.escommunity.fragmentos.NovoPost;
import com.example.escommunity.fragmentos.PaginaInicialFragmento;
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

        PaginaInicialFragmento paginaInicialFragmento = new PaginaInicialFragmento();
        Bundle bundle = new Bundle();
        bundle.putString("loginId", loginId);
        paginaInicialFragmento.setArguments(bundle);

        BottomNavigationView bottomNavigationView = findViewById(R.id.menuBottom);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        Toast.makeText(getApplicationContext(), loginId, Toast.LENGTH_LONG).show();
    }

}