package com.example.restaurante;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    @SuppressLint("NewApi")
    public static Connection connectionclass(){
        Connection con = null;
        String ip = "192.168.1.6", port="1433", username="Cesar",password="123456",databasename="RestauranteUTP";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename="
                    + databasename + ";User=" + username + ";password=" + password + ";";
            con = DriverManager.getConnection(connectionUrl);
        }catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }
        return con;
    }
}
