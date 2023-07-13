package com.example.restaurante.FragmentosAdmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    int idplato, cantidad,idempleado=7, idpedido;
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

        tipoPlato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                llenarPrecios(tipoPlato.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        RegistrarPedido = view.findViewById(R.id.RegistrarPedido);
        RegistrarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarPedido();
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

    public void registrarPedido() {
        Connection connection = Conexion.connectionclass();
        try {
            String queryPlato = "select id_plato from Platos where nombre = '" + tipoPlato.getSelectedItem().toString() + "'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(queryPlato);
            rs.next();
            idplato = rs.getInt(1);

            if (connection != null) {
                String queryp = "Insert into Pedido values('"
                        + idempleado + "','"
                        + PrecioTotalPedido.getText().toString() + "')";
                Statement st1 = connection.createStatement();
                boolean rs1 = st1.execute(queryp);
            }

            String queryIdPedido = "select top 1 * from Pedido order by id_pedido desc";
            Statement stp = connection.createStatement();
            ResultSet rsp = stp.executeQuery(queryIdPedido);
            rsp.next();
            idpedido = rsp.getInt(1);

            if (connection != null) {
                String querypd = "Insert into Pedido_detalle values ('"
                        + idpedido + "','"
                        + idplato + "','"
                        + PrecioUnitarioPedido.getText().toString()  + "','"
                        + CantidadPedido.getText().toString() + "')";
                Statement st2 = connection.createStatement();
                boolean rs2 = st2.execute(querypd);
                Toast.makeText(getActivity(),"PEDIDO REGISTRADO EXITOSAMENTE",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
     }
}