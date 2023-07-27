package com.example.restaurante;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion2 {
    @SuppressLint("NewApi")
    public static Connection connectionclass(){
        Connection con = null;
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://db4free.net:3306/dbpruebares12345?characterEncoding=latin1&useSSL=false";
            con = DriverManager.getConnection(connectionUrl, "userpruebar12345", "Prueba1234");
            System.out.println(con);
        }catch (Exception e){
            Log.e("ERROR",e.getMessage());
            System.out.println("Errorsdsad"+e);
        }
        return con;
    }

}
