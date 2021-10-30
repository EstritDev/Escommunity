package com.example.escommunity;

import androidx.appcompat.app.AppCompatActivity;

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
                if(!emailSplit[1].equals("alunos.sefo.pt")){
                    Toast.makeText(getApplicationContext(), "O email deve pertencer á escola", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        lblJaTemConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}