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

        //labels
        TextView lblNome = findViewById(R.id.lblNome);
        TextView lblDesc = findViewById(R.id.lblDesc);

        //Buttons
        ImageButton btnEditarPerfil = findViewById(R.id.btnEditarPerfil);
        ConstraintLayout btnHome = findViewById(R.id.btnHomePerfil);
        ConstraintLayout btnPerfil = findViewById(R.id.btnPerfilPerfil);
        ConstraintLayout btnMessagens = findViewById(R.id.btnMessagensPerfil);
        ConstraintLayout btnPostar = findViewById(R.id.btnPostarPerfil);
        ConstraintLayout btnProcurar = findViewById(R.id.btnProcurarPerfil);

        //Tirar as animações
        getWindow().setWindowAnimations(0);

        //Não mudar as cores do layout mesmo que o telemovél esteja em darkmode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Esconder a actionbar(barra com o nome da app no topo)
        getSupportActionBar().hide();

        //buscar a tabela utilizadores
        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(this);

        //Variáveis
        String loginId = getIntent().getStringExtra("loginId");
        String userProfileLoginId = getIntent().getStringExtra("userProfileLoginId");
        Utilizador utilizador = utilizadoresDAO.getUserData(userProfileLoginId);

        //Coloca o nome de utilizador por baixo da foto de perfil
        lblNome.setText(utilizador.getNome());

        //Coloca a descrição
        lblDesc.setText(utilizador.getDesc());

        //Coloca o botão de editar

        /*if(loginId.equals(userProfileLoginId)){
            btnEditarPerfil.setVisibility(View.INVISIBLE);
        }*/

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilActivity.this,PerfilActivity.class);
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

        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilActivity.this, EditarPerfilActivity.class);
                intent.putExtra("loginId", loginId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        btnPostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(PerfilActivity.this,NovoPostActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("loginId", loginId);
                        startActivity(intent);
            }
        });
        btnProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilActivity.this, ProcurarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("loginId", loginId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        //buscar a tabela utilizadores
        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(this);

        TextView lblDesc = findViewById(R.id.lblDesc);
        String loginId = getIntent().getStringExtra("loginId");
        String userProfileLoginId = getIntent().getStringExtra("userProfileLoginId");
        Utilizador utilizador = utilizadoresDAO.getUserData(userProfileLoginId);


        //Coloca a descrição quando o utilizador voltar a este intent
        lblDesc.setText(utilizador.getDesc());
    }
}