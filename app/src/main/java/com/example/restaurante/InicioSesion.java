package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InicioSesion extends AppCompatActivity {

    TextView Correo,Password;

    Button Acceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        Correo = findViewById(R.id.Correo);
        Password = findViewById(R.id.Password);

        Acceder = findViewById(R.id.Acceder);

        Acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Correo.getText().toString().trim().equals("admin") && Password.getText().toString().equals("123456")){
                    startActivity(new Intent(InicioSesion.this, MainActivityAdministrador.class));
                }
            }
        });
    }
}