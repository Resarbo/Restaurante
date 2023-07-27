package com.example.restaurante.Categorias.Usuarios;

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

import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioA extends AppCompatActivity {

    private RecyclerView recyclerViewAdministrador;
    private RecyclerViewAdaptadorUsuarios recyclerViewAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Usuarios");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerViewAdministrador = (RecyclerView) findViewById(R.id.recyclerViewAdministradores);
        recyclerViewAdministrador.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdaptador = new RecyclerViewAdaptadorUsuarios(obtenerAdministradores());
        recyclerViewAdministrador.setAdapter(recyclerViewAdaptador);

        recyclerViewAdaptador.setOnClickListener(new RecyclerViewAdaptadorUsuarios.OnClickListener() {
            @Override
            public void onClick(int position, Usuario model) {

            }
        });
    }

    private List<Usuario> obtenerAdministradores() {
        List<Usuario> administradores = new ArrayList<>();
        try{
            Statement st = Conexion.connectionclass().createStatement();
            ResultSet rs = st.executeQuery("select u.id_usuario,u.id_usuario_tipo,u.correo,u.contrasena,t.nombre from Usuario u, Tipos_usuario t where u.id_usuario_tipo = t.id_usuario_tipo");
            while(rs.next()){
                administradores.add(new Usuario(rs.getInt("id_usuario_tipo"), rs.getInt("id_usuario"), rs.getString("correo"), rs.getString("contrasena"), rs.getString("nombre")));
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
        return administradores;
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
                startActivity(new Intent(UsuarioA.this, AgregarUsuario.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}