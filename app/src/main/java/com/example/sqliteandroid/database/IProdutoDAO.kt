package com.example.sqliteandroid.database

import com.example.sqliteandroid.model.Produto

interface IProdutoDAO {

    fun salvar(produto: Produto): Boolean
    fun atualizar(produto: Produto): Boolean
    fun remover(idProduto : Int ): Boolean
    fun listar(): List<Produto>
}