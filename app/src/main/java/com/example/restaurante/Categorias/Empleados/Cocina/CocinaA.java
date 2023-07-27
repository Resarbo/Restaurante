package com.example.restaurante.Categorias.Empleados.Cocina;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.Categorias.Empleados.Empleado;
import com.example.restaurante.Categorias.Empleados.RecyclerViewAdaptadorEmpleado;
import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CocinaA extends AppCompatActivity {

    private RecyclerView recyclerViewCocinero;
    private RecyclerViewAdaptadorEmpleado recyclerViewAdaptador;


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

        recyclerViewAdaptador = new RecyclerViewAdaptadorEmpleado(obtenerCocineros());
        recyclerViewCocinero.setAdapter(recyclerViewAdaptador);

        recyclerViewAdaptador.setOnClickListener(new RecyclerViewAdaptadorEmpleado.OnClickListener() {
            @Override
            public void onClick(int position, Empleado model) {

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
                startActivity(new Intent(CocinaA.this, AgregarCocina.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Empleado> obtenerCocineros(){
        List<Empleado> cocineros = new ArrayList<>();
        try{
            Statement st = Conexion.connectionclass().createStatement();
            ResultSet rs = st.executeQuery("select e.nombre, e.id_empleado, e.apellido, e.dni, e.descripcion, e.fecha_nacimiento \n" +
                    "from Empleado e, Tipos_empleado t \n" +
                    "where e.id_empleado_tipo = t.id_empleado_tipo and e.id_empleado_tipo='1'");
            while(rs.next()){
                cocineros.add(new Empleado(rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getInt("id_empleado"),
                        rs.getString("descripcion")));
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return cocineros;
    }
}