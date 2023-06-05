package com.example.restaurante.Categorias.Cocina;

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

public class CocinaA extends AppCompatActivity {

    private RecyclerView recyclerViewCocinero;
    private RecyclerViewAdaptadorCocina recyclerViewAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocina);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Cocina");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerViewCocinero = (RecyclerView) findViewById(R.id.recyclerViewCocina);
        recyclerViewCocinero.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdaptador = new RecyclerViewAdaptadorCocina(obtenerCocineros());
        recyclerViewCocinero.setAdapter(recyclerViewAdaptador);
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
                startActivity(new Intent(CocinaA.this,AgregarCocina.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Cocinero> obtenerCocineros(){
        List<Cocinero> cocineros = new ArrayList<>();
        try{
            Statement st = Conexion.connectionclass().createStatement();
            ResultSet rs = st.executeQuery("select * from Cocineros");
            while(rs.next()){
                cocineros.add(new Cocinero(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido")
                ,rs.getString("edad"),rs.getString("descripcion")));
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return cocineros;
    }
}