package com.thechance.dictionaryapp

object UrlModifier {
    private var urlinfo:String = ""
    val url: String
        get() = urlinfo

    fun getUrlInfo(word:String="" ,sourceLanguage:String , targetLanguage:String ): String {
          urlinfo = "https://translate.astian.org/translate?" +
                "q=${word}" +
                "&source=$sourceLanguage" +
                "&target=$targetLanguage" +
                "&format=text"

        return urlinfo
    }


}