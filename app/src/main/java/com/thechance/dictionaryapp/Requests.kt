package com.thechance.dictionaryapp

import com.google.gson.annotations.SerializedName

data class Requests(
    @SerializedName("q")
    val word:String,
    @SerializedName("source")
    val sourceLanguage:String,
    @SerializedName("target")
    val targetLanguage:String,
)