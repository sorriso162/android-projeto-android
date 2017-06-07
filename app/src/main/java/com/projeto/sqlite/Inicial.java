package com.projeto.sqlite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.projeto.sqlite.Model.LoginDados;
import com.projeto.sqlite.Model.ManipulaId;

public class Inicial extends AppCompatActivity {
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);


        // Shared Preference
        SharedPreferences preference =
                getSharedPreferences("user_id", MODE_PRIVATE);

        // Insere valor do Shared Preference
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt("user_id", 35);
        editor.commit();

        // Recupera valor do Shared Preference
        int userId = preference.getInt("user_id", 0);
        ManipulaId ld = new ManipulaId();
        Log.i("sfsdf:", String.valueOf(ld.getId()));

    }

    public void InsertDelete(View view){
        Intent it = new Intent(Inicial.this, InsereChamadoActivity.class);
       // it.putExtra("codigo", it.getString("codigo"));
        startActivity(it);
    }

    public void SelectUpdate(View view){
        Intent it = new Intent(Inicial.this, ConsultaChamadoActivity.class);
        startActivity(it);
    }

}
