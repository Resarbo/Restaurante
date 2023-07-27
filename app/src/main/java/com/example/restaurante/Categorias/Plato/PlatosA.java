package com.example.restaurante.Categorias.Plato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlatosA extends AppCompatActivity {

    private RecyclerView recyclerViewMesero;
    private RecyclerViewAdaptadorPlatos recyclerViewAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Platos");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerViewMesero = (RecyclerView) findViewById(R.id.recyclerViewPlatos);
        recyclerViewMesero.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdaptador = new RecyclerViewAdaptadorPlatos(obtenerPlatos());
        recyclerViewMesero.setAdapter(recyclerViewAdaptador);

        recyclerViewAdaptador.setOnClickListener(new RecyclerViewAdaptadorPlatos.OnClickListener() {
            @Override
            public void onClick(int position, Plato model) {

            }
        });
    }

    private List<Plato> obtenerPlatos() {
        List<Plato> platos = new ArrayList<>();
        try{
            Statement st = Conexion.connectionclass().createStatement();
            ResultSet rs = st.executeQuery("select * from Platos_intent");
            while(rs.next()){
                platos.add(new Plato(rs.getInt("id_plato"),rs.getString("nombre"),rs.getFloat("precio"),
                        rs.getInt("cantidad"),rs.getString("descripcion"),rs.getBytes("imagen")));
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
        return platos;
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
                startActivity(new Intent(PlatosA.this, AgregarPlatos.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}