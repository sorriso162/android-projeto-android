package com.projeto.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Inicial extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

    }

    public void InsertDelete(View view){
        Intent it = new Intent(Inicial.this, InsereChamadoActivity.class);
        startActivity(it);
    }

    public void SelectUpdate(View view){
        Intent it = new Intent(Inicial.this, Consulta.class);
        startActivity(it);
    }

}
