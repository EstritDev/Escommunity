package com.example.escommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NovoPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_post);

        //variaveis
        String loginId = getIntent().getStringExtra("loginId");

        //buscar a tabela posts
        PostsDAO postsDAO = new PostsDAO(this);

        //Esconder a actionbar(barra com o nome da app no topo)
        getSupportActionBar().hide();

        //Buttons
        Button btnPublicar;
        ImageButton btnVoltar;
        btnVoltar = findViewById(R.id.btnVoltar);
        btnPublicar = findViewById(R.id.btnPublicar);

        //Caixas de texto
        EditText txtNovoPost;
        txtNovoPost = findViewById(R.id.txtNovoPost);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNovoPost.getText().length() > 0){
                    //Enviar mensagem de confirmação
                }
                finish();
            }
        });

        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNovoPost.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Tem escrever alguma coisa.", Toast.LENGTH_LONG).show();
                    return;
                }
                //Buscar a data atual
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                String dia = sdf.format(calendar.getTime());
                //Guardar o post na base de dados
                postsDAO.guardarPost(loginId,txtNovoPost.getText().toString(),dia);
                txtNovoPost.setText("");
                finish();
            }
        });
    }
}