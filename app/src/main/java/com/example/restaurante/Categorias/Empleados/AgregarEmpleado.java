package com.example.restaurante.Categorias.Empleados;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurante.Categorias.Empleados.Cajeros.CajaA;
import com.example.restaurante.Categorias.Empleados.Cocina.AgregarCocina;
import com.example.restaurante.Categorias.Empleados.Cocina.CocinaA;
import com.example.restaurante.Categorias.Empleados.Meseros.MesasA;
import com.example.restaurante.Categorias.Plato.AgregarPlatos;
import com.example.restaurante.Categorias.Plato.PlatosA;
import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AgregarEmpleado extends AppCompatActivity {

    Connection connection = Conexion.connectionclass();

    TextView Nombres,Apellidos,FechaNacimiento,Dni,Descripcion;
    Spinner tipoEmpleado,usuarioEmpleado;
    Button Registrar;

    int tipoEmp, tipoUser, idEmpleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_empleado);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        Nombres = findViewById(R.id.Nombres);
        Apellidos = findViewById(R.id.Apellidos);
        FechaNacimiento = findViewById(R.id.FechaNacimiento);
        Dni = findViewById(R.id.Dni);
        Descripcion = findViewById(R.id.Descripcion);

        tipoEmpleado = findViewById(R.id.tipoEmpleado);
        usuarioEmpleado = findViewById(R.id.usuarioEmpleado);
        Registrar = findViewById(R.id.Registrar);

        llenarSpinerEmpleado();
        llenarSpinerUsuario();

        Bundle intent = getIntent().getExtras();
        if(intent != null){
            //setear
            Nombres.setText(intent.getString("nombre"));
            Apellidos.setText(intent.getString("apellido"));
            FechaNacimiento.setText(intent.getString("fecha"));
            Dni.setText(intent.getString("dni"));
            Descripcion.setText(intent.getString("descripcion"));
            idEmpleado = Integer.parseInt(intent.getString("idEmpleado"));

            actionBar.setTitle("Actualizar");
            String actualizar = "Actualizar";
            Registrar.setText(actualizar);
        }

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Registrar.getText().equals("REGISTRAR")){
                    RegistrarEmpleado();
                } else if (Registrar.getText().equals("Actualizar")) {
                    ActualizarPlato();
                }
            }
        });
    }

    private void ActualizarPlato() {
        Connection connection = Conexion.connectionclass();
        try{
            String queryEmp = "select id_empleado_tipo from Tipos_empleado where nombre = '"+ tipoEmpleado.getSelectedItem().toString() +"'";
            Statement st1 = connection.createStatement();
            ResultSet rs1 = st1.executeQuery(queryEmp);
            rs1.next();
            tipoEmp = rs1.getInt(1);

            String queryUser = "select id_usuario from Usuario where correo = '"+ usuarioEmpleado.getSelectedItem().toString() +"'";
            Statement st2 = connection.createStatement();
            ResultSet rs2 = st2.executeQuery(queryUser);
            rs2.next();
            tipoUser = rs2.getInt(1);

            if(connection!= null){
                String query =  "update Empleado set " +
                        "id_empleado_tipo='"+tipoEmp+"', " +
                        "id_usuario='"+tipoUser+"'," +
                        "nombre='"+Nombres.getText().toString()+"'," +
                        "apellido='"+Apellidos.getText().toString()+"'," +
                        "fecha_nacimiento='"+FechaNacimiento.getText().toString()+"'," +
                        "descripcion='"+Descripcion.getText().toString()+"'," +
                        "dni='"+Dni.getText().toString()+"' where " +
                        "id_empleado='"+idEmpleado+"'";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
            }
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }
        switch (tipoEmp){
            case 1 :
                startActivity(new Intent(AgregarEmpleado.this, CocinaA.class));
                break;
            case 2 :
                startActivity(new Intent(AgregarEmpleado.this, CajaA.class));
                break;
            case 3 :
                startActivity(new Intent(AgregarEmpleado.this, MesasA.class));
                break;
        }
        finish();
    }

    public void llenarSpinerEmpleado(){
        try{
            String query = "select * from Tipos_empleado";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> data = new ArrayList<>();
            while(rs.next()){
                String name = rs.getString("nombre");
                data.add(name);
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter<>(AgregarEmpleado.this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, data);
            tipoEmpleado.setAdapter(arrayAdapter);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void llenarSpinerUsuario(){
        try{
            String query = "select * from Usuario";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> data = new ArrayList<>();
            while(rs.next()){
                String name = rs.getString("correo");
                data.add(name);
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter<>(AgregarEmpleado.this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, data);
            usuarioEmpleado.setAdapter(arrayAdapter);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void RegistrarEmpleado() {
        Connection connection = Conexion.connectionclass();
        try{
            String queryEmp = "select id_empleado_tipo from Tipos_empleado where nombre = '"+ tipoEmpleado.getSelectedItem().toString() +"'";
            Statement st1 = connection.createStatement();
            ResultSet rs1 = st1.executeQuery(queryEmp);
            rs1.next();
            tipoEmp = rs1.getInt(1);

            String queryUser = "select id_usuario from Usuario where correo = '"+ usuarioEmpleado.getSelectedItem().toString() +"'";
            Statement st2 = connection.createStatement();
            ResultSet rs2 = st2.executeQuery(queryUser);
            rs2.next();
            tipoUser = rs2.getInt(1);

            if(connection!= null){
                String query =  "Insert into Empleado values (NULL,'"
                        + tipoEmp + "','"
                        + tipoUser + "','"
                        + Nombres.getText().toString() + "','"
                        + Apellidos.getText().toString() + "','"
                        + FechaNacimiento.getText().toString() + "','"
                        + Descripcion.getText().toString() + "','"
                        + Dni.getText().toString() + "')";
                Statement st = connection.createStatement();
                boolean rs = st.execute(query);
                Toast.makeText(AgregarEmpleado.this,"EMPLEADO REGISTRADO EXITOSAMENTE",Toast.LENGTH_SHORT).show();

                switch (tipoEmp){
                    case 1 :
                        startActivity(new Intent(AgregarEmpleado.this, CocinaA.class));
                        break;
                    case 2 :
                        startActivity(new Intent(AgregarEmpleado.this, CajaA.class));
                        break;
                    case 3 :
                        startActivity(new Intent(AgregarEmpleado.this, MesasA.class));
                        break;
                }
                finish();
            }
        }catch (Exception e){
            Toast.makeText(AgregarEmpleado.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}