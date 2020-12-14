package com.example.eva2_11_resource_files;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView txtVwArchivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwArchivo = findViewById(R.id.txtVwArchivo);
    }


    @Override
    protected void onStart() {
        super.onStart();
        //abrir el archivo  InputStream
        //leer en forma de bytes  InputStreamReader
        //interpretarlo (convertirlo a caracteres) BufferedReader

        InputStream inputStream = getResources().openRawResource(R.raw.lorem_ipsum);
        InputStreamReader isReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(isReader);
        String sCade;
        try {
            while((sCade = bufferedReader.readLine()) != null){
                txtVwArchivo.append((sCade));
                txtVwArchivo.append(("\n"));
            }
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }



    }
}