package com.example.MyUniverse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{
        private Context context;
        private static final String bdMyUniverse = "bd_MyUniverse";
        public static final String tbUtilizadores = "Utilizadores";
        public static final String tbPosts = "Posts";
        public static final String tbSeguidores = "Seguidores";
        public static final String tbLikes = "Likes";
        private static final int versao = 1;

        public static final String tableUtilizadores = "create table " + tbUtilizadores
                + "(_id integer primary key autoincrement,"
                + "loginId text not null,"
                + "nome text not null,"
                + "email text not null,"
                + "password text not null,"
                + "description text not null)";

        public static final String tablePosts = "create table " + tbPosts
                + "(_idPost integer primary key autoincrement,"
                + "userId text not null,"
                + "conteudo text not null,"
                + "likes integer not null,"
                + "edited text not null,"
                + "hora text not null,"
                + "dia text not null)";

        public static final String tableSeguidores = "create table " + tbSeguidores
                + "(_id integer primary key autoincrement,"
                + "pessoa text not null,"
                + "seguiu text not null)";

        public static final String tableLikes = "create table " + tbLikes
                + "(_id integer primary key autoincrement,"
                + "postId integer not null,"
                + "userId integer not null,"
                + "day text not null,"
                + "time text not null)";

        public DBHelper(Context context) {
            super(context, bdMyUniverse, null, versao);
            this.context= context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(tableUtilizadores);
            db.execSQL(tablePosts);
            db.execSQL(tableSeguidores);
            db.execSQL(tableLikes);
            Log.i("bd", "Base de dados criada com sucesso");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tbUtilizadores);
            db.execSQL("DROP TABLE IF EXISTS " + tbPosts);
            db.execSQL("DROP TABLE IF EXISTS " + tbSeguidores);
            db.execSQL("DROP TABLE IF EXISTS " + tbLikes);
            onCreate(db);
        }

}
