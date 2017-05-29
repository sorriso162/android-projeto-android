package com.projeto.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.gson.Gson;
import com.projeto.sqlite.Controller.BancoController;
import com.projeto.sqlite.Db.CallEntry;
import com.projeto.sqlite.Model.ChamadoDados;
import com.projeto.sqlite.Model.ConvertGson;
import com.projeto.sqlite.Model.LoginDados;
import com.projeto.sqlite.Request.MethodRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andre on 22/04/2017.
 */
public class Consulta extends Activity {
    ArrayList<ChamadoDados> lista = new ArrayList<>();
    private List<String> tasks = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);


        new Thread(new Runnable() {
            @Override
            public void run() {

                String[] lstEstados = new String[]{"São Paulo", "Rio de Janeiro", "Minas Gerais", "Rio Grande do Sul",
                        "Santa Catarina", "Paraná", "Mato Grosso", "Amazonas"};

                try {
                    Gson convert = new Gson();
                    String url       = "http://10.0.2.2:8080/SistemaChamado/rest/lista";
                    MethodRequest ma = new MethodRequest();
                    String resultado = ma.get(url);

                    JSONArray root = new JSONArray(resultado);
                    JSONObject item = null;

                    for (int i = 0; i < root.length(); i++ ) {
                        item = (JSONObject)root.get(i);
                        int id               = item.getInt("id");
                        String usuario       = item.getString("usuario");
                        String descricao     = item.getString("descricao");
                        String status        = item.getString("status");
                        String tipo          = item.getString("tipo");
                        String solucionador  = item.getString("solucionador");
                        String dataInicio    = item.getString("dateInicio");
                        String dateFim       = item.getString("dataFim");

                        lista.add(new ChamadoDados(id, usuario, descricao, status, tipo,
                                solucionador, dataInicio, dateFim));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }






                ListView teste = (ListView) findViewById(R.id.lvopcoes);

                ArrayAdapter<ChamadoDados> adapter1 = new ArrayAdapter(Consulta.this,
                        R.layout.item_task,
                        R.id.textView,
                        lista);

                teste.setAdapter( adapter1 );


            }
        }).start();




    }


}