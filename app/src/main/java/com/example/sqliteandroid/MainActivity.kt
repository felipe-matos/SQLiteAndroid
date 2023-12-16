package com.example.sqliteandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sqliteandroid.database.DatabaseHelper
import com.example.sqliteandroid.database.ProdutoDAO
import com.example.sqliteandroid.databinding.ActivityMainBinding
import com.example.sqliteandroid.model.Produto

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

        with(binding) {
            btnSalvar.setOnClickListener {
                salvar()
            }

            btnListar.setOnClickListener {
                listar()
            }

            btnAtualizar.setOnClickListener {
                atualizar()
            }

            btnRemover.setOnClickListener {
                remover()
            }
        }

    }

    private fun remover() {

        val produtoDAO = ProdutoDAO(this)

        produtoDAO.remover(3)

    }


    private fun atualizar() {

        val titulo = binding.editProduto.text.toString()

        val produtoDAO = ProdutoDAO(this)
        val produto = Produto(
            -1, titulo, "descricao.."
        )
        produtoDAO.atualizar(produto)
    }


    private fun listar() {

        val produto = ProdutoDAO(this)
        val listaProdutos = produto.listar()

        if(listaProdutos.isNotEmpty()){
            listaProdutos.forEach{produto ->
                Log.i("info_db", "${produto.idProduto} - ${produto.titulo}")
            }
        }

    }

    private fun salvar() {

        val titulo = binding.editProduto.text.toString()

        val produtoDAO = ProdutoDAO(this)
        val produto = Produto(
            -1, titulo, "descricao.."
        )
        produtoDAO.salvar(produto)
    }
}