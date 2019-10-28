package com.example.restapiphotoes.api

import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("name")
    var name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("region")
    val region: String
)
