package com.example.restaurante.Categorias.Pedidos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class PedidoDetalle extends AppCompatActivity {
    Connection connection = Conexion.connectionclass();
    TextView idPedidotxt, platoPedidotxt, cantidadPedidotxt, precioPedidotxt, montoTotaltxt, estadoPedidotxt;
    Button editarPedido, cambiarEstado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_detalle);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Detalle de plato");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        idPedidotxt = findViewById(R.id.idPedidotxt);
        platoPedidotxt = findViewById(R.id.platoPedidotxt);
        cantidadPedidotxt = findViewById(R.id.cantidadPedidotxt);
        precioPedidotxt = findViewById(R.id.precioPedidotxt);
        montoTotaltxt = findViewById(R.id.montoTotaltxt);
        estadoPedidotxt = findViewById(R.id.estadoPedidotxt);

        editarPedido = findViewById(R.id.editarPedido);
        cambiarEstado = findViewById(R.id.cambiarEstado);

        String idPedido = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            idPedido = extras.getString("idPedido");
        }
        idPedidotxt.setText(idPedido);

        llenarDatos();

        editarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PedidoDetalle.this, AgregarPedidos.class);
                intent.putExtra("idPedido",idPedidotxt.getText());
                intent.putExtra("cantidad",cantidadPedidotxt.getText());
                intent.putExtra("nombre",platoPedidotxt.getText());
                intent.putExtra("precio",precioPedidotxt.getText());
                intent.putExtra("monto",montoTotaltxt.getText());
                intent.putExtra("estado",estadoPedidotxt.getText());
                startActivity(intent);
            }
        });

        cambiarEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarEstado();
            }
        });
    }

    private void actualizarEstado() {
        Connection connection = Conexion.connectionclass();
        try{
            if(connection!= null){
                String query =  "update Pedido set " +
                        "estado ='"+1+"' where " +
                        "id_pedido ='"+idPedidotxt.getText().toString()+"'";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
            }
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }
        startActivity(new Intent(PedidoDetalle.this, PedidosA.class));
        finish();
    }

    private void llenarDatos() {
        try{
            Statement st = Conexion.connectionclass().createStatement();
            ResultSet rs = st.executeQuery("select p.id_pedido, pd.precio, p.monto_total,pl.nombre,pd.cantidad,p.estado from Pedido p, Platos pl, Pedido_detalle pd\n" +
                    "where p.id_pedido = pd.id_pedido and pd.id_plato = pl.id_plato and p.id_pedido='"+idPedidotxt.getText()+"'");
            while(rs.next()){
                platoPedidotxt.setText(rs.getString("nombre"));
                cantidadPedidotxt.setText(String.valueOf(rs.getInt("cantidad")));
                precioPedidotxt.setText(String.valueOf(rs.getFloat("precio")));
                montoTotaltxt.setText(String.valueOf(rs.getFloat("monto_total")));
                if(rs.getString("estado").equals("0")){
                    estadoPedidotxt.setText("EN ESPERA");
                } else if (rs.getString("estado").equals("1")) {
                    estadoPedidotxt.setText("ENTREGADO");
                }
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