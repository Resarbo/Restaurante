package com.example.restaurante.FragmentosAdmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurante.Categorias.Cocina.AgregarCocina;
import com.example.restaurante.Categorias.Cocina.CocinaA;
import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegistroAdmin extends Fragment {

    TextView Correo,Password,Nombres,Apellidos,Dni,Descripcion;
    Button Registrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_admin, container, false);

        Dni = view.findViewById(R.id.Dni);
        Correo = view.findViewById(R.id.Correo);
        Password = view.findViewById(R.id.Password);
        Nombres = view.findViewById(R.id.Nombres);
        Apellidos = view.findViewById(R.id.Apellidos);
        Descripcion = view.findViewById(R.id.Descripcion);

        Registrar = view.findViewById(R.id.Registrar);
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarAdmin();
            }
        });
        return view;
    }

    public void RegistrarAdmin() {
        Connection connection = Conexion.connectionclass();
        try{
            if(connection!= null){
                String query =  "Insert into Admins values ('"
                        + Dni.getText().toString() + "','"
                        + Nombres.getText().toString() + "','"
                        + Apellidos.getText().toString() + "','"
                        + Correo.getText().toString() + "','"
                        + Password.getText().toString() + "','"
                        + Descripcion.getText().toString() + "')";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                Toast.makeText(getActivity(),"ADMIN REGISTRADO EXITOSAMENTE",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getActivity(),"ADMIN REGISTRADO EXITOSAMENTE"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
     }
}