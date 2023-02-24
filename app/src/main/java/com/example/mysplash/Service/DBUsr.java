package com.example.mysplash.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mysplash.json.MyInfo;

import java.util.ArrayList;
import java.util.List;

public class DBUsr extends UsuariosDBService {
    Context context;
    public DBUsr(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public long saveUsr(MyInfo info){
        long id = 0;
        try{
            UsuariosDBService usuariosDBservice = new UsuariosDBservice(context);
            SQLiteDatabase db = usuariosDBservice.getWritableDatabase();
            ContentValues values = new ContentValues();
            id = db.insert(TABLE_USERS, null, UsuariosContract.UsuarioEntry.toContentValues(info));
        }
        catch (Exception ex){
            ex.toString();
        }
        finally {
            return id;
        }
    }
    public List<MyInfo> getUsuarios(){
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        List<MyInfo>usuarios = null;
        MyInfo usuaio = null;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT*FROM" + TABLE_USERS, null);
        if (cursor == null){
            return null;
        }
        if (cursor.getCount() < 1){
         return null;
        }
        if(!cursor.moveToFirst()){
            return null;
        }
        Log.d(TAG, "" + cursor.getCount());
        usuarios = new ArrayList<MyInfo>();
        for (int i = 0; i < cursor.getCount(); i++){
            usuaio.setUser(cursor.getString(0));
            usuaio.setContrasena(cursor.getString(1));
            usuaio.setCorreo(cursor.getString(2));
            usuaio.setBox1(cursor.getString(3));
            usuaio.setBox2(cursor.getString(4));
            usuaio.setGen(cursor.getInt(5));
            usuaio.setFecha(cursor.getString(6));
            usuaio.setNumero(cursor.getString(7));
            usuaio.setFeliz(cursor.getInt(8));
            usuaio.setNotifi(cursor.getInt(9));
            usuaio.setEdad(cursor.getString(10));
            usuarios.add(usuaio);
            cursor.moveToNext();
        }
        return usuarios;
    }
    public MyInfo GetUsuario(String user){
        MyInfo info = new MyInfo();
        UsuariosDBService usuariosDBService = new UsuariosDBservice(context);
        SQLiteDatabase db = usuariosDBService.getRradableDatabase();
        Cursor cursor = null;
        String query = "SELECT * FROM t_usuarios WHERE usuario = ?";
        String[] args = {user};

        cursor = db.rawQuery(query, args);
        if(cursor.moveToFirst()){
            info.setId_usr(cursor.getInt(0));
            info.setUser(cursor.getString(1));
            info.setContrasena(cursor.getString(2));
            info.setCorreo(cursor.getString(3));
            info.setBox1(cursor.getString(4));
            info.setBox2(cursor.getString(5));
            info.setGen(cursor.getInt(6));
            info.setFecha(cursor.getString(7));
            info.setNumero(cursor.getString(8));
            info.setFeliz(cursor.getInt(9));
            info.setNotifi(cursor.getInt(10));
            info.setEdad(cursor.getString(11));
            return info;
        }
        cursor.close();
        return null;
    }
    public MyInfo GetUsuario(String user, String mail){
        MyInfo info = new MyInfo();
        UsuariosDBService usuariosDBService = new UsuariosDBService(context);
        SQLiteDatabase db = usuariosDBService.getReadableDatabase();
        Cursor cursor = null;
        String query = "SELECT * FROM t_usuarios WHERE usuario = ? AND mail = ?";
        String [] args = {user, mail};

        cursor = db.rawQuery(query, args);
        if (cursor.moveToFirst()) {
            info.setId_usr(cursor.getInt(0));
            info.setUser( cursor.getString( 1 ) );
            info.setContrasena(cursor.getString(2));
            info.setCorreo(cursor.getString(3));
            info.setBox1(cursor.getString(4));
            info.setBox2(cursor.getString(5));
            info.setGen(cursor.getInt(6));
            info.setFecha(cursor.getString(7));
            info.setNumero(cursor.getString(8));
            info.setFeliz(cursor.getInt(9));
            info.setNotifi(cursor.getInt(10));
            info.setEdad(cursor.getString(11));
            return info;
        }
        cursor.close();
        return null;
    }
    public boolean AlterUser(String user, String contra){
        boolean correcta = false;
        UsuariosDBService usuariosDBService = new UsuariosDBService(context);
        SQLiteDatabase db = usuariosDBService.getWrtableDatabase();
        try{
            db.execSQL("UPDATE " + TABLE_USERS + " SET paswd = '" + contra + "' WHERE usuario='" + user + "'");
            correcta = true;
        }catch (Exception ex){
            ex.toString();
        }
        return correcta;
    }
}
