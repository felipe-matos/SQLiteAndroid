package com.example.sqliteandroid.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHelper(context : Context) : SQLiteOpenHelper (
    //1 Contexto , 2)Nome do Banco de Dados
    //3 CursorFactory 4)Versão do banco de dados
    context,"loja.db", null ,2
) {

    companion object{
        const val TABELA_PRODUTOS = "produtos"
        const val ID_PRODUTO = "id_produto"
        const val TITULO = "titulo"
        const val DESCRICAO = "descricao"


    }

    //Metódo chamado uma única vez
    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("info_db", "Executou o onCreate : ")
        val sql = "CREATE TABLE IF NOT EXISTS $TABELA_PRODUTOS (" +
                "$ID_PRODUTO INTEGER not NULL PRIMARY KEY AUTOINCREMENT," +
                "$TITULO VARCHAR (100)," +
                "$DESCRICAO text " +
                ");"
       try {
           db?.execSQL(sql)
           Log.i("info_db", "Sucesso ao criar a tabela : ")
       }catch (e : Exception){
            e.printStackTrace()
           Log.i("info_db", "Error ao criar a tabela : ")
       }

    }

    //Metódo chamado quando tiver um nova versão
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        Log.i("info_db", "Executou o onUpgrade : ")
        //cria a tabela de vendas
        TODO("Not yet implemented")
    }
}