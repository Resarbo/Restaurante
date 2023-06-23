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

import com.example.restaurante.Categorias.Usuarios.Usuario_tipo;
import com.example.restaurante.Conexion;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegistroAdmin extends Fragment {

    Connection connection = Conexion.connectionclass();

    TextView CorreoAdmin,PasswordAdmin;
    Button RegistrarAdmin;
    Spinner tipoAdmin;

    int tipoUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_admin, container, false);

        CorreoAdmin = view.findViewById(R.id.CorreoAdmin);
        PasswordAdmin = view.findViewById(R.id.PasswordAdmin);
        tipoAdmin = view.findViewById(R.id.tipoAdmin);

        llenarSpiner();

        RegistrarAdmin = view.findViewById(R.id.RegistrarAdmin);
        RegistrarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarAdmin();
            }
        });
        return view;

    }


    public void llenarSpiner(){
        try{
            String query = "select * from Tipos_usuario";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> data = new ArrayList<>();
            while(rs.next()){
                String name = rs.getString("nombre");
                data.add(name);
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(), com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, data);
            tipoAdmin.setAdapter(arrayAdapter);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void RegistrarAdmin() {
        try{
            if (tipoAdmin.getSelectedItem().toString().equals("admin")){
                tipoUser = 1;
            } else if (tipoAdmin.getSelectedItem().toString().equals("empleado")) {
                tipoUser = 2;
            }
            if(connection!= null){
                String query =  "Insert into Usuario values ('"
                        + tipoUser + "','"
                        + CorreoAdmin.getText().toString() + "','"
                        + PasswordAdmin.getText().toString() + "')";
                Statement st = connection.createStatement();
                boolean rs = st.execute(query);
                Toast.makeText(getActivity(),"ADMIN REGISTRADO EXITOSAMENTE",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
            System.out.println(e);
        }
     }
}