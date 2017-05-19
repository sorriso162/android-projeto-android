package com.projeto.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.projeto.sqlite.Controller.BancoController;
import com.projeto.sqlite.Db.LoginDados;

import java.io.IOException;

public class InsereDado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere);

        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"Redes", "Hardware", "Software"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }



    public void insere(View view){
        String status = "aberto";
        BancoController crud = new BancoController(getBaseContext());
        Spinner titulo = (Spinner)findViewById(R.id.spinner1);
        EditText autor = (EditText)findViewById((R.id.editText2));
        String tituloString = titulo.getSelectedItem().toString();
        String autorString = autor.getText().toString();
        String resultado;

        resultado = crud.insereDado(tituloString,autorString,status);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
    }
}
