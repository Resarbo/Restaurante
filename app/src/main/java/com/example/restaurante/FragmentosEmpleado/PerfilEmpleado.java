package com.example.restaurante.FragmentosEmpleado;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.restaurante.Conexion;
import com.example.restaurante.InicioSesion;
import com.example.restaurante.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PerfilEmpleado extends Fragment {

    Connection connection = Conexion.connectionclass();
    TextView DNIPERFIL,NOMBRESPERFIL,APELLIDOSPERFIL,CORREOPERFIL,PASSWORDPERFIL,FECHANACIMINETOPERFIL,AREAPERFIL,DESCRIPCIONPERFIL;

    int id_usuario= InicioSesion.id_usuario, id_empleado_tipo=InicioSesion.id_tipo_empleado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_empleado, container, false);

        DNIPERFIL = view.findViewById(R.id.DNIPERFIL);
        NOMBRESPERFIL = view.findViewById(R.id.NOMBRESPERFIL);
        APELLIDOSPERFIL = view.findViewById(R.id.APELLIDOSPERFIL);
        CORREOPERFIL = view.findViewById(R.id.CORREOPERFIL);
        PASSWORDPERFIL = view.findViewById(R.id.PASSWORDPERFIL);
        FECHANACIMINETOPERFIL = view.findViewById(R.id.FECHANACIMINETOPERFIL);
        AREAPERFIL = view.findViewById(R.id.AREAPERFIL);
        DESCRIPCIONPERFIL = view.findViewById(R.id.DESCRIPCIONPERFIL);

        llenarDatos();
        return view;
    }

    public void llenarDatos(){
        try{
            if(connection!= null){
                String query =  "select E.dni, E.nombre, E.apellido, U.correo, U.contrasena, E.fecha_nacimiento, TE.nombre, E.descripcion \n" +
                        "from Usuario U, Tipos_empleado TE, Empleado E\n" +
                        "where E.id_usuario = U.id_usuario AND E.id_empleado_tipo = TE.id_empleado_tipo AND U.id_usuario='"+id_usuario+"' AND E.id_empleado_tipo='"+id_empleado_tipo+"'; ";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next()){
                    DNIPERFIL.setText(rs.getString(1));
                    NOMBRESPERFIL.setText(rs.getString(2));
                    APELLIDOSPERFIL.setText(rs.getString(3));
                    CORREOPERFIL.setText(rs.getString(4));
                    PASSWORDPERFIL.setText(rs.getString(5));
                    FECHANACIMINETOPERFIL.setText(rs.getString(6));
                    AREAPERFIL.setText(rs.getString(7));
                    DESCRIPCIONPERFIL.setText(rs.getString(8));
                }
            }
        }catch (Exception e){
            System.out.println(e);
            Log.e("error",e.getMessage());
        }
    }

    public void actualizarDatos(){

    }
}