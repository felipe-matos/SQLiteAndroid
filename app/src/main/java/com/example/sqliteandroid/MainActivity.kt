package com.example.sqliteandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        produtoDAO.remover(1)

    }


    private fun atualizar() {

        val titulo = binding.editProduto.text.toString()

        val produtoDAO = ProdutoDAO(this)
        val produto = Produto(
            1, titulo, "descricao.."
        )
        produtoDAO.atualizar(produto)
    }


    private fun listar() {

        val produto = ProdutoDAO(this)
        val listaProdutos = produto.listar()

        var texto =  ""
        if(listaProdutos.isNotEmpty()){
            listaProdutos.forEach{produto ->
                texto+= "${produto.idProduto} - ${produto.titulo}\n"
                Log.i("info_db", "${produto.idProduto} - ${produto.titulo}")
            }
            binding.textResultado.text = texto
        } else {
            binding.textResultado.text = "Nehum item cadastrado"
        }

    }

    private fun salvar() {

        val titulo = binding.editProduto.text.toString()

        val produtoDAO = ProdutoDAO(this)
        val produto = Produto(
            -1, titulo, "descricao.."
        )
        if(produtoDAO.salvar(produto)){
            Toast.makeText(this, "Sucesso ao cadastrar produto", Toast.LENGTH_SHORT).show()
            binding.editProduto.setText("")
        } else {
            Toast.makeText(this, "Error ao cadastrar produto", Toast.LENGTH_SHORT).show()

        }
    }
}