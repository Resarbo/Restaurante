package com.example.restaurante.Categorias.Empleados.Meseros;

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

import com.example.restaurante.Categorias.Empleados.Cajeros.AgregarCajero;
import com.example.restaurante.Categorias.Empleados.Cajeros.CajaA;
import com.example.restaurante.Categorias.Empleados.Empleado;
import com.example.restaurante.Categorias.Empleados.RecyclerViewAdaptadorEmpleado;
import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MesasA extends AppCompatActivity {

    private RecyclerView recyclerViewMesas;
    private RecyclerViewAdaptadorEmpleado recyclerViewAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Meseros");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerViewMesas = (RecyclerView) findViewById(R.id.recyclerViewMesas);
        recyclerViewMesas.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdaptador = new RecyclerViewAdaptadorEmpleado(obtenerMeseros());
        recyclerViewMesas.setAdapter(recyclerViewAdaptador);
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
                startActivity(new Intent(MesasA.this, AgregarMesero.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Empleado> obtenerMeseros(){
        List<Empleado> cocineros = new ArrayList<>();
        try{
            Statement st = Conexion.connectionclass().createStatement();
            ResultSet rs = st.executeQuery("select e.nombre, e.apellido, e.dni, e.descripcion, e.fecha_nacimiento \n" +
                    "from Empleado e, Tipos_empleado t \n" +
                    "where e.id_empleado_tipo = t.id_empleado_tipo and e.id_empleado_tipo='3'");
            while(rs.next()){
                cocineros.add(new Empleado(rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("descripcion")));
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return cocineros;
    }
}