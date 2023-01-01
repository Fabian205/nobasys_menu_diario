package com.example.menu_diario.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.menu_diario.entidades.Menues;

import java.util.ArrayList;

public class DbMenuDiario extends DbHelper {

    Context context;

    public DbMenuDiario(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarMenu(String tipo_menu, String detalle, String preparacion, String compras) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("tipo_menu", tipo_menu);
            //values.put("cod_tipo_menu", cod_tipo_menu);
            values.put("detalle", detalle);
            values.put("preparacion",preparacion);
            values.put("compras", compras);

            id = db.insert(TABLE_MENU_DIARIO, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Menues> mostrarMenues() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Menues> listaMenues = new ArrayList<>();
        Menues menu;
        Cursor cursorMenues;

        cursorMenues = db.rawQuery("SELECT * FROM " + TABLE_MENU_DIARIO + " ORDER BY tipo_menu ASC", null);

        if (cursorMenues.moveToFirst()) {
            do {
                menu = new Menues();
                menu.setId(cursorMenues.getInt(0));
                menu.setTipo_menu(cursorMenues.getString(1));
                //menu.setCod_tipo_menu(cursorMenues.getString(2));
                menu.setDetalle(cursorMenues.getString(2));
                menu.setPreparacion(cursorMenues.getString(3));
                menu.setCompras(cursorMenues.getString(4));

                listaMenues.add(menu);
            } while (cursorMenues.moveToNext());
        }
        cursorMenues.close();
        return listaMenues;
    }

    public ArrayList<Menues> mostrarMenuesDes() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Menues> listaMenues = new ArrayList<>();
        Menues menu;
        Cursor cursorMenues;

        cursorMenues = db.rawQuery("SELECT * FROM " + TABLE_MENU_DIARIO + " WHERE tipo_menu = 'DESAYUNO' " + " ORDER BY tipo_menu ASC", null);

        if (cursorMenues.moveToFirst()) {
            do {
                menu = new Menues();
                menu.setId(cursorMenues.getInt(0));
                menu.setTipo_menu(cursorMenues.getString(1));
                //menu.setCod_tipo_menu(cursorMenues.getString(2));
                menu.setDetalle(cursorMenues.getString(2));
                menu.setPreparacion(cursorMenues.getString(3));
                menu.setCompras(cursorMenues.getString(4));

                listaMenues.add(menu);
            } while (cursorMenues.moveToNext());
        }
        cursorMenues.close();
        return listaMenues;
    }

    public ArrayList<Menues> mostrarMenuesAlm() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Menues> listaMenues = new ArrayList<>();
        Menues menu;
        Cursor cursorMenues;

        cursorMenues = db.rawQuery("SELECT * FROM " + TABLE_MENU_DIARIO + " WHERE tipo_menu = 'ALMUERZO' " + " ORDER BY tipo_menu ASC", null);

        if (cursorMenues.moveToFirst()) {
            do {
                menu = new Menues();
                menu.setId(cursorMenues.getInt(0));
                menu.setTipo_menu(cursorMenues.getString(1));
                //menu.setCod_tipo_menu(cursorMenues.getString(2));
                menu.setDetalle(cursorMenues.getString(2));
                menu.setPreparacion(cursorMenues.getString(3));
                menu.setCompras(cursorMenues.getString(4));

                listaMenues.add(menu);
            } while (cursorMenues.moveToNext());
        }
        cursorMenues.close();
        return listaMenues;
    }

    public ArrayList<Menues> mostrarMenuesMer() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Menues> listaMenues = new ArrayList<>();
        Menues menu;
        Cursor cursorMenues;

        cursorMenues = db.rawQuery("SELECT * FROM " + TABLE_MENU_DIARIO + " WHERE tipo_menu = 'MERIENDA' " + " ORDER BY tipo_menu ASC", null);

        if (cursorMenues.moveToFirst()) {
            do {
                menu = new Menues();
                menu.setId(cursorMenues.getInt(0));
                menu.setTipo_menu(cursorMenues.getString(1));
                //menu.setCod_tipo_menu(cursorMenues.getString(2));
                menu.setDetalle(cursorMenues.getString(2));
                menu.setPreparacion(cursorMenues.getString(3));
                menu.setCompras(cursorMenues.getString(4));

                listaMenues.add(menu);
            } while (cursorMenues.moveToNext());
        }
        cursorMenues.close();
        return listaMenues;
    }

    public Menues verMenu(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Menues menu = null;
        Cursor cursorMenues;

        cursorMenues = db.rawQuery("SELECT * FROM " + TABLE_MENU_DIARIO + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorMenues.moveToFirst()) {
            menu = new Menues();
            menu.setId(cursorMenues.getInt(0));
            menu.setTipo_menu(cursorMenues.getString(1));
            //menu.setCod_tipo_menu(cursorMenues.getString(2));
            menu.setDetalle(cursorMenues.getString(2));
            menu.setPreparacion(cursorMenues.getString(3));
            menu.setCompras(cursorMenues.getString(4));
        }

        cursorMenues.close();

        return menu;
    }

    public boolean editarMenues(int id, String tipo_menu, String detalle, String preparacion, String compras) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_MENU_DIARIO + " SET tipo_menu = '" + tipo_menu + "', detalle = '" + detalle + "', preparacion = '" + preparacion + "', compras = '" + compras + "'  WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarMenues(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_MENU_DIARIO + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
