package com.projeto.sqlite;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... params) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://api.github.com/users/ashokslsk")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String res = response.body().string();
                    Log.v(TAG, "doInBackground() called with: " + "params = [" + res + "]");
                    return res;
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
