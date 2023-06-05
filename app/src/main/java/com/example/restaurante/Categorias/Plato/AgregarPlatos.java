package com.example.restaurante.Categorias.Plato;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restaurante.Categorias.Mesero.AgregarMesero;
import com.example.restaurante.Categorias.Mesero.MeseroA;
import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AgregarPlatos extends AppCompatActivity {
    TextView IdPlato, NombrePlato, CantidadPlato, DescripcionPlato;
    Button AgregarPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.restaurante.R.layout.activity_agregar_platos);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        IdPlato = findViewById(R.id.IdPlato);
        NombrePlato = findViewById(R.id.NombrePlato);
        CantidadPlato = findViewById(R.id.CantidadPlato);
        DescripcionPlato = findViewById(R.id.DescripcionPlato);

        AgregarPlato = findViewById(R.id.AgregarPlato);

        AgregarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubirPlato();
            }
        });
    }

    private void SubirPlato() {
        Connection connection = Conexion.connectionclass();
        try{
            if(connection!= null){
                String query =  "Insert into Platos values ('" + IdPlato.getText().toString() + "','"
                        + NombrePlato.getText().toString() + "','"
                        + CantidadPlato.getText().toString() + "','"
                        + DescripcionPlato.getText().toString() + "')";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
            }
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }
        startActivity(new Intent(AgregarPlatos.this, PlatosA.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}