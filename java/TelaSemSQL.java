package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class TelaSemSQL extends AppCompatActivity {

    BancoSemSQL banco;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sem_sql);
        edit = (EditText) findViewById(R.id.editTextSemSQL);
        banco = new BancoSemSQL(this);
    }

    public void inserirSemSQLClick(View v){
        banco.inserirSemSQL("Corsa", "BBB9876", "1997");
    }
    public void alterarSemSQLClick(View v){
        banco.alterarSemSQL(2, "Corsinha", "bbb6789", "1998");
    }
    public void excluirSemSQLClick(View v){
        banco.excluirSemSQL(2);
    }
    public void buscarSemSQLClick(View v){
        edit.setText(banco.buscarSemSQL("Corsinha"));
    }
    public void buscarTudoSemSQLClick(View v){
        edit.setText(banco.buscarTudoSemSQL());
    }

}
