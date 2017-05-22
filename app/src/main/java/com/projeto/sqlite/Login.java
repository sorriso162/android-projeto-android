package com.projeto.sqlite;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.text.TextUtils;
import android.text.style.TabStopSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.projeto.sqlite.Db.LoginDados;
import com.projeto.sqlite.Network.CheckInternet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;


public class Login extends Activity  {
    AutoCompleteTextView cpf;
    EditText senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cpf = (AutoCompleteTextView)findViewById(R.id.email);
        senha = (EditText)findViewById(R.id.password);
    }

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
                    }
                }
            }).start();
        }else{
            Toast.makeText(this, "Você não tem Internet", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean loginUser(LoginDados ld) throws IOException {
        String url       = "http://10.0.2.2:8080/SistemaChamado/rest/chamados/user";
        PostMethod ma    = new PostMethod();
        String resultado = ma.post(url, converteParaJson(ld));
        Gson gson = new Gson();
        // transforma json em objeto
        ld = gson.fromJson(resultado, LoginDados.class);

        if(ld != null){
            return true;
        }

        return false;
    }

    public String converteParaJson(LoginDados ld) {
        Gson gson = new Gson();
        String json = gson.toJson(ld);
        return json;
    }
}