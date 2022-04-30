package com.example.MyUniverse.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.MyUniverse.DBHelper;
import com.example.MyUniverse.constructors.Utilizador;

import java.util.ArrayList;

public class SeguidoresDAO {
    private final SQLiteDatabase db;
    private final DBHelper dbHelper;


    public SeguidoresDAO(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void follow(String follower, String followed){
        ContentValues contentValues = new ContentValues();
        contentValues.put("pessoa", follower);
        contentValues.put("seguiu", followed);
        db.insert("Seguidores",null , contentValues);
    }

    public void unfollow(String loginId, String unfollowed){
        db.delete("Seguidores", "pessoa='" + loginId + "' and seguiu='" + unfollowed + "'", null);
    }

    public int countFollowers(String userId){
        String sql = "SELECT COUNT(*) FROM Seguidores WHERE seguiu ='" + userId + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        int total = Integer.parseInt(statement.simpleQueryForString());
        return total;
    }

    public int countFollowing(String userId){
        String sql = "SELECT COUNT(*) FROM Seguidores WHERE pessoa='" + userId + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        int total = Integer.parseInt(statement.simpleQueryForString());
        return total;
    }

    public boolean checkIfUserFollows(String userId, String userProfileId){
        String sql = "SELECT * FROM Seguidores WHERE pessoa='" + userId + "' and seguiu='" + userProfileId + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        if(c.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<Utilizador> getUserFollowers(String userId){
        String sql = "SELECT pessoa FROM Seguidores WHERE seguiu='" + userId + "'";
        ArrayList<Utilizador> users = new ArrayList<Utilizador>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        String person;
        if(c.moveToFirst()){
            do {
                person = c.getString(c.getColumnIndexOrThrow("pessoa"));
                Utilizador utilizador = new Utilizador(person);
                users.add(utilizador);
            }while (c.moveToNext());
        }
        return users;
    }

    public ArrayList<Utilizador> getWhoUserFollows(String userId){
        String sql = "SELECT seguiu FROM Seguidores WHERE pessoa='" + userId + "'";
        ArrayList<Utilizador> users = new ArrayList<Utilizador>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        String person;
        if(c.moveToFirst()){
            do {
                person = c.getString(c.getColumnIndexOrThrow("seguiu"));
                Utilizador utilizador = new Utilizador(person);
                users.add(utilizador);
            }while (c.moveToNext());
        }
        return users;
    }
}
