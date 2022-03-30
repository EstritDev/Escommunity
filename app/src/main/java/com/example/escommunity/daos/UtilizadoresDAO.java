package com.example.escommunity.daos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.escommunity.DBHelper;
import com.example.escommunity.constructors.Utilizador;

import java.util.ArrayList;

public class UtilizadoresDAO {
    private final SQLiteDatabase db;
    private final DBHelper dbHelper;

    public UtilizadoresDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean criarUtilizador(Context ct, String loginId, String nome, String email, String pass){
        String sql = "select loginId from Utilizadores where loginId='" + loginId +"'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        if(c.getCount() > 0){
            return false;
        }else{
            ContentValues contentValues = new ContentValues();
            contentValues.put("loginId",loginId);
            contentValues.put("nome", nome);
            contentValues.put("email",email);
            contentValues.put("password", pass);
            contentValues.put("description", "Este utilizador ainda não tem descrição definida.");
            db.insert("Utilizadores",null , contentValues);
            return true;
        }
    }

    public Utilizador login(String userLoginId, String pass){
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

    @SuppressLint("Range")
    public ArrayList<Utilizador> getUtilizadores(){
        String sql = "select * from Utilizadores";
        ArrayList<Utilizador> users = new ArrayList<Utilizador>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        String loginId="",nome="", desc="";

       if(c.moveToFirst()){
            do {
                loginId = c.getString(c.getColumnIndex("loginId"));
                nome = c.getString(c.getColumnIndex("nome"));
                desc = c.getString(c.getColumnIndex("description"));
                Utilizador utilizador = new Utilizador(loginId, nome, desc);
                users.add(utilizador);
            } while (c.moveToNext());
        }
        return users;
    }

    public ArrayList<Utilizador> procurarUsers(String arg){
        String sql = "select * from Utilizadores where loginId LIKE '%" + arg + "%' or nome like '%" + arg + "%'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Utilizador> users = new ArrayList<Utilizador>();
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()){
            do {
                String loginId="", nome="", desc="";
                loginId = c.getString(c.getColumnIndexOrThrow("loginId"));
                nome = c.getString(c.getColumnIndexOrThrow("nome"));
                desc = c.getString(c.getColumnIndexOrThrow("description"));

                Utilizador utilizador = new Utilizador(loginId,nome,desc);

                users.add(utilizador);
            }while (c.moveToNext());
        }
        return users;
    }

    public void updateDescricao(String loginId, String descricao){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("description", descricao);
        db.update("Utilizadores", cv, "loginId='" + loginId + "'", null);
    }
}
