package com.example.menu_diario.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "menu_diario.db";
    public static final String TABLE_MENU_DIARIO = "t_menudiario";
    public static final String TABLE_MENU_SEMANAL = "t_menusemanal";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MENU_DIARIO + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tipo_menu TEXT NOT NULL," +
                "detalle TEXT NOT NULL," +
                "preparacion TEXT NOT NULL," +
                "compras TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MENU_SEMANAL + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "semana TEXT NOT NULL," +
                "dia_semana TEXT NOT NULL," +
                "desayuno TEXT NOT NULL," +
                "almuerzo TEXT NOT NULL," +
                "merienda TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MENU_DIARIO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MENU_SEMANAL);
        onCreate(sqLiteDatabase);
    }
}