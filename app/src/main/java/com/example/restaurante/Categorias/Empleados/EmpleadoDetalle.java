package com.example.restaurante.Categorias.Empleados;

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

public class EmpleadoDetalle extends AppCompatActivity {
    Connection connection = Conexion.connectionclass();
    TextView idEmpleadotxt, nombresEmpleadotxt, apellidoEmpleadotxt, fechaEmpleadotxt, dniEmpleadotxt, descripcionEmpleadtxt, areaEmpleadotxt, usuarioEmpleadotxt;
    Button editarEmpleado, borrarEmpleado;
    int tipo_empleado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_detalle);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Detalle de empleado");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        idEmpleadotxt = findViewById(R.id.idEmpleadotxt);
        nombresEmpleadotxt = findViewById(R.id.nombresEmpleadotxt);
        apellidoEmpleadotxt = findViewById(R.id.apellidoEmpleadotxt);
        fechaEmpleadotxt = findViewById(R.id.fechaEmpleadotxt);
        dniEmpleadotxt = findViewById(R.id.dniEmpleadotxt);
        descripcionEmpleadtxt = findViewById(R.id.descripcionEmpleadtxt);
        areaEmpleadotxt = findViewById(R.id.areaEmpleadotxt);
        usuarioEmpleadotxt = findViewById(R.id.usuarioEmpleadotxt);

        editarEmpleado = findViewById(R.id.editarEmpleado);
        borrarEmpleado = findViewById(R.id.borrarEmpleado);

        String idEmpleado = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            idEmpleado = extras.getString("idEmpleado");
        }
        idEmpleadotxt.setText(idEmpleado);

        llenarDatos();

        editarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpleadoDetalle.this, AgregarEmpleado.class);
                intent.putExtra("idEmpleado",idEmpleadotxt.getText());
                intent.putExtra("nombre",nombresEmpleadotxt.getText());
                intent.putExtra("apellido",apellidoEmpleadotxt.getText());
                intent.putExtra("fecha",fechaEmpleadotxt.getText());
                intent.putExtra("dni",dniEmpleadotxt.getText());
                intent.putExtra("descripcion",descripcionEmpleadtxt.getText());
                startActivity(intent);
            }
        });

        borrarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliminarDatos();
            }
        });
    }

    private void EliminarDatos() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EmpleadoDetalle.this);
        builder.setTitle("Eliminar");
        builder.setMessage("¿Desea eliminar empleado?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    String query =  "delete Empleado where id_empleado = '"+ idEmpleadotxt.getText().toString() + "'";
                    Statement st = connection.createStatement();
                    st.executeUpdate(query);
                    startActivity(new Intent(EmpleadoDetalle.this, EmpleadosCategorias.class));
                    finish();
                }catch (Exception e){
                    Toast.makeText(EmpleadoDetalle.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(EmpleadoDetalle.this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    private void llenarDatos() {
        try{
            Statement st = Conexion.connectionclass().createStatement();
            ResultSet rs = st.executeQuery("select e.nombre,e.apellido,e.fecha_nacimiento,e.descripcion,e.dni,e.id_empleado_tipo,u.correo from Empleado e, Tipos_empleado te, Usuario u \n" +
                    "where e.id_usuario = u.id_usuario and id_empleado = '"+idEmpleadotxt.getText()+"';");
            while(rs.next()){
                nombresEmpleadotxt.setText(rs.getString("nombre"));
                apellidoEmpleadotxt.setText(rs.getString("apellido"));
                fechaEmpleadotxt.setText(rs.getString("fecha_nacimiento"));
                dniEmpleadotxt.setText(rs.getString("dni"));
                descripcionEmpleadtxt.setText(rs.getString("descripcion"));
                tipo_empleado = rs.getInt("id_empleado_tipo");
                if (tipo_empleado==1){
                    areaEmpleadotxt.setText("Cocina");
                } else if (tipo_empleado==2) {
                    areaEmpleadotxt.setText("Cajero");
                } else if (tipo_empleado==3) {
                    areaEmpleadotxt.setText("Mesero");
                }
                usuarioEmpleadotxt.setText(rs.getString("correo"));
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}