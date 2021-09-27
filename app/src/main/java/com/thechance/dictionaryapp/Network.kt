package com.thechance.dictionaryapp

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.*
import java.io.IOException
import java.net.URL

object Network {

    private val client = OkHttpClient()
    val gson = Gson()
    private val builder = FormBody.Builder()
    var result = ""
    var words:String?=""


    fun sendData(word: String , source:String , target:String):String?{

        var response:String=""
        val url = UrlModifier.getUrlInfo(word , source , target)
        val request = Request.Builder()
            .url(url)
            .post(builder.build())
            .build()

            client.newCall(request).enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {
                    println("----- ERROR ----- = ${e.message}")
                }

                override fun onResponse(call: Call, response: okhttp3.Response) {
                    val result  = gson.fromJson(response.body?.string().toString(), Response::class.java)
                    words = result.translatedWord


                }

            })
        return words
        }

    object TAG {
        const val LOG_TAG = "REQUEST"
    }


    }






