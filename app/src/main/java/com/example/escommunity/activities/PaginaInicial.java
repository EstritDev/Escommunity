package com.example.escommunity.activities;

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
import com.example.escommunity.fragmentos.novoPost;
import com.example.escommunity.fragmentos.pagina_inicial;
import com.example.escommunity.fragmentos.perfil;
import com.example.escommunity.fragmentos.procurar;
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

        Fragment novoPost = new novoPost();
        Bundle bundle = new Bundle();
        bundle.putString("loginId", loginId);
        novoPost.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, novoPost).commit();

    }

}