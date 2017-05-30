package com.projeto.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.projeto.sqlite.Model.ChamadoDados;
import com.projeto.sqlite.Request.MethodRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ConsultaChamadoActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter ca;
    private ArrayList<ChamadoDados> people = new ArrayList<ChamadoDados>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_chamado);

        // consulta chamado layout
        listView = (ListView) findViewById(R.id.listview);

        try {
            findAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findAll() throws IOException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://10.0.2.2:8080/SistemaChamado/rest/lista";
                    MethodRequest method = new MethodRequest();
                    String resultado = method.get(url);
                    JSONArray obj = new JSONArray(resultado);

                    for (int i = 0; i < obj.length(); i++) {
                        JSONObject data = obj.getJSONObject(i);
                        people.add(new ChamadoDados(data.getInt("id"), data.getString("descricao")));
                    }

                    ca =  new CustomAdapter(ConsultaChamadoActivity.this, 0, people);
                    listView.setAdapter(ca);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
