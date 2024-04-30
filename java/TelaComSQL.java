package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class TelaComSQL extends AppCompatActivity {

    BancoComSQL banco;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_com_sql);
        edit = (EditText) findViewById(R.id.editTextComSQL);
        banco = new BancoComSQL(this);
    }

    public void inserirComSQLClick(View v){
        banco.inserirSQL("Celta", "AAA1234", "2001");
    }

    public void alterarComSQLClick(View v){
        banco.alterarSQL(1, "Celtinha", "aaa4321", "2002");
    }

    public void excluirComSQLClick(View v){
        banco.excluirSQL(1);
    }

    public void buscarComSQLClick(View v){
        //edit.setText(banco.buscarSQLPorNome("Celtinha"));
        //edit.setText(banco.buscarSQLPorID(1));
        String consulta = banco.buscaTudo();
        edit.setText(consulta);
    }

    public void buscarPeloNomeComSQLClick(View v){
        String consulta = banco.buscarSQLPorNome("Celta");
        edit.setText(consulta);
    }
    public void buscarPeloIDComSQLClick(View v){
        String consulta = banco.buscarSQLPorID(5);
        edit.setText(consulta);
    }


}
