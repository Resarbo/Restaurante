package com.example.restaurante;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PDFUploader {
    private static final Executor mExecutor = Executors.newSingleThreadExecutor();
    private static Drive mDriveService;

    public PDFUploader(){
        this.mDriveService = mDriveService;
    }

    public static Task<String> createFilePDF(String filepath){
        return Tasks.call(mExecutor, ()-> {

            File fileMetaData = new File();
            fileMetaData.setName("MyPDFFile");

            java.io.File file = new java.io.File(filepath);

            FileContent mediaContent = new FileContent("application/pdf",file);

            File myFile = null;

            try{
                myFile = mDriveService.files().create(fileMetaData, mediaContent).execute();
            } catch (Exception e){
                e.printStackTrace();
            }

            if (myFile != null){
                throw new IOException("Resultado nulo cuando se solicita el archivo");
            }

            return myFile.getId();
        });
    }
}

