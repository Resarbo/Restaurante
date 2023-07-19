package com.example.restaurante.Categorias.Plato;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurante.Conexion;
import com.example.restaurante.R;
import com.mysql.jdbc.PreparedStatement;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AgregarPlatos extends AppCompatActivity {
    TextView NombrePlato, CantidadPlato, DescripcionPlato, PrecioPlato;
    Button AgregarPlato;

    ImageView imagenAgregarPlato;

    private Uri imagenSeleccionadaUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.restaurante.R.layout.activity_agregar_platos);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        NombrePlato = findViewById(R.id.NombrePlato);
        PrecioPlato = findViewById(R.id.PrecioPlato);
        CantidadPlato = findViewById(R.id.CantidadPlato);
        DescripcionPlato = findViewById(R.id.DescripcionPlato);

        AgregarPlato = findViewById(R.id.AgregarPlato);

        imagenAgregarPlato = findViewById(R.id.ImagenAgregarPlato);

        AgregarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubirPlato();
            }
        });

        imagenAgregarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObtenerImg();
            }
        });
    }

    private void SubirPlato() {
        int id = 0;
        Connection connection = Conexion.connectionclass();
        try{
            InputStream inputStream = getContentResolver().openInputStream(imagenSeleccionadaUri);
            byte[] imagenBytes = convertInputStreamToByteArray(inputStream);
            inputStream.close();
            if(connection!= null){
                String query = "INSERT INTO Platos (nombre, precio, cantidad, descripcion, imagen_blob) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
                statement.setString(1, NombrePlato.getText().toString());
                statement.setString(2, PrecioPlato.getText().toString());
                statement.setString(3, CantidadPlato.getText().toString());
                statement.setString(4, DescripcionPlato.getText().toString());
                statement.setBytes(5, imagenBytes);
                statement.executeUpdate();
            }
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }
        startActivity(new Intent(AgregarPlatos.this, PlatosA.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void ObtenerImg() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 100);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            imagenSeleccionadaUri = data.getData();

            if (imagenSeleccionadaUri != null) {
                imagenAgregarPlato.setImageURI(imagenSeleccionadaUri);
            }
        }
    }

    private byte[] convertInputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}