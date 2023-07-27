package com.example.restaurante.PDFDrive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.File;
import java.util.Collections;

import javax.annotation.Nullable;

public class RequestSignIn extends AppCompatActivity {

        PDFUploader pdfUploader;
        public void requestSignIn () {
            GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestScopes(new Scope(DriveScopes.DRIVE_FILE))
                    .build();

            GoogleSignInClient client = GoogleSignIn.getClient(this, signInOptions);

            startActivityForResult(client.getSignInIntent(), 400);
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            switch (requestCode) {
                case 400:
                    if (resultCode == RESULT_OK) {
                        handleSignInIntent(data);
                    }
                    break;
            }
        }

        public void handleSignInIntent (Intent data){
            GoogleSignIn.getSignedInAccountFromIntent(data)
                    .addOnSuccessListener(new OnSuccessListener<GoogleSignInAccount>() {
                        @Override
                        public void onSuccess(GoogleSignInAccount googleSignInAccount) {

                            GoogleAccountCredential credential = GoogleAccountCredential
                                    .usingOAuth2(RequestSignIn.this, Collections.singleton(DriveScopes.DRIVE_FILE));

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

        public void uploadPdfFile (View v){
            ProgressDialog progressDialog = new ProgressDialog(RequestSignIn.this);
            progressDialog.setTitle("Subiendo a Google Drive");
            progressDialog.setMessage("Por favor espere....");
            progressDialog.show();

            String rutaDescargas = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            String rutaArchivoPDF = rutaDescargas + File.separator + "Cartilla.pdf";

            String filePath = "/storage/emulated/0/mypdf.pdf";

            PDFUploader.createFilePDF(rutaArchivoPDF).addOnSuccessListener(new OnSuccessListener<String>() {
                @Override
                public void onSuccess(String s) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Se subió correctamente", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    //Toast.makeText(getApplicationContext(), "Verifica la API key de tu google drive", Toast.LENGTH_SHORT).show();
                }
            });
        }

    /*
    public String getLastUploadedFileUrl() {

        DriveApi driveApi = new Drive.Builder(
                new NetHttpTransport(),
                new JacksonFactory(),
                new GoogleCredential.Builder().getTokenServerUrl().build()
        ).build();

        Drive driveClient = new Drive(this);

        Query query = new Query.Builder()
                .build();

        Task<FileList> queryTask = driveClient.listFiles(query);

        return queryTask.continueWith(new Continuation<MetadataBuffer, String>() {
            @Override
            public String then(@NonNull Task<MetadataBuffer> task) throws Exception {
                if (task.isSuccessful()) {
                    MetadataBuffer metadataBuffer = task.getResult();
                    if (metadataBuffer.getItems().size() > 0) {
                        Metadata metadata = metadataBuffer.getItems().get(0);
                        return metadata.getWebContentLink();
                    }
                }
                return null;
            }
        });
}
     */



}
