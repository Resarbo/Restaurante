package com.example.restaurante.FragmentosAdmin;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.restaurante.PDFGenerator;
import com.example.restaurante.R;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class CartillaAdmin extends Fragment {

    ImageView ivCodigoQR;
    EditText etDatos;
    Button btnGenerar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cartilla_admin, container, false);

        ivCodigoQR = view.findViewById(R.id.ivCodigoQR);
        etDatos = view.findViewById(R.id.etDatos);
        btnGenerar = view.findViewById(R.id.btnGenerar);

        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PDFGenerator.generatePDF();
                    //Ver como adapto el codigo para que se suba solo
                    //RequestSignIn.uploadPdfFile();

                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(
                            "https://drive.google.com/file/d/1HKUgpJIrwyKqdhe6Q6XSmzJg0CZ6PPg9/view?usp=sharing",
                            BarcodeFormat.QR_CODE, 750, 750);
                    ivCodigoQR.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}