package com.example.unidad5.ui.ficheros;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unidad5.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FicherosFragment extends Fragment {

    private FicherosViewModel ficherosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ficherosViewModel =
                new ViewModelProvider(this).get(FicherosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ficheros, container, false);
        final TextView textView = root.findViewById(R.id.lblBBDD_cabecera);
        ficherosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final EditText textoescrito=(EditText) root.findViewById(R.id.edit1);
        final Button botonguardar=(Button) root.findViewById(R.id.button1);
        final Button botonleer=(Button) root.findViewById(R.id.button2);
        final String FILENAME = "ficherointerno";
        final TextView etiqueta=(TextView) root.findViewById(R.id.textView1);
        textoescrito.setText("");

        //esto de externos
        final Button botonguardarext=(Button) root.findViewById(R.id.button3);
        final Button botonleerext=(Button) root.findViewById(R.id.button4);

        // y este para el de raw
        final Button botonleerraw=(Button) root.findViewById(R.id.button6);
        final Button botonguardarraw=(Button) root.findViewById(R.id.button5);


        botonguardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String cadena = textoescrito.getText().toString();

                FileOutputStream fos = null;
                try {
                    fos = (FileOutputStream) getContext().openFileOutput(FILENAME,  getContext().MODE_APPEND);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    fos.write(cadena.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textoescrito.setText("");
                etiqueta.setText("");
            }//onClick
        });

        botonleer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                InputStreamReader ficherolectura=null;
                String cadena="";
                try {
                    ficherolectura = new InputStreamReader(getContext().openFileInput(FILENAME));
                    BufferedReader br= new BufferedReader(ficherolectura);
                    cadena = br.readLine();
                    while (cadena != null){
                        etiqueta.append(cadena);
                        cadena=br.readLine();
                    }
                } catch (Exception ex) {
                    Log.e("Aplicación ficheros", "Error leyendo de fichero");
                }
                finally{
                    try{
                        if (ficherolectura!=null)
                            ficherolectura.close();
                    }catch (IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }
        });


        //esto de externos, hasta aquí de internos
        botonguardarext.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String estado;
                boolean memok=false;
                estado = Environment.getExternalStorageState();
                if (estado.equals(Environment.MEDIA_MOUNTED)){
                    memok = true;
                }
                TextView etiqueta = (TextView) root.findViewById(R.id.textView1);
                etiqueta.setText(estado.toString()+memok);
                if (memok==true){
                    try{
                        File ruta = new File(getContext().getExternalFilesDir(null),"ficheroexterno.txt");
                        Toast toast1 =Toast.makeText(getContext().getApplicationContext(),ruta.toString(), Toast.LENGTH_SHORT);
                        toast1.show();
                        OutputStreamWriter salida = new OutputStreamWriter(new FileOutputStream(ruta));
                        //salida.append(textoescrito.getText().toString());
                        salida.write(textoescrito.getText().toString());
                        //salida.write("Otra vez con ficheros, esta vez externos");
                        salida.close();
                    }catch (Exception ex){
                        Log.e("Ficherosexternos", "Error al escribir fichero en memoria externa");
                    }
                }
            }
        });

        botonleerext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try{
                    File ruta = new File(getContext().getExternalFilesDir(null),"ficheroexterno.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
                    String cadena = br.readLine();
                    br.close();
                    Toast toast1 =Toast.makeText(getContext().getApplicationContext(),cadena.toString(), Toast.LENGTH_SHORT);
                    etiqueta.setText(cadena.toString());
                    toast1.show();
                }catch (Exception ex){
                    Log.e("Ficherosexternos", "Error al leer fichero en memoria externa");
                }

            }
        });


        //// y a partir de aquí, los ficheros Raw

        botonleerraw.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                BufferedReader br;
                InputStream ficheroraw;
                String todo="";
                try
                {
                    String cadena="";
                    ficheroraw = getResources().openRawResource(R.raw.android);
                    br =new BufferedReader(new InputStreamReader(ficheroraw));
                    while ((cadena = br.readLine()) != null){
                        Log.i("Aplicacion Ficheros raw", cadena);
                        //todo.concat(todo + cadena);
                        etiqueta.setText(cadena.toString());
                    }
                    //etiqueta.setText(todo);
                    ficheroraw.close();
                }
                catch (IOException e){
                    e.printStackTrace();}
            }
        });

        botonguardarraw.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                etiqueta.setText("En ficheros Raw, sólo escritura");

            }
        });

        return root;
    }
}