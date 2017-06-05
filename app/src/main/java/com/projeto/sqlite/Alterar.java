package com.projeto.sqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.sqlite.Controller.BancoController;
import com.projeto.sqlite.Db.CallEntry;
import com.projeto.sqlite.Model.ChamadoDados;
import com.projeto.sqlite.Network.ConvertGson;
import com.projeto.sqlite.Network.MethodRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Andre on 22/04/2017.
 */
public class Alterar extends Activity {
    String codigo;
    TextView id;
    TextView tipo;
    EditText descricao;
    EditText status;
    TextView nome_usuario;
    TextView nome_solucionador;
    TextView data_inicio;
    TextView data_fim;

    JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");

        try {
            selectById(codigo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectById(final String codigo) throws IOException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://10.0.2.2:8080/SistemaChamado/rest/lista/"+codigo;
                MethodRequest method = new MethodRequest();
                String resultado = null;
                try {

                    resultado = method.get(url);
                    JSONArray obj = new JSONArray(resultado);

                    for (int i = 0; i < obj.length(); i++) {
                        data = obj.getJSONObject(i);
                    }

                    String solucionador = "";
                    if(data.getString("nomeSolucionador") == "null"){
                        solucionador = "Não há Solucionador";
                    }

                    String dataFim = "";
                    if(data.getString("dataFim") == "null"){
                        dataFim = "Não foi fechado ainda!";
                    }
                    
                    // Pegando os Elementos
                    id = (TextView)findViewById(R.id.id);
                    tipo = (TextView) findViewById(R.id.tipo);
                    data_inicio = (TextView) findViewById(R.id.data_inicio);
                    data_fim = (TextView) findViewById(R.id.data_fim);
                    descricao = (EditText) findViewById(R.id.descricao);
                    status = (EditText) findViewById(R.id.status);
                    nome_usuario = (TextView) findViewById(R.id.nome_usuario);
                    nome_solucionador = (TextView) findViewById(R.id.nome_solucionador);

                    // Colocando o valor nos Elementos
                    id.setText(codigo);
                    tipo.setText(data.getString("tipo"));
                    data_inicio.setText(data.getString("dateInicio"));
                    data_fim.setText(dataFim);
                    descricao.setText(data.getString("descricao"));
                    status.setText(data.getString("status"));
                    nome_usuario.setText(data.getString("nomeUsuario"));
                    nome_solucionador.setText(solucionador);


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public void updateCall(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                id = (TextView)findViewById(R.id.id);
                String idText = id.getText().toString();
                status = (EditText) findViewById(R.id.status);
                String statusText = status.getText().toString();
                descricao = (EditText) findViewById(R.id.descricao);
                String descricaoText = descricao.getText().toString();

                ChamadoDados cd = new ChamadoDados();
                cd.setId(Integer.parseInt(idText));
                cd.setDescricao(descricaoText);
                cd.setStatus(statusText);
                //cd.setIdSolucionador(1);

                try{
                    if(atualizarChamado(cd)){
                        Alterar.this.runOnUiThread(new Runnable(){
                            public void run(){
                            Toast.makeText(Alterar.this, "Chamado Alterado!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }catch (IOException e){
                    Alterar.this.runOnUiThread(new Runnable(){
                        public void run(){
                        Toast.makeText(Alterar.this, "Ocorreu um Erro!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                Intent intent = new Intent(Alterar.this,ConsultaChamadoActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }

    public Boolean atualizarChamado(ChamadoDados cd) throws IOException{
        ConvertGson convert = new ConvertGson();
        String url       = "http://10.0.2.2:8080/SistemaChamado/rest/update";
        MethodRequest ma = new MethodRequest();
        String resultado = ma.post(url, convert.converteParaJson(cd));

        if(resultado != null){
            return true;
        }

        return false;
    }
}