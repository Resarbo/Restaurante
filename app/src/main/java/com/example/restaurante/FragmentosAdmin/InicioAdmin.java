package com.example.restaurante.FragmentosAdmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.restaurante.Categorias.Usuarios.UsuarioA;
import com.example.restaurante.Categorias.Empleados.EmpleadosCategorias;
import com.example.restaurante.Categorias.Plato.PlatosA;
import com.example.restaurante.R;

public class InicioAdmin extends Fragment {

    Button Empleados, Platos, Pedidos, Usuarios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inicio_admin, container, false);

        Empleados = view.findViewById(R.id.Empleados);
        Platos = view.findViewById(R.id.Platos);
        Pedidos = view.findViewById(R.id.Pedidos);
        Usuarios = view.findViewById(R.id.Usuarios);

        Empleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EmpleadosCategorias.class));
            }
        });

        Platos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PlatosA.class));
            }
        });

        Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PlatosA.class));
            }
        });

        Usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UsuarioA.class));
            }
        });
        return view;
    }
}