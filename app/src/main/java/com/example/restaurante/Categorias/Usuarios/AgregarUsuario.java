package com.example.restaurante.Categorias.Usuarios;

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

import com.example.restaurante.Categorias.Empleados.Cocina.AgregarCocina;
import com.example.restaurante.Categorias.Empleados.Cocina.CocinaA;
import com.example.restaurante.Categorias.Plato.AgregarPlatos;
import com.example.restaurante.Categorias.Plato.PlatosA;
import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AgregarUsuario extends AppCompatActivity {

    Connection connection = Conexion.connectionclass();

    TextView CorreoAdmin,PasswordAdmin;
    Button RegistrarAdmin;
    Spinner tipoAdmin;

    int tipoUser, idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        CorreoAdmin = findViewById(R.id.CorreoAdmin);
        PasswordAdmin = findViewById(R.id.PasswordAdmin);
        tipoAdmin = findViewById(R.id.tipoAdmin);

        llenarSpiner();

        RegistrarAdmin = findViewById(R.id.RegistrarAdmin);

        Bundle intent = getIntent().getExtras();
        if(intent != null){
            //setear
            idUser = Integer.parseInt(intent.getString("idUsuario"));
            CorreoAdmin.setText(intent.getString("correo"));
            PasswordAdmin.setText(intent.getString("contrasena"));

            actionBar.setTitle("Actualizar");
            String actualizar = "Actualizar";
            RegistrarAdmin.setText(actualizar);
        }
        RegistrarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (RegistrarAdmin.getText().equals("Registrar")){
                    RegistrarAdmin();
                } else if (RegistrarAdmin.getText().equals("Actualizar")) {
                    ActualizarUsuario();
                }
            }
        });
    }

    public void llenarSpiner(){
        try{
            String query = "select * from Tipos_usuario";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> data = new ArrayList<>();
            while(rs.next()){
                String name = rs.getString("nombre");
                data.add(name);
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter<>(AgregarUsuario.this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, data);
            tipoAdmin.setAdapter(arrayAdapter);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void RegistrarAdmin() {
        try{
            if (tipoAdmin.getSelectedItem().toString().equals("admin")){
                tipoUser = 1;
            } else if (tipoAdmin.getSelectedItem().toString().equals("empleado")) {
                tipoUser = 2;
            }
            if(connection!= null){
                String query =  "Insert into Usuario values ('"
                        + tipoUser + "','"
                        + CorreoAdmin.getText().toString() + "','"
                        + PasswordAdmin.getText().toString() + "')";
                Statement st = connection.createStatement();
                boolean rs = st.execute(query);
                Toast.makeText(AgregarUsuario.this,"ADMIN REGISTRADO EXITOSAMENTE",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(AgregarUsuario.this, UsuarioA.class));
                finish();
            }
        }catch (Exception e){
            Toast.makeText(AgregarUsuario.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
    }

    public void ActualizarUsuario(){
        Connection connection = Conexion.connectionclass();
        try{
            if (tipoAdmin.getSelectedItem().toString().equals("admin")){
                tipoUser = 1;
            } else if (tipoAdmin.getSelectedItem().toString().equals("empleado")) {
                tipoUser = 2;
            }
            if(connection!= null){
                String query =  "update Usuario set " +
                        "correo='"+CorreoAdmin.getText().toString()+"', " +
                        "contrasena='"+PasswordAdmin.getText().toString()+"', " +
                        "id_usuario_tipo='"+tipoUser+"' where " +
                        "id_usuario = '"+idUser+"'";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
            }
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }
        startActivity(new Intent(AgregarUsuario.this, UsuarioA.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}