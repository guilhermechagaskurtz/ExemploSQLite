package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class BancoSemSQL {

    SQLiteDatabase db;
    String BANCO = "banco.db";

    private Context ctx;
    public BancoSemSQL(Context ctx){
        this.ctx = ctx;
    }

    public void inserirSemSQL(String nome, String placa, String ano){
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("ano", ano);
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        long id = db.insert("carro", null, valores);//insert retorna o ID do item inserido
        db.close();
        Toast.makeText(ctx, "Dados inseridos, Id = "+id, Toast.LENGTH_SHORT).show();
    }

    public void alterarSemSQL(int id, String nome, String placa, String ano){
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("placa", placa);
        valores.put("ano", ano);
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        db.update("carro", valores, "id = "+id, null);
        //poderia ser feito assim também:
        //db.update("carro", valores, "id = ?", new String[]{ id+"" });
        db.close();
        Toast.makeText(ctx, "Dados alterados", Toast.LENGTH_SHORT).show();
    }

    public void excluirSemSQL(int id){
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        db.delete("carro", "id = "+id, null);
        //poderia ser feito assim também:
        //db.delete("carro", "id = ?",new String[]{id+""});

        db.close();
        Toast.makeText(ctx, "Registro excluído", Toast.LENGTH_SHORT).show();
    }

    public String buscarSemSQL(String nome){
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        //SELECT id, nome, placa, ano FROM carro WHERE nome like '%nome%'
        /*Cursor linhas = db.query(
                "carro",
                new String[] {"id, nome, placa, ano"},
                "nome like '%"+nome+"%'",
                null,
                null,
                null,
                null);*/

        //poderia ser feito assim também:
        Cursor linhas = db.query(
                "carro",
                new String[] {"id, nome, placa, ano"},
                "nome like ?",
                new String[]{"%"+nome+"%"},
                null,
                null,
                null);
        String retorno = "";
        if(linhas.moveToFirst()){ //retorna false se não há linhas na tabela
            do{
                String ID = linhas.getString(0);
                String NOME = linhas.getString(1);
                String PLACA = linhas.getString(2);
                String ANO = linhas.getString(3);
                retorno+=ID+","+ NOME+","+PLACA+","+ANO+"\n";
            }
            while(linhas.moveToNext()); //laço até a última linha da tabela
        }
        db.close();
        return retorno;
    }

    public String buscarTudoSemSQL(){
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        //SELECT * FROM carro
        Cursor linhas = db.query(
                "carro",
                null,
                null,
                null,
                null,
                null,
                null);
        String retorno = "";
        if(linhas.moveToFirst()){ //retorna false se não há linhas na tabela
            do{
                String ID = linhas.getString(0);
                String NOME = linhas.getString(1);
                String PLACA = linhas.getString(2);
                String ANO = linhas.getString(3);
                retorno+=ID+","+ NOME+","+PLACA+","+ANO+"\n";
            }
            while(linhas.moveToNext()); //laço até a última linha da tabela
        }
        db.close();
        return retorno;
    }
}
