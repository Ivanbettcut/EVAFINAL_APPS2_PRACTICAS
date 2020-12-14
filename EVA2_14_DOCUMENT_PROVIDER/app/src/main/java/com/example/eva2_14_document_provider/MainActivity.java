package com.example.eva2_14_document_provider;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    EditText edtTxtDatos;
    final int ABRIR_ARCHIVO = 100;
    final int GUARDAR_ARCHIVO = 100;

    Uri uriRes = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTxtDatos = findViewById(R.id.edtTextDatos);
    }

    public void abrir(View v){
        Intent inAbrir = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        inAbrir.addCategory(Intent.CATEGORY_OPENABLE);
        inAbrir.setType("text/plain");//MIME type
        inAbrir.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriRes);//opcional
        startActivityForResult(inAbrir, ABRIR_ARCHIVO);

    }

    public void cerrar(View v){
        Intent inGuardar = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        inGuardar.addCategory(Intent.CATEGORY_OPENABLE);
        inGuardar.setType("text/plain");//MIME type
        inGuardar.putExtra(Intent.EXTRA_TITLE, "prueba.txt");
        inGuardar.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriRes);//opcional
        startActivityForResult(inGuardar, ABRIR_ARCHIVO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ABRIR_ARCHIVO:
                if(resultCode == Activity.RESULT_OK){
                    uriRes = data.getData();
                    Log.wtf("URI", data.getData().toString());
                    String sCade;
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uriRes);
                        InputStreamReader isr = new InputStreamReader(inputStream);
                        BufferedReader br = new BufferedReader(isr);
                        while((sCade = br.readLine()) != null){
                            edtTxtDatos.append(sCade);
                            edtTxtDatos.append("\n");
                        }
                        br.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case GUARDAR_ARCHIVO:
                if(resultCode == Activity.RESULT_OK){
                    uriRes = data.getData();
                    Log.wtf("URI", uriRes.toString());
                    try {

                    }catch(FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;

        }
    }
}