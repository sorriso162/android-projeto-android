package com.projeto.sqlite.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.sqlite.Db.CallEntry;
import com.projeto.sqlite.Db.DbHelper;

/**
 * Created by Andre on 22/04/2017.
 */

public class BancoController {
    private SQLiteDatabase db;
    private DbHelper banco;

    public BancoController(Context context){
        banco = new DbHelper(context);
    }

    public String insereDado(String titulo, String autor, String editora){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CallEntry.DESCRICAO, titulo);
        valores.put(CallEntry.TIPO, autor);
        valores.put(CallEntry.STATUS, editora);

        resultado = db.insert(CallEntry.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {CallEntry.ID,CallEntry.DESCRICAO};
        db = banco.getReadableDatabase();
        String status = "'aberto'";
        String where = CallEntry.STATUS + " = " + status;
        cursor = db.query(CallEntry.TABELA, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {CallEntry.ID,CallEntry.DESCRICAO,CallEntry.TIPO,CallEntry.STATUS};
        String where = CallEntry.ID + " = " + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CallEntry.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(int id, String titulo, String autor, String editora){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CallEntry.ID + "=" + id;

        valores = new ContentValues();
        valores.put(CallEntry.DESCRICAO, titulo);
        valores.put(CallEntry.TIPO, autor);
        valores.put(CallEntry.STATUS, editora);

        db.update(CallEntry.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = CallEntry.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CallEntry.TABELA,where,null);
        db.close();
    }
    public void verificaLogin(String usuario,String senha){

    }
}
