package com.cibertec.jarolcl3.model

import com.google.gson.annotations.SerializedName

data class Producto(

    @SerializedName("codigo")
    val codigo: Int,
    @SerializedName("producto")
    val producto: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("stock")
    val stock: Int
)
