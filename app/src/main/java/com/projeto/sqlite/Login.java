package com.projeto.sqlite;

import android.app.Activity;
import android.content.Intent;

import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.projeto.sqlite.Model.ChamadoDados;
import com.projeto.sqlite.Model.LoginDados;
import com.projeto.sqlite.Model.ManipulaId;
import com.projeto.sqlite.Network.CheckInternet;
import com.projeto.sqlite.Network.ConvertGson;
import com.projeto.sqlite.Network.MethodRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class Login extends Activity  {
    EditText cpf;
    EditText senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cpf = (EditText)findViewById(R.id.cpf);
        senha = (EditText)findViewById(R.id.password);
    }

    /*
        Acao ao clicar, se houxer conexao ele faz a requisicao
        se nao ele manda uma msg que nao tem internet
     */
    public void VerificaLogin(View view) throws IOException {
        CheckInternet check = new CheckInternet();

        if(check.isConnected(this)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LoginDados ld = new LoginDados();
                    ld.setEmail(cpf.getText().toString());
                    ld.setPassword(senha.getText().toString());

                    try {
                        // se existir ele acessa o sistema
                        if(loginUser(ld)) {

                            Intent it = new Intent(Login.this, Inicial.class);

                            startActivity(it);
                        }else{
                            Login.this.runOnUiThread(new Runnable() {
                                public void run() {
                                Toast.makeText(Login.this, "Login Incorreto!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } catch (IOException e) {
                        // Imprime o erro
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else{
            Toast.makeText(this, "Você não tem Internet", Toast.LENGTH_SHORT).show();
        }
    }

    /*
        Metodo esta recebendo o objeto e transformando em json
     */
    public Boolean loginUser(LoginDados ld) throws IOException, JSONException {
        MethodRequest ma    = new MethodRequest();
        ConvertGson convert = new ConvertGson();
        String url          = "http://10.0.2.2:8080/SistemaChamado/rest/user";
        String resultado = ma.post(url, convert.converteParaJson(ld));
        ld = (LoginDados) convert.paraObjeto(resultado, LoginDados.class);
        ManipulaId mi = new ManipulaId();
        mi.setId(ld.getId());
        Log.i("o id e =",""+mi);

        if(ld != null){
            return true;
        }

        return false;
    }

}