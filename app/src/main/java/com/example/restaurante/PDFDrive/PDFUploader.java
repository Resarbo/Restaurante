package com.example.restaurante.PDFDrive;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PDFUploader {
    public static String lastFileId;

    public static String fileId;

    private static final Executor mExecutor = Executors.newSingleThreadExecutor();
    private static Drive mDriveService;

    public PDFUploader(Drive mDriveService){
        this.mDriveService = mDriveService;
    }

    public static Task<String> createFilePDF(String filepath){
        return Tasks.call(mExecutor, ()-> {

            File fileMetaData = new File();
            fileMetaData.setName("Cartilla");

            // Aquí se establece el ID de la carpeta específica "Cartillas"
            fileMetaData.setParents(Collections.singletonList("109f6bOYXlgBm4e9NhqoVymjepZ5W7Fdt"));

            java.io.File file = new java.io.File(filepath);
            FileContent mediaContent = new FileContent("application/pdf",file);

            File myFile = null;

            try{
                myFile = mDriveService.files().create(fileMetaData,mediaContent).execute();
            } catch (Exception e){
                e.printStackTrace();
            }

            if (myFile != null){
                throw new IOException("Resultado nulo cuando se solicita el archivo");
            }

            fileId = myFile.getId();

            return fileId;
        });

    }
}

