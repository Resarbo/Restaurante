package com.example.restaurante.FragmentosAdmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RegistrarPedido extends Fragment {

    Connection connection = Conexion.connectionclass();

    TextView PrecioUnitarioPedido, PrecioTotalPedido,CantidadPedido;
    Button RegistrarPedido;
    Spinner tipoPlato;

    int idplato, cantidad=1;
    float precioU, precioT;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_pedido, container, false);

        PrecioUnitarioPedido = view.findViewById(R.id.PrecioUnitarioPedido);
        PrecioTotalPedido = view.findViewById(R.id.PrecioTotalPedido);
        CantidadPedido = view.findViewById(R.id.CantidadPedido);
        tipoPlato = view.findViewById(R.id.tipoPlato);

        llenarSpiner();

        llenarPrecios(tipoPlato.getSelectedItem().toString());

        RegistrarPedido = view.findViewById(R.id.RegistrarPedido);
        RegistrarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarPedido();
            }
        });
        return view;

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
            ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(), com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, data);
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
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
    }

    public void RegistrarPedido() {
        try{
            /*if(connection!= null){
                String query =  "Insert into Usuario values ('"
                        + tipoUser + "','"
                        + CorreoAdmin.getText().toString() + "','"
                        + PasswordAdmin.getText().toString() + "')";
                Statement st = connection.createStatement();
                boolean rs = st.execute(query);
                Toast.makeText(getActivity(),"ADMIN REGISTRADO EXITOSAMENTE",Toast.LENGTH_SHORT).show();
            }*/
        }catch (Exception e){
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
     }
}