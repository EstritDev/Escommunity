package com.example.escommunity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{
        private Context context;
        private static final String bdEscommunity = "bd_escommunity";
        public static final String tbUtilizadores = "Utilizadores";
        public static final String tbPosts = "Posts";
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
                + "dia text not null)";

        public DBHelper(Context context) {
            super(context, bdEscommunity, null, versao);
            this.context= context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(tableUtilizadores);
            db.execSQL(tablePosts);
            Log.i("bd", "Base de dados criada com sucesso");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tbUtilizadores);
            db.execSQL("DROP TABLE IF EXISTS " + tbPosts);
            onCreate(db);
        }

}
