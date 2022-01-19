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

        //tabela utilizadores
        utilizadoresDAO = new UtilizadoresDAO(this);

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
                procurarUsers(txtProcurar.getText().toString());
            }
        });
    }

    private void setAdapter(ArrayList<Utilizador> listaUtilizadores){
        UsersAdapter adapter = new UsersAdapter(listaUtilizadores);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void procurarUsers(String args){
        //buscar a tabela users
        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(this);

        //Carregar a recycler view
        recyclerView = findViewById(R.id.rvUtilizadores);
        ArrayList<Utilizador> listaUtilizadores = utilizadoresDAO.procurarUsers(args);
        UsersAdapter adapter = new UsersAdapter(listaUtilizadores);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        setAdapter(listaUtilizadores);
    }
}