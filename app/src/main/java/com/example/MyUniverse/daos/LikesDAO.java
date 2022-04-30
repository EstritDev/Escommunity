package com.example.MyUniverse.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.MyUniverse.DBHelper;

public class LikesDAO {
    private final SQLiteDatabase db;
    private final DBHelper dbHelper;

    public LikesDAO(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void like(int postId, String userId, String day, String time){
        ContentValues contentValues = new ContentValues();
        contentValues.put("postId", postId);
        contentValues.put("userId", userId);
        contentValues.put("day", day);
        contentValues.put("time", time);
    }

    public int getLikes(int postId){
        String sql = "SELECT count(*) FROM Likes WHERE postId='" + postId + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        int total = Integer.parseInt(statement.simpleQueryForString());
        return total;
    }
}
