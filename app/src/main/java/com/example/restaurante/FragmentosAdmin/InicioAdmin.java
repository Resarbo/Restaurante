package com.example.restaurante.FragmentosAdmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.restaurante.Categorias.Caja.CajaA;
import com.example.restaurante.Categorias.Cocina.CocinaA;
import com.example.restaurante.Categorias.Mesero.MeseroA;
import com.example.restaurante.Categorias.Plato.PlatosA;
import com.example.restaurante.R;

public class InicioAdmin extends Fragment {

    Button Cocina, Caja, Mesas, Platos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inicio_admin, container, false);

        Cocina = view.findViewById(R.id.Cocina);
        Caja = view.findViewById(R.id.Caja);
        Mesas = view.findViewById(R.id.Mesas);
        Platos = view.findViewById(R.id.Platos);

        Cocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CocinaA.class));
            }
        });

        Caja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CajaA.class));
            }
        });

        Mesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MeseroA.class));
            }
        });

        Platos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PlatosA.class));
            }
        });
        return view;
    }
}