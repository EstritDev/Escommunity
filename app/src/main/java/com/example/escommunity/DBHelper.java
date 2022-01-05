package com.example.escommunity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper {
    public class DbHelper extends SQLiteOpenHelper {
        private Context context;
        private static final String bdEscommunity = "bd_escommunity";
        public static final String tbUtilizadores = "Utilizadores";
        private static final int versao = 1;

        public static final String tableUtilizadores = "create table " + tbUtilizadores
                + "(_id integer primary key autoincrement,"
                + " username text not null,"
                + " email text not null,"
                + " password text not null)";

        public DbHelper(Context context) {
            super(context, bdEscommunity, null, versao);
            this.context= context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(tableUtilizadores);
            Log.i("bd", "Base de dados criada com sucesso");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tbUtilizadores);
            onCreate(db);
        }

    }
}
