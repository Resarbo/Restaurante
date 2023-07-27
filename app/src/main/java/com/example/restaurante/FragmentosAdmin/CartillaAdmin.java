package com.example.restaurante.FragmentosAdmin;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.restaurante.PDFDrive.PDFGenerator;
import com.example.restaurante.PDFDrive.PDFUploader;
import com.example.restaurante.PDFDrive.RequestSignIn;
import com.example.restaurante.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.util.Collections;

import javax.annotation.Nullable;

public class CartillaAdmin extends Fragment {

    ImageView ivCodigoQR;
    EditText etDatos;
    Button btnGenerar;

    private static RequestSignIn requestSignInInstance;

    PDFUploader pdfUploader;

    public void requestSignIn () {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(DriveScopes.DRIVE_FILE))
                .build();

        GoogleSignInClient client = GoogleSignIn.getClient(requireContext(), signInOptions);

        startActivityForResult(client.getSignInIntent(), 400);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 400:
                if (resultCode == RESULT_OK) {
                    handleSignInIntent(data);
                }
                break;
        }
    }

    public void uploadPdfFile (){
        ProgressDialog progressDialog = new ProgressDialog(requireContext());
        progressDialog.setTitle("Subiendo a Google Drive");
        progressDialog.setMessage("Por favor espere....");
        progressDialog.show();

        String rutaDescargas = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        String rutaArchivoPDF = rutaDescargas + File.separator + "Cartilla.pdf";

        PDFUploader.createFilePDF(rutaArchivoPDF).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                progressDialog.dismiss();
                Toast.makeText(requireContext(), "Se subió correctamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(requireContext(), "Se subió correctamente", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleSignInIntent (Intent data){
        GoogleSignIn.getSignedInAccountFromIntent(data)
                .addOnSuccessListener(new OnSuccessListener<GoogleSignInAccount>() {
                    @Override
                    public void onSuccess(GoogleSignInAccount googleSignInAccount) {

                        GoogleAccountCredential credential = GoogleAccountCredential
                                .usingOAuth2(requireContext(), Collections.singleton(DriveScopes.DRIVE_FILE));

                        credential.setSelectedAccount(googleSignInAccount.getAccount());

                        Drive googleDriveService = new Drive.Builder(
                                AndroidHttp.newCompatibleTransport(),
                                new GsonFactory(),
                                credential)
                                .setApplicationName("My own drive")
                                .build();

                        pdfUploader = new PDFUploader(googleDriveService);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cartilla_admin, container, false);

        ivCodigoQR = view.findViewById(R.id.ivCodigoQR);
        etDatos = view.findViewById(R.id.etDatos);
        btnGenerar = view.findViewById(R.id.btnGenerar);
        requestSignIn();

        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    PDFGenerator.generatePDF(requireContext());
                    uploadPdfFile();

                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(
                            "https://drive.google.com/drive/folders/109f6bOYXlgBm4e9NhqoVymjepZ5W7Fdt?usp=sharing",
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