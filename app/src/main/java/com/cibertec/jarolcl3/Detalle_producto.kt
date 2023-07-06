package com.cibertec.jarolcl3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cibertec.jarolcl3.databinding.ActivityDetalleProductoBinding
import com.cibertec.jarolcl3.databinding.RegisterProductoBinding
import com.cibertec.jarolcl3.networkin.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class Detalle_producto : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleProductoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        bundle?.let {
            val code = it.getString("KEY_ID")  ?: ""
            binding.tvCodigo.text = code
        } ?: kotlin.run {
            binding.tvCodigo.text=""
        }
        getListById()
        binding.fabRegisteradoView.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity((intent))
        }
    }

    private fun getListById() {
        binding.tvProducto.text=""
        binding.tvDescripcion.text=""
        binding.tvStock.text=""

        GlobalScope.launch(Dispatchers.Main){
            try {
                binding.progressBar2.visibility = View.VISIBLE
                val response = withContext(Dispatchers.IO){
                    Api.build().getProductoById(binding.tvCodigo.text.toString().toInt())
                }
                binding.tvProducto.append(response.producto)
                binding.tvDescripcion.append(response.descripcion)
                binding.tvStock.append("${response.stock}")
            } catch (ex:IOException) {
                Toast.makeText(this@Detalle_producto, "Revisa tu conexion a internet", Toast.LENGTH_SHORT).show()
            } catch (ex:Exception) {
                Toast.makeText(this@Detalle_producto, ex.message, Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar2.visibility = View.GONE
            }
        }
    }
}