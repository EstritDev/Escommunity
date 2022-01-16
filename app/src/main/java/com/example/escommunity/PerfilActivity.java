package com.example.escommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Tirar as animações
        getWindow().setWindowAnimations(0);

        //Não mudar as cores do layout mesmo que o telemovél esteja em darkmode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //buscar a tabela utilizadores
        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(this);

        //Variáveis
        String loginId = getIntent().getStringExtra("loginId");
        Utilizador utilizador = utilizadoresDAO.getUserData(loginId);

        //labels
        TextView lblNome = findViewById(R.id.lblNome);
        TextView lblDesc = findViewById(R.id.lblDesc);

        //Buttons
        ImageButton btnEditarPerfil = findViewById(R.id.btnEditarPerfil);
        ConstraintLayout btnHome = findViewById(R.id.btnHome);
        ConstraintLayout btnPerfil = findViewById(R.id.btnPerfil);
        ConstraintLayout btnMessagens = findViewById(R.id.btnMessagens);

        //Coloca o nome de utilizador por baixo da foto de perfil
        lblNome.setText(utilizador.getNome());

        //Coloca a descrição
        lblDesc.setText(utilizador.getDesc());

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnMessagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Ação indisponível", Toast.LENGTH_LONG).show();
            }
        });

        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilActivity.this, EditarPerfilActivity.class);
                intent.putExtra("loginId", loginId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }
}