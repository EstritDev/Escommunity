package com.example.escommunity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        String idPost = getIntent().getStringExtra("id");
        Toast.makeText(getApplicationContext(), idPost, Toast.LENGTH_LONG).show();

        //Definir a caixa de texto com o conteudo do post
        txtEditarPost.setText(conteudo);

        //Vai buscar o postsDAO
        PostsDAO postsDAO = new PostsDAO(this);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtEditarPost.getText().length() > 0){
                    postsDAO.editarPost(txtEditarPost.getText().toString(), Integer.parseInt(idPost));
                }
            }
        });
    }
}