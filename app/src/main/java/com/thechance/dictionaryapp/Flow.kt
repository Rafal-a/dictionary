package com.thechance.dictionaryapp


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.Flow


object Flow {
    lateinit var flow: Flow<State<Response>>
    @FlowPreview
     fun flowData(text:String , source:String , target:String)=flow {
                emit(Network.sendData(text, source, target))
            }.flowOn(Dispatchers.IO).debounce(2000)
    }
