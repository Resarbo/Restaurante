package com.example.restaurante.Categorias.Mesero;

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

public class AgregarMesero extends AppCompatActivity {
    TextView DniMesero, NombreMesero, ApellidoMesero, EdadMesero, DescripcionMesero;
    Button AgregarMesero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.restaurante.R.layout.activity_agregar_mesero);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        DniMesero = findViewById(R.id.DniMesero);
        NombreMesero = findViewById(R.id.NombreMesero);
        ApellidoMesero = findViewById(R.id.ApellidoMesero);
        EdadMesero = findViewById(R.id.EdadMesero);
        DescripcionMesero = findViewById(R.id.DescripcionMesero);

        AgregarMesero = findViewById(R.id.AgregarMesero);

        AgregarMesero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubirMesero();
            }
        });
    }

    private void SubirMesero() {
        Connection connection = Conexion.connectionclass();
        try{
            if(connection!= null){
                String query =  "Insert into Meseros values ('" + DniMesero.getText().toString() + "','"
                        + NombreMesero.getText().toString() + "','"
                        + ApellidoMesero.getText().toString() + "','"
                        + EdadMesero.getText().toString() + "','"
                        + DescripcionMesero.getText().toString() + "')";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
            }
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }
        startActivity(new Intent(AgregarMesero.this, MeseroA.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}