package com.example.sqliteandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sqliteandroid.database.DatabaseHelper
import com.example.sqliteandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val bancoDados by lazy {
        DatabaseHelper(this)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            btnSalvar.setOnClickListener{
                salvar()
            }

            btnListar.setOnClickListener {
                listar()
            }
        }

    }

    private fun listar() {

        val sql =  "SELECT * FROM ${DatabaseHelper.TABELA_PRODUTOS};"
        val cursor = bancoDados.readableDatabase
            .rawQuery(sql,null)

        val indiceId = cursor.getColumnIndex("${DatabaseHelper.ID_PRODUTO}")
        val indiceTitulo = cursor.getColumnIndex("${DatabaseHelper.TITULO}")
        val indiceDescricao = cursor.getColumnIndex("${DatabaseHelper.DESCRICAO}")

       while (cursor.moveToNext()){//false ou true

           val idProduto = cursor.getInt(indiceId)
           val titulo = cursor.getString(indiceTitulo)
           val descricao = cursor.getString(indiceDescricao)
           Log.i("info_db", "id $idProduto - $titulo")

       }

    }

   private fun salvar(){

       val titulo = binding.editProduto.text.toString()
       val sql = "INSERT into produtos VALUES (null,'$titulo', 'Descricao..');"

        try {
            bancoDados.writableDatabase.execSQL(sql)
            Log.i("info_db", "Sucesso ao inserir ")
        }catch (e:Exception){
            Log.i("info_db", "Error ao inserir : ")

        }
    }
}