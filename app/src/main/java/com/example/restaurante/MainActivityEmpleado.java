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

import com.example.restaurante.FragmentosEmpleado.InicioEmpleado;
import com.example.restaurante.FragmentosEmpleado.PerfilEmpleado;
import com.google.android.material.navigation.NavigationView;

public class MainActivityEmpleado extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open
                ,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //fragmento defecto
        if (savedInstanceState== null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new InicioEmpleado()).commit();
            navigationView.setCheckedItem(R.id.InicioEmpleado);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.InicioEmpleado:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InicioEmpleado()).commit();
                break;
            case R.id.PerfilEmpleado:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PerfilEmpleado()).commit();
                break;
            case R.id.SalirEmpleado:
                startActivity(new Intent(MainActivityEmpleado.this, InicioSesion.class));
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