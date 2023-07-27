package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.CoreComponentFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class InicioSesion extends AppCompatActivity {
    Connection connection = Conexion.connectionclass();

    public static int id_empleado, id_usuario, id_tipo_empleado;

    int resultado=0, id_tipo_usuario;
    TextView Correo,Password;

    Button Acceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        Correo = findViewById(R.id.Correo);
        Password = findViewById(R.id.Password);

        Acceder = findViewById(R.id.Acceder);

        Acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(connection!= null){
                        String query =  "select * from Usuario where " +
                                "correo='"+Correo.getText().toString()+"' and " +
                                "contrasena='"+Password.getText().toString()+"'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        if (rs.next()){
                            resultado = 1;
                            if (resultado==1){
                                String queryUsuario = "select id_usuario_tipo, id_usuario from Usuario where correo='"+ Correo.getText().toString() +"'";
                                Statement st1 = connection.createStatement();
                                ResultSet rs1 = st.executeQuery(queryUsuario);
                                rs1.next();
                                id_tipo_usuario = rs1.getInt(1);
                                id_usuario = rs1.getInt(2);
                                if (id_tipo_usuario == 1){
                                    startActivity(new Intent(InicioSesion.this, MainActivityAdministrador.class));
                                    finish();
                                } else if (id_tipo_usuario == 2) {
                                    startActivity(new Intent(InicioSesion.this, MainActivityEmpleado.class));
                                    finish();
                                }
                                Toast.makeText(InicioSesion.this, "BIENVENIDO!", Toast.LENGTH_SHORT).show();

                                String queryEmpleado = "select e.id_empleado,e.id_empleado_tipo from Usuario u, Empleado e where e.id_usuario=u.id_usuario and u.id_usuario='"+id_usuario+"'";
                                Statement st2 = connection.createStatement();
                                ResultSet rs2 = st.executeQuery(queryUsuario);
                                while (rs2.next()){
                                    id_empleado = rs2.getInt("id_empleado");
                                    id_tipo_empleado = rs2.getInt("id_empleado_tipo");
                                }

                                Toast.makeText(InicioSesion.this, id_empleado+""+id_tipo_empleado, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }catch (Exception e){
                    Log.e("error",e.getMessage());
                }
            }
        });
    }
}