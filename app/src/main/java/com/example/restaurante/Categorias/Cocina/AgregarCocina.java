package com.example.restaurante.Categorias.Cocina;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AgregarCocina extends AppCompatActivity {

    TextView DniCocinero, NombreCocinero, ApellidoCocinero, EdadCocinero, DescripcionCocinero;

    Button AgregarCocinero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cocina);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        DniCocinero = findViewById(R.id.DniCocinero);
        NombreCocinero = findViewById(R.id.NombreCocinero);
        ApellidoCocinero = findViewById(R.id.ApellidoCocinero);
        EdadCocinero = findViewById(R.id.EdadCocinero);
        DescripcionCocinero = findViewById(R.id.DescripcionCocinero);

        AgregarCocinero = findViewById(R.id.AgregarCocinero);

        AgregarCocinero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubirCocinero();
            }
        });
    }

    private void SubirCocinero() {
        Connection connection = Conexion.connectionclass();
        try{
            if(connection!= null){
                String query =  "Insert into Cocineros values ('" + DniCocinero.getText().toString() + "','"
                        + NombreCocinero.getText().toString() + "','"
                        + ApellidoCocinero.getText().toString() + "','"
                        + EdadCocinero.getText().toString() + "','"
                        + DescripcionCocinero.getText().toString() + "')";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
            }
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }
        startActivity(new Intent(AgregarCocina.this,CocinaA.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}