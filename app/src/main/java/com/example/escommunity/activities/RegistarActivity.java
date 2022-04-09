package com.example.escommunity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.escommunity.R;
import com.example.escommunity.constructors.Utilizador;
import com.example.escommunity.daos.UtilizadoresDAO;

public class RegistarActivity extends AppCompatActivity {

    UtilizadoresDAO utilizadoresDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        //Esconder a actionbar(barra com o nome da app no topo)
        getSupportActionBar().hide();

        //tabela utilizadores
        utilizadoresDAO = new UtilizadoresDAO(this);

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
        EditText txtNome;

        lblJaTemConta = findViewById(R.id.lblJaTemConta);
        btnRegistar = findViewById(R.id.btnRegistar);
        txtUtilizador = findViewById(R.id.txtUtilizador);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassReg = findViewById(R.id.txtPassReg);
        txtConfPass = findViewById(R.id.txtConfPass);
        txtNome = findViewById(R.id.txtNome);

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtUtilizador.getText().length() == 0 || txtEmail.getText().length() == 0 || txtPassReg.getText().length() == 0 || txtConfPass.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "You should fill all the fields.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(utilizadoresDAO.getUserData(txtUtilizador.getText().toString()) != null){
                    Toast.makeText(getApplicationContext(), "This user already exists!", Toast.LENGTH_LONG).show();
                    return;
                }
                String email = txtEmail.getText().toString();
                String[] emailSplit = email.split("@");
                if(emailSplit.length <= 1 || emailSplit.length > 2){
                    Toast.makeText(getApplicationContext(), "The email should be valid.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(txtPassReg.getText().length() < 6){
                    Toast.makeText(getApplicationContext(), "The password should have at least 6 digits.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!txtPassReg.getText().toString().equals(txtConfPass.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Passwords doesn't match.", Toast.LENGTH_LONG).show();
                    return;
                }
                String user = txtUtilizador.getText().toString();
                String nome = txtNome.getText().toString();
                if(utilizadoresDAO.criarUtilizador(getApplicationContext(), user,nome,email,txtPassReg.getText().toString())){
                    utilizadoresDAO.criarUtilizador(getApplicationContext(), user, nome, email,txtPassReg.getText().toString());
                    Utilizador utilizador = utilizadoresDAO.login(user, txtPassReg.getText().toString());
                    Intent intent = new Intent(RegistarActivity.this, PaginaInicial.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("loginId", utilizador.getLoginId());
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"This user already exists.", Toast.LENGTH_LONG).show();
                }

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