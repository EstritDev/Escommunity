package com.example.escommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        //Não mudar as cores do layout mesmo que o telemovél esteja em darkmode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //labels
        TextView lblJaTemConta;

        //Buttons
        Button btnRegistar;

        //Text boxes
        EditText txtUtilizador;
        EditText txtEmail;
        EditText txtPassReg;
        EditText txtConfPass;

        lblJaTemConta = findViewById(R.id.lblJaTemConta);
        btnRegistar = findViewById(R.id.btnRegistar);
        txtUtilizador = findViewById(R.id.txtUtilizador);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassReg = findViewById(R.id.txtPassReg);
        txtConfPass = findViewById(R.id.txtConfPass);

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtUtilizador.getText().length() == 0 || txtEmail.getText().length() == 0 || txtPassReg.getText().length() == 0 || txtConfPass.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Deves preencher todos os campos.", Toast.LENGTH_LONG).show();
                    return;
                }
                String email = txtEmail.getText().toString();
                String[] emailSplit = email.split("@");
                email = emailSplit[1];
                if(!email.equals("alunos.sefo.pt")){
                    Toast.makeText(getApplicationContext(), "O email deve pertencer á escola!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(txtPassReg.getText().length() < 6){
                    Toast.makeText(getApplicationContext(), "A palavra-passe deve conter pelo menos 6 digitos.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!txtPassReg.getText().toString().equals(txtConfPass.getText().toString())){
                    Toast.makeText(getApplicationContext(),"As palavras-passe devem ser iguais.", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(getApplicationContext(), "Utilizador registado com sucesso!", Toast.LENGTH_LONG).show();
                String user = txtUtilizador.getText().toString();
                Intent intent = new Intent(RegistarActivity.this, PaginaInicial.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        lblJaTemConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setWindowAnimations(0);
                finish();
            }
        });
    }
}