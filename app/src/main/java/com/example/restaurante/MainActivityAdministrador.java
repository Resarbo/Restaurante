package com.example.restaurante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.restaurante.Categorias.Plato.AgregarPlatos;
import com.example.restaurante.Categorias.Plato.PlatosA;
import com.example.restaurante.FragmentosAdmin.CartillaAdmin;
import com.example.restaurante.FragmentosAdmin.InicioAdmin;
import com.example.restaurante.FragmentosAdmin.PerfilAdmin;
import com.google.android.material.navigation.NavigationView;

public class MainActivityAdministrador extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_administrador);


        Toolbar toolbar = findViewById(R.id.toolbarA);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_A);

        NavigationView navigationView = findViewById(R.id.nav_viewA);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open
                ,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //fragmento defecto
        if (savedInstanceState== null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                    new InicioAdmin()).commit();
            navigationView.setCheckedItem(R.id.InicioAdmin);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.InicioAdmin:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                        new InicioAdmin()).commit();
                break;
            case R.id.PerfilAdmin:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                        new PerfilAdmin()).commit();
                break;
            case R.id.ListarAdmin:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                        new CartillaAdmin()).commit();
                break;
            case R.id.Salir:
                startActivity(new Intent(MainActivityAdministrador.this, InicioSesion.class));
                finish();
                CerrarSesion();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void CerrarSesion(){
        Toast.makeText(this,"Cerraste sesion exitosamente",Toast.LENGTH_SHORT).show();
    }
}