package com.example.restaurante.Categorias.Caja;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restaurante.Categorias.Cocina.AgregarCocina;
import com.example.restaurante.Categorias.Cocina.CocinaA;
import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AgregarCajero extends AppCompatActivity {

    TextView DniCajero, NombreCajero, ApellidoCajero, EdadCajero, DescripcionCajero;

    Button AgregarCajero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.restaurante.R.layout.activity_agregar_cajero);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        DniCajero = findViewById(R.id.DniCajero);
        NombreCajero = findViewById(R.id.NombreCajero);
        ApellidoCajero = findViewById(R.id.ApellidoCajero);
        EdadCajero = findViewById(R.id.EdadCajero);
        DescripcionCajero = findViewById(R.id.DescripcionCajero);

        AgregarCajero = findViewById(R.id.AgregarCajero);

        AgregarCajero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubirCajero();
            }
        });
    }

    private void SubirCajero() {
        Connection connection = Conexion.connectionclass();
        try{
            if(connection!= null){
                String query =  "Insert into Cajeros values ('" + DniCajero.getText().toString() + "','"
                        + NombreCajero.getText().toString() + "','"
                        + ApellidoCajero.getText().toString() + "','"
                        + EdadCajero.getText().toString() + "','"
                        + DescripcionCajero.getText().toString() + "')";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
            }
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }
        startActivity(new Intent(AgregarCajero.this, CajaA.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}