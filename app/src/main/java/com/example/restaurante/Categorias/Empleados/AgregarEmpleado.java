package com.example.restaurante.Categorias.Empleados;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    int tipoEmp, tipoUser;

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

        llenarSpinerEmpleado();
        llenarSpinerUsuario();

        Registrar = findViewById(R.id.Registrar);
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarEmpleado();
            }
        });
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
                String query =  "Insert into Empleado values ('"
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