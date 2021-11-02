package com.example.escommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Não mudar as cores do layout mesmo que o telemovél esteja em darkmode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        String user = getIntent().getStringExtra("user");

        //labels
        TextView lblNome = findViewById(R.id.lblNome);


        lblNome.setText(user);
    }
}