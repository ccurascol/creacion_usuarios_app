package com.example.android.pets.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;


import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String COMMENTS_TABLE_CREATE = "CREATE TABLE user(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, correo TEXT,fecha_nac TEXT,password TEXT)";
    private static final String DB_NAME = "shelter.sqlite";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMMENTS_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Insertar un nuevo usuario
    public void insertar(String nombre, String correo, String fecha_nac, String password){
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("correo", correo);
        cv.put("fecha_nac", fecha_nac);
        cv.put("password", password);
        db.insert("user", null, cv);
    }


    public void update(String id, String nombre, String correo, String fecha_nac, String password){
        ContentValues cv = new ContentValues();
        //cv.put("id", id);
        cv.put("nombre", nombre);
        cv.put("correo", correo);
        cv.put("fecha_nac", fecha_nac);
        cv.put("password", password);

        db.update("user", cv, "_id="+id, null);
    }

    public void borrar(int id){
        String[] args = new String[]{String.valueOf(id)};
        db.delete("user", "_id=?", args);

    }

    //Obtener la lista de comentarios en la base de datos
    public ArrayList<User> getUsuarios(){
        //Creamos el cursor
        ArrayList<User> lista=new ArrayList<User>();
        Cursor c = db.rawQuery("select _id, nombre,correo,fecha_nac,password from user", null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                String nombre_ = c.getString(c.getColumnIndex("nombre"));
                String correo_ = c.getString(c.getColumnIndex("correo"));
                String fecha_nac_ = c.getString(c.getColumnIndex("fecha_nac"));
                String password_ = c.getString(c.getColumnIndex("password"));

                int id_=c.getInt(c.getColumnIndex("_id"));

                User userlist =new User( id_, nombre_, correo_, fecha_nac_,  password_);

                //Añadimos el comentario a la lista
                lista.add(userlist);
            } while (c.moveToNext());
        }

        //Cerramos el cursor
        c.close();
        return lista;
    }
}