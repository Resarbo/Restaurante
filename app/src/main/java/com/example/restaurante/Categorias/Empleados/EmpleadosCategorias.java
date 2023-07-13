package com.example.restaurante.Categorias.Empleados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.restaurante.Categorias.Empleados.Cajeros.CajaA;
import com.example.restaurante.Categorias.Empleados.Cocina.AgregarCocina;
import com.example.restaurante.Categorias.Empleados.Cocina.CocinaA;
import com.example.restaurante.Categorias.Empleados.Meseros.MesasA;
import com.example.restaurante.R;

public class EmpleadosCategorias extends AppCompatActivity {

    Button Cocina, Caja, Mesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados_categorias);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Empleados");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Cocina = findViewById(R.id.Cocina);
        Caja = findViewById(R.id.Caja);
        Mesas = findViewById(R.id.Mesas);

        Cocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmpleadosCategorias.this, CocinaA.class));
            }
        });

        Caja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmpleadosCategorias.this, CajaA.class));
            }
        });

        Mesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmpleadosCategorias.this, MesasA.class));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_agregar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Agregar:
                Toast.makeText(this, "Agregar", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EmpleadosCategorias.this, AgregarEmpleado.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}