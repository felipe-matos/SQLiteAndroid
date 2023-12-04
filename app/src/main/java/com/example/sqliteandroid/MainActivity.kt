package com.example.sqliteandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sqliteandroid.database.DatabaseHelper

class MainActivity : AppCompatActivity() {

    private val bancoDados by lazy {
        DatabaseHelper(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            bancoDados.writableDatabase.execSQL(
                "INSERT into produtos VALUES (null,'Notebook Acer', 'Descricao..');"
            )
            Log.i("info_db", "Sucesso ao inserir ")
        }catch (e:Exception){
            Log.i("info_db", "Error ao inserir : ")

        }


    }
}