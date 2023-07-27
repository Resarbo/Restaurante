package com.example.restaurante.Categorias.Usuarios;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurante.Categorias.Plato.AgregarPlatos;
import com.example.restaurante.Categorias.Plato.PlatoDetalle;
import com.example.restaurante.Categorias.Plato.PlatosA;
import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDetalle extends AppCompatActivity {
    Connection connection = Conexion.connectionclass();
    TextView idUsuariotxt, correoUsuariotxt, contrasenaUsuariotxt, tipoUsuariotxt;
    Button editarUsuario, borrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_detalle);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Detalle de usuario");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        idUsuariotxt = findViewById(R.id.idUsuariotxt);
        correoUsuariotxt = findViewById(R.id.correoUsuariotxt);
        contrasenaUsuariotxt = findViewById(R.id.contrasenaUsuariotxt);
        tipoUsuariotxt = findViewById(R.id.tipoUsuariotxt);

        editarUsuario = findViewById(R.id.editarUsuario);
        borrarUsuario = findViewById(R.id.borrarUsuario);

        String idUsuario = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            idUsuario = extras.getString("idUsuario");
        }
        idUsuariotxt.setText(idUsuario);

        llenarDatos();

        editarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsuarioDetalle.this, AgregarUsuario.class);
                intent.putExtra("idUsuario",idUsuariotxt.getText());
                intent.putExtra("correo",correoUsuariotxt.getText());
                intent.putExtra("contrasena",contrasenaUsuariotxt.getText());
                intent.putExtra("tipo",tipoUsuariotxt.getText());
                startActivity(intent);
            }
        });

        borrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliminarDatos();
            }
        });
    }

    public void llenarDatos(){
        try{
            Statement st = Conexion.connectionclass().createStatement();
            ResultSet rs = st.executeQuery("select * from Usuario where id_usuario = '"+idUsuariotxt.getText()+"';");
            while(rs.next()){
                correoUsuariotxt.setText(rs.getString("correo"));
                contrasenaUsuariotxt.setText(rs.getString("contrasena"));
                if(rs.getString("id_usuario_tipo").equals("1")){
                    tipoUsuariotxt.setText("Administrador");
                } else if (rs.getString("id_usuario_tipo").equals("2")) {
                    tipoUsuariotxt.setText("Empleado");
                }
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
    }

    public void EliminarDatos(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UsuarioDetalle.this);
        builder.setTitle("Eliminar");
        builder.setMessage("¿Desea eliminar usuario?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    String query =  "delete Usuario where id_usuario = '"+ idUsuariotxt.getText().toString() + "'";
                    Statement st = connection.createStatement();
                    st.executeUpdate(query);
                    startActivity(new Intent(UsuarioDetalle.this, UsuarioA.class));
                    finish();
                }catch (Exception e){
                    Toast.makeText(UsuarioDetalle.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(UsuarioDetalle.this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}