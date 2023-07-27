package com.example.restaurante.FragmentosEmpleado;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.restaurante.Categorias.Empleados.EmpleadosCategorias;
import com.example.restaurante.Categorias.Pedidos.PedidosA;
import com.example.restaurante.Categorias.Plato.PlatosA;
import com.example.restaurante.R;

public class InicioEmpleado extends Fragment {

    Button Platos, Pedidos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio_empleado, container, false);

        Platos = view.findViewById(R.id.Platos);
        Pedidos = view.findViewById(R.id.Pedidos);

        Platos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PlatosA.class));
            }
        });

        Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PedidosA.class));
            }
        });
        return view;
    }
}