package com.example.escommunity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PostsDAO {
    private final SQLiteDatabase db;
    private final DBHelper dbHelper;

    public PostsDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<Posts> getPosts(){
        String sql = "select Utilizadores.nome, Posts.conteudo, Posts.dia from Posts inner join Utilizadores on Posts.userId = Utilizadores.loginId";
        ArrayList<Posts> listaPosts = new ArrayList<Posts>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        String user,conteudo,dia;
        if(c.moveToFirst()){
            do{
                user = c.getString(c.getColumnIndexOrThrow("nome"));
                conteudo = c.getString(c.getColumnIndexOrThrow("conteudo"));
                dia = c.getString(c.getColumnIndexOrThrow("dia"));
                Posts post = new Posts(user,conteudo,dia);
                listaPosts.add(post);
            }while (c.moveToNext());
        }
        return listaPosts;
    }

    public void guardarPost(String userId, String conteudo, String dia){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", userId);
        contentValues.put("conteudo", conteudo);
        contentValues.put("dia", dia);
        db.insert("Posts",null , contentValues);
    }

    public void eliminarPost(int id){
        db.delete("Posts", "_idPost=" + id, null);
    }

    public void editarPost(String conteudo, int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("conteudo", conteudo);
        db.update("Posts",cv,"_idPost=" + id,null);
    }
}
