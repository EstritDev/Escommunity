package com.example.MyUniverse.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.MyUniverse.R;
import com.example.MyUniverse.constructors.Utilizador;
import com.example.MyUniverse.daos.UtilizadoresDAO;


public class MainActivity extends AppCompatActivity {

    public String loginIdG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Esconder a actionbar(barra com o nome da app no topo)
        getSupportActionBar().hide();

        //buscar a tabela utilizadores
        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(this);

        //Não mudar as cores do layout mesmo que o telemovél esteja em darkmode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Buttons
        Button btnLogin, btnCriarConta;

        //Text boxes
        EditText txtUser, txtPass;

        //Check boxes
        CheckBox cboxMemorizar;

        btnLogin = findViewById(R.id.btnLogin);
        btnCriarConta = findViewById(R.id.btnCriarConta);
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        cboxMemorizar = findViewById(R.id.cboxMemorizar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtUser.getText().length() <= 0 || txtPass.getText().length() <= 0){
                    Toast.makeText(getApplicationContext(), "You should fill all the fields.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(utilizadoresDAO.login(txtUser.getText().toString(), txtPass.getText().toString()) == null){
                    Toast.makeText(getApplicationContext(), "Username or password wrong.", Toast.LENGTH_LONG).show();
                    return;
                }
                Utilizador utilizador = utilizadoresDAO.login(txtUser.getText().toString(), txtPass.getText().toString());
                Intent intent = new Intent(MainActivity.this, PaginaInicial.class);
                loginIdG = utilizador.getLoginId();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("loginId", loginIdG);
                startActivity(intent);
            }

        });

        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }
}