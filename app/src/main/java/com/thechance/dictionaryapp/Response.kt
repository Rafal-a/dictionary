package com.thechance.dictionaryapp

import com.google.gson.annotations.SerializedName

data class Response( @SerializedName("translatedText")
                     val translatedWord:String
)
