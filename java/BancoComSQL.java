package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class BancoComSQL {

    SQLiteDatabase db;
    String BANCO = "banco.db";

    private Context ctx;
    public BancoComSQL(Context ctx){
        this.ctx = ctx;
    }

    public void inserirSQL(String nome, String placa, String ano){
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        db.execSQL("INSERT INTO carro (NOME, PLACA, ANO) VALUES (" +
                "'"+nome+"','"+placa+"','"+ano+"')");
        db.close();
        Toast.makeText(ctx, "Dados inseridos", Toast.LENGTH_SHORT).show();
    }

    public void alterarSQL(int id, String nome, String placa, String ano){
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        db.execSQL("UPDATE carro SET NOME = '"+nome+"'," +
                "PLACA = '"+placa+"'," +
                "ANO = '"+ano+"' WHERE ID = "+id);
        db.close();
        Toast.makeText(ctx, "Dados alterados", Toast.LENGTH_SHORT).show();

    }

    public void excluirSQL(int id){
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        db.execSQL("DELETE FROM carro WHERE ID = "+id);
        db.close();
        Toast.makeText(ctx, "Registro Excluído", Toast.LENGTH_SHORT).show();
    }

    //busca todos os carros do banco
    public String buscaTudo(){
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        Cursor linhas = db.rawQuery("SELECT * FROM carro", null);

        String retorno = "";
        if(linhas.moveToFirst()){ //se o SELECT retornou algo do banco
            do{
                //pega os dados do carro
                int id = linhas.getInt(0); //pega o ID que é a coluna 0
                String nome = linhas.getString(1); //pega a coluna NOME que é a coluna 1
                String placa = linhas.getString(2); //pega a coluna PLACA que é a coluna 2
                String ano = linhas.getString(3); //pega a coluna ANO que é a coluna 2
                retorno+=id+","+ nome+","+placa+","+ano+"\n";
            }while(linhas.moveToNext()); //tenta ir para o próximo carro
        }
        else{
            Toast.makeText(ctx, "Nenhum carro cadastrado", Toast.LENGTH_SHORT).show();
        }

        return retorno;
    }

    //busca um carro a partir do nome dele
    public String buscarSQLPorNome(String nome){
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        //Cursor linhas = db.rawQuery("SELECT * FROM carro WHERE nome = '"+nome+"'", null);
        //poderia ser feito assim também:
        Cursor linhas = db.rawQuery("SELECT * FROM carro WHERE nome = ?", new String[] { nome });

        String retorno = "";
        if(linhas.moveToFirst()){ //retorna false se não há linhas na tabela
            do{
                int ID = linhas.getInt(0);
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

    //busca um carro a partir do ID dele
    public String buscarSQLPorID(int id){
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        Cursor linhas = db.rawQuery("SELECT * FROM carro WHERE id = ?", new String[] { id+"" });
        String retorno = "";
        if(linhas.moveToFirst()){ //retorna false se não há linhas na tabela
            //como a consulta é por ID, não haverão mais de 1 carro com o mesmo ID
            //logo, não precisa ser feito um laço
            String ID = linhas.getString(0);
            String NOME = linhas.getString(1);
            String PLACA = linhas.getString(2);
            String ANO = linhas.getString(3);
            retorno+=ID+","+ NOME+","+PLACA+","+ANO+"\n";
        }
        db.close();
        return retorno;
    }
}
