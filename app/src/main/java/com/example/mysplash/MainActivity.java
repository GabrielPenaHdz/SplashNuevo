package com.example.mysplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsuariosBDService usuariosBDService = new UsuariosBDService(MainActivity.this);
        SQLiteDatabase bd = usuariosBDService.getWritableDatabase();

        if(bd!= null){
            Toast.makeText(this, "DB successfully built", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Fail",Toast.LENGTH_LONG).show();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this , login_activity.class);
                startActivity( intent );
                finish();

            }
        }, 4000);
    }
}