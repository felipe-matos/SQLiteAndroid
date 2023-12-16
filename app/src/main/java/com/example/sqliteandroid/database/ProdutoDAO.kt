package com.example.sqliteandroid.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.sqliteandroid.model.Produto

class ProdutoDAO (context : Context) : IProdutoDAO {

    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(produto: Produto): Boolean {

        val titulo = produto.titulo

        //val sql = "INSERT into produtos VALUES (null,'$titulo', 'Descricao..');"

        val valores = ContentValues()
        valores.put("${DatabaseHelper.TITULO}",produto.titulo)
        valores.put("${DatabaseHelper.DESCRICAO}",produto.descricao)

        try {
           // escrita.execSQL(sql)
            escrita.insert(
                DatabaseHelper.TABELA_PRODUTOS,
                null,
                valores,

            )
            Log.i("info_db", "Sucesso ao inserir ")
        } catch (e: Exception) {
            Log.i("info_db", "Error ao inserir : ")
            return false

        }

        return true

    }

    override fun atualizar(produto: Produto): Boolean {

         /*
            val titulo = produto.titulo
            val idProduto = produto.idProduto
            val sql = "UPDATE produtos SET titulo = '$titulo' WHERE id_produto = $idProduto; "*/

        val valores = ContentValues()
        valores.put("${DatabaseHelper.TITULO}",produto.titulo)
        valores.put("${DatabaseHelper.DESCRICAO}",produto.descricao)
        val args = arrayOf(produto.idProduto.toString())
        try {
            escrita.update(
                DatabaseHelper.TABELA_PRODUTOS,
                valores,
                "id_produto = ? ",
                args
            )
            //escrita.execSQL(sql)
            Log.i("info_db", "Sucesso ao atualizar ")
        } catch (e: Exception) {
            Log.i("info_db", "Error ao atualizar : ")
            return false
        }

        return true
    }

    override fun remover(idProduto: Int): Boolean {

        //val sql = "DELETE FROM ${DatabaseHelper.TABELA_PRODUTOS} WHERE ${DatabaseHelper.ID_PRODUTO} = $idProduto;"

        var args = arrayOf(idProduto.toString())
        try {
            escrita.delete(
                DatabaseHelper.TABELA_PRODUTOS,
                "${DatabaseHelper.ID_PRODUTO} = ?",
                args
            )
            //escrita.execSQL(sql)
            Log.i("info_db", "Sucesso ao remover ")
        }catch (e:Exception){
            Log.i("info_db", "Error ao remover : ")
            return false

        }

        return true
    }

    override fun listar(): List<Produto> {

        val listaProdutos = mutableListOf<Produto>()

        val sql =  "SELECT * FROM ${DatabaseHelper.TABELA_PRODUTOS};"
        val cursor = leitura.rawQuery(sql,null)

        val indiceId = cursor.getColumnIndex("${DatabaseHelper.ID_PRODUTO}")
        val indiceTitulo = cursor.getColumnIndex("${DatabaseHelper.TITULO}")
        val indiceDescricao = cursor.getColumnIndex("${DatabaseHelper.DESCRICAO}")

        while (cursor.moveToNext()){//false ou true

            val idProduto = cursor.getInt(indiceId)
            val titulo = cursor.getString(indiceTitulo)
            val descricao = cursor.getString(indiceDescricao)
            Log.i("info_db", "id $idProduto - $titulo")

            val produto =
                listaProdutos.add(
                    Produto(idProduto,titulo,descricao)
                )
        }

        return listaProdutos
    }

}