package com.example.escommunity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class UtilizadoresDAO {
    private SQLiteDatabase db;
    private DBHelper.DbHelper dbHelper;

    private List<Utilizador> utilizador;
    private Utilizador user;
    public UtilizadoresDAO(Context context) {
        dbHelper = new DBHelper.DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
}
