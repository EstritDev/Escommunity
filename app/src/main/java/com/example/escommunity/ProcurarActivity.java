package com.example.escommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;

public class ProcurarActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adaptador;
    RecyclerView.LayoutManager layoutManager;
    UtilizadoresDAO utilizadoresDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar);

        //Variáveis
        String loginId = getIntent().getStringExtra("loginId");

        //tabela utilizadores
        utilizadoresDAO = new UtilizadoresDAO(this);

        EditText txtProcurar;
        txtProcurar = findViewById(R.id.txtProcurar);

        //Carregar utilizadores
        setAdapter();

        txtProcurar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                procurar(txtProcurar.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void procurar(String arg){
        //buscar a tabela utilizadores
        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(this);

        //Carregar a recycler view
        recyclerView = findViewById(R.id.rvUtilizadores);
        ArrayList<Utilizador> listUtilizadores = utilizadoresDAO.procurarUsers(arg);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        UsersAdapter usersAdapter = new UsersAdapter(listUtilizadores);
        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setAdapter(){
        //buscar a tabela utilizadores
        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(this);

        //Carregar a recycler view
        recyclerView = findViewById(R.id.rvUtilizadores);
        ArrayList<Utilizador> listUtilizadores = utilizadoresDAO.getUtilizadores();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        UsersAdapter usersAdapter = new UsersAdapter(listUtilizadores);
        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

}