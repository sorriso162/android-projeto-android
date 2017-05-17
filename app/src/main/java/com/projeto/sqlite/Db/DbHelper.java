package com.projeto.sqlite.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andre on 22/04/2017.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context, Db.NAME_DB, null, Db.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ CallEntry.TABELA+" ("
                + CallEntry.ID + " integer primary key autoincrement,"
                + CallEntry.DESCRICAO + " text,"
                + CallEntry.TIPO + " text,"
                + CallEntry.STATUS + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + CallEntry.TABELA;
        db.execSQL(dropTable);
        onCreate(db);
    }
}