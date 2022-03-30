package com.example.escommunity.activities;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.escommunity.R;
import com.example.escommunity.daos.UtilizadoresDAO;

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
       // Utilizador utilizador = utilizadoresDAO.getUserData(userProfileLoginId);

        //Coloca o nome de utilizador por baixo da foto de perfil

        //lblNome.setText(utilizador.getNome());

        //Coloca a descrição
        //lblDesc.setText(utilizador.getDesc());

        //Coloca o botão de editar

        /*if(loginId.equals(userProfileLoginId)){
            btnEditarPerfil.setVisibility(View.INVISIBLE);
        }*/
    }

    @Override
    public void onResume(){
        super.onResume();
/*        //buscar a tabela utilizadores
        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(this);

        TextView lblDesc = findViewById(R.id.lblDesc);
        String loginId = getIntent().getStringExtra("loginId");
        String userProfileLoginId = getIntent().getStringExtra("userProfileLoginId");
        Utilizador utilizador = utilizadoresDAO.getUserData(userProfileLoginId);


        //Coloca a descrição quando o utilizador voltar a este intent
        lblDesc.setText(utilizador.getDesc());*/
    }
}