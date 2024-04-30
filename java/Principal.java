package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Principal extends AppCompatActivity {

    SQLiteDatabase db;
    String BANCO = "banco.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void criarBancoClick(View v){
        db = openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS carro (" +
                "ID INTEGER PRIMARY KEY, " +
                "NOME TEXT, " +
                "PLACA TEXT, " +
                "ANO TEXT);");
        db.close();
    }

    public void abreTela1Click(View v){
        Intent it = new Intent(this, TelaComSQL.class);
        startActivity(it);
    }
    public void abreTela2Click(View v){
        Intent it = new Intent(this, TelaSemSQL.class);
        startActivity(it);
    }
}
