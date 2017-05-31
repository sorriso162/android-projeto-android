package com.projeto.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.projeto.sqlite.Model.ChamadoDados;
import com.projeto.sqlite.Network.ConvertGson;
import com.projeto.sqlite.Network.CheckInternet;
import com.projeto.sqlite.Network.MethodRequest;

import java.io.IOException;

public class InsereChamadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void insere(View view) {
        boolean check = new CheckInternet().isConnected(this);
        if(check == true){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // Obtendo valor do EditText
                    EditText editText = (EditText) findViewById(R.id.editText2);
                    String message = editText.getText().toString();

                    // Obtendo valor do Spinner
                    Spinner dropdown = (Spinner)findViewById(R.id.spinner_type);
                    String text = dropdown.getSelectedItem().toString();

                    ChamadoDados cd = new ChamadoDados();
                    cd.setIdUsuario(1);
                    cd.setTipo(text);
                    cd.setDescricao(message);

                    try{
                        if(criarChamado(cd)){
                            InsereChamadoActivity.this.runOnUiThread(new Runnable(){
                                public void run(){
                                    Toast.makeText(InsereChamadoActivity.this, "Chamado Inserido!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }catch (IOException e){
                        InsereChamadoActivity.this.runOnUiThread(new Runnable(){
                            public void run(){
                                Toast.makeText(InsereChamadoActivity.this, "Ocorreu um Erro!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }else{
            Toast.makeText(this, "Você não tem Internet", Toast.LENGTH_SHORT).show();
        }
    }


    public Boolean criarChamado(ChamadoDados cd) throws IOException{
        ConvertGson convert = new ConvertGson();
        String url       = "http://10.0.2.2:8080/SistemaChamado/rest/criar";
        MethodRequest ma = new MethodRequest();
        String resultado = ma.post(url, convert.converteParaJson(cd));

        if(resultado != null){
            return true;
        }

        return false;
    }

}
