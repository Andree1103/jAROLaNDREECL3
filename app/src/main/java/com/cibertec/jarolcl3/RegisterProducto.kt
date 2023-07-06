package com.cibertec.jarolcl3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cibertec.jarolcl3.databinding.ActivityMainBinding
import com.cibertec.jarolcl3.databinding.RegisterProductoBinding
import com.cibertec.jarolcl3.model.RegisterProductRequest
import com.cibertec.jarolcl3.networkin.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class RegisterProducto : AppCompatActivity() {
    private lateinit var  binding: RegisterProductoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= RegisterProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register_producto)

        binding.btnRegiste.setOnClickListener {
            val nom = binding.tvName.text.toString()
            val desc = binding.tvDesc.text.toString()
            val stock = binding.tvCant.text.toString().toInt()

            GlobalScope.launch(Dispatchers.Main) {

                try {
                    binding.progressBar2.visibility = View.VISIBLE

                    val response = withContext(Dispatchers.IO) {
                        Api.build().saveProducto(request = RegisterProductRequest(nom, desc,stock))
                    }

                    if (response.isSuccessful) {
                        val resultBody = response.body()
                        resultBody?.let {
                            Toast.makeText(this@RegisterProducto, it.resultadoMensaje,Toast.LENGTH_LONG).show()
                        }
                    }else {
                        Toast.makeText(this@RegisterProducto, response.message(), Toast.LENGTH_SHORT).show()
                    }

                } catch (ex: IOException) {
                    Toast.makeText(
                        this@RegisterProducto,
                        "No tienes conexion a Internet. Verificar",
                        Toast.LENGTH_LONG
                    ).show()
                } catch (ex: Exception) {
                    Toast.makeText(this@RegisterProducto, ex.message, Toast.LENGTH_LONG).show()
                } finally {
                    binding.progressBar2.visibility = View.GONE
                    binding.tvName.setText("")
                    binding.tvDesc.setText("")
                    binding.tvCant.setText("")
                }

            }
        }
    }
}