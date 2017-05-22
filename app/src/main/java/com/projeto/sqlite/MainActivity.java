package com.projeto.sqlite;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.projeto.sqlite.Request.ChamadaRequest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://10.0.2.2:8080/api/chamada/12";

        ChamadaRequest request = new ChamadaRequest();

        try {
            request.getRequest(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
