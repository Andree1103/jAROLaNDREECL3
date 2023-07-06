package com.cibertec.jarolcl3.model

data class RegisterProductRequest(
    val producto:String,
    val descripcion:String,
    val stock:Int
)
