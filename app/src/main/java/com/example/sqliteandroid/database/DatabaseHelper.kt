package com.example.sqliteandroid.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context : Context) : SQLiteOpenHelper (
    //1 Contexto , 2)Nome do Banco de Dados
    //3 CursorFactory 4)Versão do banco de dados
    context,"loja.db", null ,1
) {

    //Metódo chamado uma única vez
    override fun onCreate(db: SQLiteDatabase?) {



    }

    //Metódo chamado quando tiver um nova versão
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        //cria a tabela de vendas
        TODO("Not yet implemented")
    }
}