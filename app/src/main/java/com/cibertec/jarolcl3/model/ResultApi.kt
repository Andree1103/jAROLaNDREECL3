package com.cibertec.jarolcl3.model

import com.google.gson.annotations.SerializedName

data class ResultApi(
    @SerializedName("codigoMensaje")
    val codigoMensaje:Int,

    @SerializedName("resultadoMensaje")
    val resultadoMensaje:String
)
