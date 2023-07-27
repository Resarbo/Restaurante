package com.example.restaurante;

import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PDFGenerator extends AppCompatActivity {
    public static void generatePDF() throws FileNotFoundException {
        // Datos de conexión a la base de datos
        Connection connection = Conexion.connectionclass();

        // Consulta SQL para obtener los datos de la base de datos
        String query =  "SELECT nombre, precio, cantidad, descripcion FROM `Platos`";

        try {
            // Crear el documento PDF
            // Obtener la ruta de la carpeta de descargas
            String rutaDescargas = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            String rutaArchivoPDF = rutaDescargas + File.separator + "Cartilla.pdf";

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivoPDF));
            document.open();
            document.setMargins(0, 0, 0, 0);

            // Agregar imagen de fondo
            /*
            Image backgroundImage = Image.getInstance("/drawable/admin.jpg");
            backgroundImage.scaleAbsolute(document.getPageSize());
            document.add(backgroundImage);
             */

            // Conexión a la base de datos
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Agregar los datos al documento PDF
            while (resultSet.next()) {
                String dato1 = resultSet.getString("nombre");
                String dato2 = resultSet.getString("precio");
                String dato3 = resultSet.getString("cantidad");
                String dato4 = resultSet.getString("descripcion");

                // Crear columna izquierda
                Paragraph leftColumn = new Paragraph();
                leftColumn.setIndentationLeft(50);
                leftColumn.setIndentationRight(10);

                // Agregar datos a la columna izquierda
                leftColumn.add(new Phrase("Dato 1: Valor 1\n", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
                leftColumn.add(new Phrase("Dato 2: Valor 2\n", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));


                Paragraph paragraph = new Paragraph("Nombre: " + dato1 + ", Precio: S/. " + dato2+ ", Cantidad: " + dato3+ ", Descripción: " + dato4);
                document.add(paragraph);
            }

            // Cerrar conexiones
            resultSet.close();
            statement.close();
            connection.close();

            // Cerrar el documento PDF
            document.close();

            System.out.println("Archivo PDF generado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
