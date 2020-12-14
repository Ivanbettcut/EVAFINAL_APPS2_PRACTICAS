package com.example.eva2_12_archivos_espacio_interno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText edtTxtDatos;
    final String ARCHIVO = "mi_archivo.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTxtDatos = findViewById(R.id.edtTextDatos);
    }


    public void guardar (View v){
        String[] asCade = edtTxtDatos.getText().toString().split("\n");
        try {
            OutputStream outputStream = openFileOutput(ARCHIVO, 0);
            OutputStreamWriter osWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(osWriter);
            for (int i = 0 ; i < asCade.length; i++){
                bufferedWriter.append(asCade[i]);
                bufferedWriter.append("\n");
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leer(View v){

        try {
            InputStream inputStream = openFileInput(ARCHIVO);
            InputStreamReader isReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(isReader);
            String sCade;

                while((sCade = bufferedReader.readLine()) != null){
                    edtTxtDatos.append((sCade));
                    edtTxtDatos.append(("\n"));
                }
                bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}