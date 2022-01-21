package com.example.escommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.ArrayList;

public class ProcurarActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adaptador;
    RecyclerView.LayoutManager layoutManager;
    UtilizadoresDAO utilizadoresDAO;

    ArrayList<Utilizador> listUtilizadores;
    UsersAdapter usersAdapter = new UsersAdapter(listUtilizadores);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar);

        //Esconder a actionbar(barra com o nome da app no topo)
        getSupportActionBar().hide();

        //Variáveis
        String loginId = getIntent().getStringExtra("loginId");

        //tabela utilizadores
        utilizadoresDAO = new UtilizadoresDAO(this);
        listUtilizadores = new ArrayList<Utilizador>();

        EditText txtProcurar;
        txtProcurar = findViewById(R.id.txtProcurar);


        txtProcurar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(txtProcurar.getText().length() > 0){
                    procurar(txtProcurar.getText().toString());
                }
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
        listUtilizadores = utilizadoresDAO.getUtilizadores();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        UsersAdapter usersAdapter = new UsersAdapter(listUtilizadores);
        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

}