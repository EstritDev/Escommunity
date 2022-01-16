package com.example.escommunity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class UtilizadoresDAO {
    private final SQLiteDatabase db;
    private final DBHelper dbHelper;

    public UtilizadoresDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void criarUtilizador(Context ct, String loginId, String nome, String email, String pass){
        String sql = "select loginId from Utilizadores where loginId='" + loginId +"'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        if(c.getCount() > 0){
            Toast.makeText(ct, "Este utilizador já está registado!", Toast.LENGTH_LONG).show();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("loginId",loginId);
        contentValues.put("nome", nome);
        contentValues.put("email",email);
        contentValues.put("password", pass);
        contentValues.put("description", "Este utilizador ainda não tem descrição definida.");
        db.insert("Utilizadores",null , contentValues);
    }

    public Utilizador login(String userLoginId,String pass){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from Utilizadores where loginId=? and password=?", new String[] {userLoginId,pass});
        if(c.getCount() > 0){
            if(!c.moveToFirst()){
                return null;
            }
            String nome,email,desc;
            nome = c.getString(c.getColumnIndexOrThrow("nome"));
            email = c.getString(c.getColumnIndexOrThrow("email"));
            desc = c.getString(c.getColumnIndexOrThrow("description"));
            return new Utilizador(userLoginId,nome,email,desc);
        }else {
            return null;
        }
    }

    public Utilizador getUserData(String loginId){
        String sql = "select * from Utilizadores where loginId='" + loginId + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        if(c.getCount() > 0){
            if(!c.moveToFirst()){
                return null;
            }
            String nome,email,desc;
            nome = c.getString(c.getColumnIndexOrThrow("nome"));
            email = c.getString(c.getColumnIndexOrThrow("email"));
            desc = c.getString(c.getColumnIndexOrThrow("description"));
            Utilizador utilizador = new Utilizador(loginId,nome,email,desc);
            return utilizador;
        }else {
            return null;
        }
    }

    public void updateDescricao(String loginId, String descricao){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("description", descricao);
        db.update("Utilizadores", cv, "loginId='" + loginId + "'", null);
    }
}
