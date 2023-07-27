package com.example.restaurante.Categorias.Plato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.Connection;

public class PlatoDetalle extends AppCompatActivity {
    Connection connection = Conexion.connectionclass();
    TextView idPlatotxt, nombrePlatotxt, precioPlatotxt, cantidadPlatotxt, descripcionPlatotxt, estadoPlatotxt;

    Button editarPlato,borrarPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato_detalle);

        idPlatotxt = findViewById(R.id.idPlatotxt);
        nombrePlatotxt = findViewById(R.id.nombrePlatotxt);
        precioPlatotxt = findViewById(R.id.precioPlatotxt);
        cantidadPlatotxt = findViewById(R.id.cantidadPlatotxt);
        descripcionPlatotxt = findViewById(R.id.descripcionPlatotxt);
        estadoPlatotxt = findViewById(R.id.estadoPlatotxt);

        editarPlato = findViewById(R.id.editarPlato);
        borrarPlato = findViewById(R.id.borrarPlato);
    }

    public void llenarDatos(){

    }
}