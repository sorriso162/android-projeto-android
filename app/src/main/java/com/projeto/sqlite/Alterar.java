package com.projeto.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.projeto.sqlite.Controller.BancoController;
import com.projeto.sqlite.Db.CallEntry;

/**
 * Created by Andre on 22/04/2017.
 */
public class Alterar extends Activity {
    EditText livro;
    EditText autor;
    EditText editora;
    Button alterar;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        livro = (EditText)findViewById(R.id.editText4);
        autor = (EditText)findViewById(R.id.editText5);
        editora = (EditText)findViewById(R.id.editText6);


        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        livro.setText(cursor.getString(cursor.getColumnIndexOrThrow(CallEntry.DESCRICAO)));
        autor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CallEntry.TIPO)));
        editora.setText(cursor.getString(cursor.getColumnIndexOrThrow(CallEntry.STATUS)));

    }

    public void updateCall(View view){
        crud.alteraRegistro(Integer.parseInt(codigo), livro.getText().toString(),autor.getText().toString(),
                editora.getText().toString());
        Intent intent = new Intent(Alterar.this,ConsultaChamadoActivity.class);
        startActivity(intent);
        finish();

    }

    public void deleteCall(View view){
        crud.deletaRegistro(Integer.parseInt(codigo));
        Intent intent = new Intent(Alterar.this,ConsultaChamadoActivity.class);
        startActivity(intent);
        finish();
    }

}