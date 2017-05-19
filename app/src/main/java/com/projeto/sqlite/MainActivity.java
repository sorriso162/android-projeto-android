package com.projeto.sqlite;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... params) {






                return null;
            }
        }.execute();
    }

    public void InsertDelete(View view){
        Intent it = new Intent(this, InsereDado.class);
        startActivity(it);
    }

    public void SelectUpdate(View view){
        Intent it = new Intent(this, Consulta.class);
        startActivity(it);
    }
}
