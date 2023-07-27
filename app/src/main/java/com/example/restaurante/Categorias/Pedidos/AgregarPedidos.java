package com.example.restaurante.Categorias.Pedidos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurante.Categorias.Plato.AgregarPlatos;
import com.example.restaurante.Categorias.Plato.PlatosA;
import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AgregarPedidos extends AppCompatActivity {
    Connection connection = Conexion.connectionclass();

    TextView PrecioUnitarioPedido, PrecioTotalPedido,CantidadPedido;
    Button RegistrarPedido;
    Spinner tipoPlato;

    int idplato, cantidad,idempleado=7, idpedido,estado;
    float precioU, precioT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pedidos);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        PrecioUnitarioPedido = findViewById(R.id.PrecioUnitarioPedido);
        PrecioTotalPedido = findViewById(R.id.PrecioTotalPedido);
        CantidadPedido = findViewById(R.id.CantidadPedido);
        tipoPlato = findViewById(R.id.tipoPlato);

        llenarSpiner();

        tipoPlato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                llenarPrecios(tipoPlato.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RegistrarPedido = findViewById(R.id.RegistrarPedido);
        RegistrarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarPedido();
            }
        });
    }

    public void llenarSpiner(){
        try{
            String query = "select * from Platos";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> data = new ArrayList<>();
            while(rs.next()){
                String name = rs.getString("nombre");
                data.add(name);
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter<>(AgregarPedidos.this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, data);
            tipoPlato.setAdapter(arrayAdapter);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void llenarPrecios(String plato){
        if (CantidadPedido.getText().toString().equals("")){
            cantidad = 1;
        }else if(!CantidadPedido.getText().toString().equals("")){
            cantidad = Integer.parseInt(CantidadPedido.getText().toString());
        }
        try{
            String queryEmp = "select precio from Platos where nombre = '"+ plato +"'";
            Statement st1 = connection.createStatement();
            ResultSet rs1 = st1.executeQuery(queryEmp);
            rs1.next();

            precioU = rs1.getInt(1);
            precioT = precioU*cantidad;

            PrecioUnitarioPedido.setText(String.valueOf(precioU));
            PrecioTotalPedido.setText(String.valueOf(precioT));
        }catch (Exception e){
            Toast.makeText(AgregarPedidos.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
    }

    public void registrarPedido() {
        Connection connection = Conexion.connectionclass();
        try {
            String queryPlato = "select id_plato from Platos where nombre = '" + tipoPlato.getSelectedItem().toString() + "'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(queryPlato);
            rs.next();
            idplato = rs.getInt(1);

            if (connection != null) {
                String queryp = "Insert into Pedido values('"
                        + idempleado + "','"
                        + PrecioTotalPedido.getText().toString() + "','"
                        + estado + "')";
                Statement st1 = connection.createStatement();
                boolean rs1 = st1.execute(queryp);
            }

            String queryIdPedido = "select top 1 * from Pedido order by id_pedido desc";
            Statement stp = connection.createStatement();
            ResultSet rsp = stp.executeQuery(queryIdPedido);
            rsp.next();
            idpedido = rsp.getInt(1);

            if (connection != null) {
                String querypd = "Insert into Pedido_detalle values ('"
                        + idpedido + "','"
                        + idplato + "','"
                        + PrecioUnitarioPedido.getText().toString()  + "','"
                        + CantidadPedido.getText().toString() + "')";
                Statement st2 = connection.createStatement();
                boolean rs2 = st2.execute(querypd);
                Toast.makeText(AgregarPedidos.this,"PEDIDO REGISTRADO EXITOSAMENTE",Toast.LENGTH_SHORT).show();
            }

            startActivity(new Intent(AgregarPedidos.this, PedidosA.class));
            finish();
        }catch (Exception e){
            Toast.makeText(AgregarPedidos.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}