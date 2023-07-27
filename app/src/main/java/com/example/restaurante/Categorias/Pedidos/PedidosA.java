package com.example.restaurante.Categorias.Pedidos;

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

import com.example.restaurante.Categorias.Plato.AgregarPlatos;
import com.example.restaurante.Categorias.Plato.Plato;
import com.example.restaurante.Categorias.Plato.PlatosA;
import com.example.restaurante.Categorias.Plato.RecyclerViewAdaptadorPlatos;
import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PedidosA extends AppCompatActivity {

    private RecyclerView recyclerViewPedido;
    private RecyclerViewAdaptadorPedidos recyclerViewAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Pedidos");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerViewPedido = (RecyclerView) findViewById(R.id.recyclerViewPedidos);
        recyclerViewPedido.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdaptador = new RecyclerViewAdaptadorPedidos(obtenerPedidos());
        recyclerViewPedido.setAdapter(recyclerViewAdaptador);
    }

    private List<Pedido> obtenerPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        try{
            Statement st = Conexion.connectionclass().createStatement();
            ResultSet rs = st.executeQuery("select p.id_pedido,pl.nombre,pd.cantidad,p.estado from Pedido p, Platos pl, Pedido_detalle pd\n" +
                    "where p.id_pedido = pd.id_pedido and pd.id_plato = pl.id_plato");
            while(rs.next()){
                pedidos.add(new Pedido(
                        rs.getInt("id_pedido"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad"),
                        rs.getInt("estado")));
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
        return pedidos;
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
                startActivity(new Intent(PedidosA.this, AgregarPedidos.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}