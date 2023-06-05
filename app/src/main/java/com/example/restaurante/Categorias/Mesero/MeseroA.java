package com.example.restaurante.Categorias.Mesero;

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

public class MeseroA extends AppCompatActivity {

    private RecyclerView recyclerViewMesero;
    private RecyclerViewAdaptadorMesero recyclerViewAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.restaurante.R.layout.activity_mesas);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Mesas");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerViewMesero = (RecyclerView) findViewById(R.id.recyclerViewMesero);
        recyclerViewMesero.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdaptador = new RecyclerViewAdaptadorMesero(obtenerMeseros());
        recyclerViewMesero.setAdapter(recyclerViewAdaptador);
    }

    private List<Mesero> obtenerMeseros() {
        List<Mesero> meseros = new ArrayList<>();
        try{
            Statement st = Conexion.connectionclass().createStatement();
            ResultSet rs = st.executeQuery("select * from Meseros");
            while(rs.next()){
                meseros.add(new Mesero(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido")
                        ,rs.getString("edad"),rs.getString("descripcion")));
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return meseros;
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
                startActivity(new Intent(MeseroA.this, AgregarMesero.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}