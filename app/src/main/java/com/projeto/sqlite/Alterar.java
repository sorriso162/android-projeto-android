package com.projeto.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.sqlite.Controller.BancoController;
import com.projeto.sqlite.Db.CallEntry;
import com.projeto.sqlite.Model.ChamadoDados;
import com.projeto.sqlite.Network.CheckInternet;
import com.projeto.sqlite.Network.ConvertGson;
import com.projeto.sqlite.Network.MethodRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Andre on 22/04/2017.
 */
public class Alterar extends Activity {

    String codigo = this.getIntent().getStringExtra("codigo");
    EditText id = (EditText)findViewById(R.id.editText4);
    EditText descricao;
    EditText tipo;

    CheckInternet check = new CheckInternet();
    private ArrayList<ChamadoDados> chamados = new ArrayList<ChamadoDados>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        if(check.isConnected(this)){
            try {
                id.setText(codigo);
                selectById();

                Intent intent = new Intent(Alterar.this, ConsultaChamadoActivity.class);
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "Você não tem Internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateCall(View view){


        if(check.isConnected(this)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ChamadoDados cd = new ChamadoDados();
                    descricao = (EditText)findViewById(R.id.editText5);
                    tipo = (EditText)findViewById(R.id.editText6);

                    try {
                        // se existir ele acessa o sistema
                        if(updateById(cd)) {
                            Alterar.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(Alterar.this, "Alterado!", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Intent it = new Intent(Alterar.this, Inicial.class);
                            startActivity(it);
                        }else{
                            Alterar.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(Alterar.this, "Ocorreu Algum Erro!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } catch (IOException e) {
                        // Imprime o erro
                        e.printStackTrace();
                    }
                }
            }).start();
        }else{
            Toast.makeText(this, "Você não tem Internet", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteCall(View view){
    }

    public boolean updateById(ChamadoDados cd) throws IOException{
        MethodRequest ma    = new MethodRequest();
        ConvertGson convert = new ConvertGson();
        String url          = "url para alterar por id, tem que ser post com parametro na url ?id="+codigo;
        String resultado = ma.post(url, convert.converteParaJson(cd));
        cd = (ChamadoDados) convert.paraObjeto(resultado, ChamadoDados.class);

        if(cd != null){
            return true;
        }

        return false;
    }

    public void selectById() throws JSONException, IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String url = "seleciona o chamado pelo id, tem que ser get ?id="+codigo;
                    MethodRequest method = new MethodRequest();
                    String resultado = method.get(url);
                    JSONArray obj = new JSONArray(resultado);
                    for (int i = 0; i < obj.length(); i++) {
                        JSONObject data = obj.getJSONObject(i);
                        chamados.add(new ChamadoDados(data.getString("descricao"), data.getString("tipo"), data.getString("status")));
                    }
                    // Sorriso, colocar os valores aqui dentro
                    descricao.setText();
                    tipo.setText();
                    //status.setText();
                }catch (IOException e){
                    e.getMessage();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}