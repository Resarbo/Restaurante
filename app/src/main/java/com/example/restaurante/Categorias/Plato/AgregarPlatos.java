package com.example.restaurante.Categorias.Plato;

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
import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AgregarPlatos extends AppCompatActivity {
    TextView NombrePlato, CantidadPlato, DescripcionPlato, PrecioPlato;
    Button AgregarPlato;

    int estado, idPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.restaurante.R.layout.activity_agregar_platos);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        NombrePlato = findViewById(R.id.NombrePlato);
        PrecioPlato = findViewById(R.id.PrecioPlato);
        CantidadPlato = findViewById(R.id.CantidadPlato);
        DescripcionPlato = findViewById(R.id.DescripcionPlato);

        AgregarPlato = findViewById(R.id.AgregarPlato);

        Bundle intent = getIntent().getExtras();
        if(intent != null){
            //setear
            NombrePlato.setText(intent.getString("nombre"));
            PrecioPlato.setText(intent.getString("precio"));
            CantidadPlato.setText(intent.getString("cantidad"));
            DescripcionPlato.setText(intent.getString("descripcion"));
            idPlato = Integer.parseInt(intent.getString("idPlato"));

            actionBar.setTitle("Actualizar");
            String actualizar = "Actualizar";
            AgregarPlato.setText(actualizar);
        }

        AgregarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AgregarPlato.getText().equals("AGREGAR")){
                    SubirPlato();
                } else if (AgregarPlato.getText().equals("Actualizar")) {
                    ActualizarPlato();
                }
            }
        });
    }

    private void SubirPlato() {
        Connection connection = Conexion.connectionclass();
        try{
            if(connection!= null){
                String query =  "Insert into Platos values ('"
                        + NombrePlato.getText().toString() + "','"
                        + PrecioPlato.getText().toString() + "','"
                        + CantidadPlato.getText().toString() + "','"
                        + DescripcionPlato.getText().toString() + "','"
                        + estado + "')";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
            }
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }
        startActivity(new Intent(AgregarPlatos.this, PlatosA.class));
        finish();
    }

    public void ActualizarPlato(){
        Connection connection = Conexion.connectionclass();
        try{
            if(connection!= null){
                String query =  "update Platos set " +
                        "precio='"+PrecioPlato.getText().toString()+"', " +
                        "nombre='"+NombrePlato.getText().toString()+"', " +
                        "cantidad='"+CantidadPlato.getText().toString()+"'," +
                        "descripcion='"+DescripcionPlato.getText().toString()+"', " +
                        "estado='"+1+"' where " +
                        "id_plato = '"+idPlato+"'";
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