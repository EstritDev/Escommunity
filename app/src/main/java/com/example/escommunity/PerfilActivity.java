package com.example.escommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Tirar as animações
        getWindow().setWindowAnimations(0);

        //Não mudar as cores do layout mesmo que o telemovél esteja em darkmode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Variáveis
        String user = getIntent().getStringExtra("user");

        //labels
        TextView lblNome = findViewById(R.id.lblNome);

        //Buttons
        ImageButton btnEditarPerfil = findViewById(R.id.btnEditarPerfil);
        ConstraintLayout btnHome = findViewById(R.id.btnHome);
        ConstraintLayout btnPerfil = findViewById(R.id.btnPerfil);
        ConstraintLayout btnMessagens = findViewById(R.id.btnMessagens);

        //Coloca o nome de utilizador por baixo da foto de perfil
        lblNome.setText(user);

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
                Toast.makeText(getApplicationContext(),"Ação indisponível", Toast.LENGTH_LONG).show();
            }
        });
    }
}