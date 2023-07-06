package com.cibertec.jarolcl3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cibertec.jarolcl3.databinding.ActivityMainBinding
import com.cibertec.jarolcl3.networkin.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var productoAdapter : ProductoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        //ProductoAdapter()

        binding.fabAdd.setOnClickListener{
            val intent = Intent(this,RegisterProducto::class.java)
            startActivity(intent)
        }
        setupAdapter()

    }
    override fun onStart(){
        super.onStart()
        getListados()

    }
    private fun getListados() {
        GlobalScope.launch(Dispatchers.Main) {

            try {
                binding.progressBar.visibility = View.VISIBLE
                val response = withContext(Dispatchers.IO){
                    Api.build().getAllProductos()
                }
                if (response.isSuccessful){
                    val pets = response.body()
                    pets?.let{
                        productoAdapter.updateList(it)
                    }
                }
            }catch (ex:IOException){
                Toast.makeText(this@MainActivity,"No tienes Internet",Toast.LENGTH_LONG).show()
            }
            catch (ex:Exception){
                Toast.makeText(this@MainActivity,ex.message,Toast.LENGTH_LONG).show()
            }
            //
            finally {
                binding.progressBar.visibility=View.GONE
            }
        }
    }
    private fun setupAdapter() {
        productoAdapter = ProductoAdapter(onItemView = {
            val bundle = Bundle().apply {
                putString("KEY_ID", it.codigo.toString())
            }
            val intent = Intent(this
            ,Detalle_producto::class.java).apply {
                putExtras(bundle
                )
            }
            startActivity(intent)
        })
        binding.rvProducto.adapter= productoAdapter
        getListados()
    }



}