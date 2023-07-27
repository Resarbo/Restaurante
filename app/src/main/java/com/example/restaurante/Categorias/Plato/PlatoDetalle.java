package com.example.restaurante.Categorias.Plato;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurante.Conexion;
import com.example.restaurante.Loading;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlatoDetalle extends AppCompatActivity {
    Connection connection = Conexion.connectionclass();
    TextView idPlatotxt, nombrePlatotxt, precioPlatotxt, cantidadPlatotxt, descripcionPlatotxt, estadoPlatotxt;

    Button editarPlato,borrarPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato_detalle);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Detalle de plato");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        idPlatotxt = findViewById(R.id.idPlatotxt);
        nombrePlatotxt = findViewById(R.id.nombrePlatotxt);
        precioPlatotxt = findViewById(R.id.precioPlatotxt);
        cantidadPlatotxt = findViewById(R.id.cantidadPlatotxt);
        descripcionPlatotxt = findViewById(R.id.descripcionPlatotxt);
        estadoPlatotxt = findViewById(R.id.estadoPlatotxt);

        editarPlato = findViewById(R.id.editarPlato);
        borrarPlato = findViewById(R.id.borrarPlato);

        String idplato = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            idplato = extras.getString("idPlato");
        }
        idPlatotxt.setText(idplato);

        llenarDatos();

        editarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlatoDetalle.this, AgregarPlatos.class);
                intent.putExtra("idPlato",idPlatotxt.getText());
                intent.putExtra("nombre",nombrePlatotxt.getText());
                intent.putExtra("precio",precioPlatotxt.getText());
                intent.putExtra("cantidad",cantidadPlatotxt.getText());
                intent.putExtra("descripcion",descripcionPlatotxt.getText());
                startActivity(intent);
            }
        });

        borrarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliminarDatos();
            }
        });
    }

    public void llenarDatos(){
        try{
            Statement st = Conexion.connectionclass().createStatement();
            ResultSet rs = st.executeQuery("select * from Platos_intent where id_plato="+idPlatotxt.getText().toString());
            while(rs.next()){
                nombrePlatotxt.setText(rs.getString("nombre"));
                precioPlatotxt.setText(String.valueOf(rs.getFloat("precio")));
                cantidadPlatotxt.setText(String.valueOf(rs.getInt("cantidad")));
                descripcionPlatotxt.setText(rs.getString("descripcion"));
                estadoPlatotxt.setText("Desactivado");
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
    }

    public void EliminarDatos(){
        AlertDialog.Builder builder = new AlertDialog.Builder(PlatoDetalle.this);
        builder.setTitle("Eliminar");
        builder.setMessage("¿Desea eliminar plato?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    String query =  "delete FROM Platos_intent where id_plato = '"+ idPlatotxt.getText().toString() + "'";
                    Statement st = connection.createStatement();
                    st.executeUpdate(query);
                    startActivity(new Intent(PlatoDetalle.this, PlatosA.class));
                    Toast.makeText(PlatoDetalle.this, "se ha eliminado con exito", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception e){
                    Toast.makeText(PlatoDetalle.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(PlatoDetalle.this, "Cancelado", Toast.LENGTH_SHORT).show();
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