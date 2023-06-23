package com.example.restaurante.FragmentosAdmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RegistroPersonal extends Fragment {
    Connection connection = Conexion.connectionclass();

    TextView Nombres,Apellidos,FechaNacimiento,Dni,Descripcion;
    Spinner tipoEmpleado,usuarioEmpleado;
    Button Registrar;

    int tipoEmp, tipoUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro_personal, container, false);

        Nombres = view.findViewById(R.id.Nombres);
        Apellidos = view.findViewById(R.id.Apellidos);
        FechaNacimiento = view.findViewById(R.id.FechaNacimiento);
        Dni = view.findViewById(R.id.Dni);
        Descripcion = view.findViewById(R.id.Descripcion);

        tipoEmpleado = view.findViewById(R.id.tipoEmpleado);
        usuarioEmpleado = view.findViewById(R.id.usuarioEmpleado);

        llenarSpinerEmpleado();
        llenarSpinerUsuario();

        Registrar = view.findViewById(R.id.Registrar);
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarAdmin();
            }
        });
        return view;
    }

    public void llenarSpinerEmpleado(){
        try{
            String query = "select * from Tipos_empleado";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> data = new ArrayList<>();
            while(rs.next()){
                String name = rs.getString("nombre");
                data.add(name);
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(), com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, data);
            tipoEmpleado.setAdapter(arrayAdapter);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void llenarSpinerUsuario(){
        try{
            String query = "select * from Usuario";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> data = new ArrayList<>();
            while(rs.next()){
                String name = rs.getString("correo");
                data.add(name);
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(), com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, data);
            usuarioEmpleado.setAdapter(arrayAdapter);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void RegistrarAdmin() {
        Connection connection = Conexion.connectionclass();
        try{
            String queryEmp = "select id_empleado_tipo from Tipos_empleado where nombre = '"+ tipoEmpleado.getSelectedItem().toString() +"'";
            Statement st1 = connection.createStatement();
            ResultSet rs1 = st1.executeQuery(queryEmp);
            rs1.next();
            tipoEmp = rs1.getInt(1);

            String queryUser = "select id_usuario from Usuario where correo = '"+ usuarioEmpleado.getSelectedItem().toString() +"'";
            Statement st2 = connection.createStatement();
            ResultSet rs2 = st2.executeQuery(queryUser);
            rs2.next();
            tipoUser = rs2.getInt(1);

            if(connection!= null){
                String query =  "Insert into Empleado values ('"
                        + tipoEmp + "','"
                        + tipoUser + "','"
                        + Nombres.getText().toString() + "','"
                        + Apellidos.getText().toString() + "','"
                        + FechaNacimiento.getText().toString() + "','"
                        + Descripcion.getText().toString() + "','"
                        + Dni.getText().toString() + "')";
                Statement st = connection.createStatement();
                boolean rs = st.execute(query);
                Toast.makeText(getActivity(),"PERSONAL REGISTRADO EXITOSAMENTE",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
     }
}