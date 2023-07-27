package com.example.restaurante.PDFDrive;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurante.Conexion;
import com.example.restaurante.R;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PDFGenerator extends AppCompatActivity {
    public static void generatePDF(Context context) {
        // Datos de conexión a la base de datos
        Connection connection = Conexion.connectionclass();

        // Consulta SQL para obtener los datos de la base de datos
        String query =  "SELECT nombre, precio, cantidad, descripcion FROM Platos";

        try {
            // Crear el documento PDF
            // Obtener la ruta de la carpeta de descargas
            String rutaDescargas = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            String rutaArchivoPDF = rutaDescargas + File.separator + "Cartilla.pdf";

            Document document = new Document(PageSize.A4, 0, 0, 0, 0);
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivoPDF));
            document.open();


            // Agregar imagen de fondo
            Drawable drawable = context.getResources().getDrawable(R.drawable.backgroundrc);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Image image = Image.getInstance(byteArray);

            image.setAlignment(Element.ALIGN_CENTER);
            image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            image.setAbsolutePosition(0, 0);
            document.add(image);

            Paragraph emptySpace = new Paragraph();
            // Agregar espacio entre la imagen de fondo y la tabla
            emptySpace.setSpacingBefore(250f);
            document.add(emptySpace);

            // Conexión a la base de datos
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Agregar los datos al documento PDF
            while (resultSet.next()) {
                String dato1 = resultSet.getString("nombre");
                String dato2 = resultSet.getString("precio");
                String dato3 = resultSet.getString("descripcion");



                // Crear tabla para mostrar los datos
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(70);
                table.setSpacingBefore(5f);
                table.setSpacingAfter(5f); // Margen inferior de la tabla

                // Crear celdas y agregarlas a la tabla
                PdfPCell cell1 = new PdfPCell(new Paragraph("Nombre: \n" + dato1, new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.WHITE)));
                cell1.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell1);

                PdfPCell cell2 = new PdfPCell(new Paragraph("Precio: S/. " + dato2, new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.WHITE)));
                cell2.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell2);

                PdfPCell cell3 = new PdfPCell(new Paragraph("Descripción: \n" + dato3, new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.WHITE)));
                cell3.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell3);

                // Agregar la tabla al documento
                document.add(table);

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
