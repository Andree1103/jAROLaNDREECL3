package com.cibertec.jarolcl3.networkin

import com.cibertec.jarolcl3.model.Producto
import com.cibertec.jarolcl3.model.RegisterProductRequest
import com.cibertec.jarolcl3.model.ResultApi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


object Api {
    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("http://iafas4841-001-site1.atempurl.com/")
        .addConverterFactory(GsonConverterFactory.create())

    interface RemoteService {

        @GET("api/producto")
        suspend fun getAllProductos(): Response<List<Producto>>

        @POST("api/producto")
        suspend fun saveProducto(@Body request: RegisterProductRequest): Response<ResultApi>

        @GET("api/producto")
        suspend fun getProductoById(@Query("codigo") codigo:Int): Producto


        //@GET("api/masctoa")
    }

    fun build(): RemoteService {
        return builder.build().create(RemoteService::class.java)
    }
}