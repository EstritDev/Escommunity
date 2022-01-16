package com.example.escommunity;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    Toast.makeText(getApplicationContext(), "Deves preencher todos os campos.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(utilizadoresDAO.login(txtUser.getText().toString(), txtPass.getText().toString()) == null){
                    Toast.makeText(getApplicationContext(), "Utilizador ou palavra-passe errados.", Toast.LENGTH_LONG).show();
                    return;
                }
                Utilizador utilizador = utilizadoresDAO.login(txtUser.getText().toString(), txtPass.getText().toString());
                Intent intent = new Intent(MainActivity.this, PaginaInicial.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("loginId", utilizador.getLoginId());
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