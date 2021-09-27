package com.thechance.dictionaryapp

sealed class State<out T >{
    //get the info state
    data class Success<T>(val data:T) : State<T>()
    data class Error(val message:String) : State<Nothing>()
    object Loading : State<Nothing>()

}
