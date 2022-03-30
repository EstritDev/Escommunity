package com.example.escommunity.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.escommunity.R;
import com.example.escommunity.daos.PostsDAO;

public class EditPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        //Caixas de texto
        EditText txtEditarPost = findViewById(R.id.txtEditarPost);

        //Buttons
        Button btnEditar = findViewById(R.id.btnEditar);

        //Variáveis
        String conteudo = getIntent().getStringExtra("conteudo");
        String idPost = getIntent().getStringExtra("idPost");

        //Definir a caixa de texto com o conteudo do post
        txtEditarPost.setText(conteudo);

        //Vai buscar o postsDAO
        PostsDAO postsDAO = new PostsDAO(this);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtEditarPost.getText().length() > 0){
                    postsDAO.editarPost(txtEditarPost.getText().toString(), Integer.parseInt(idPost));
                    finish();
                }
            }
        });
    }
}