package com.thechance.dictionaryapp


import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow


object Flow {
    lateinit var flow1: Flow<State<Response>>
    @FlowPreview
     fun flowData(text:String , source:String , target:String): Flow<State<Response>> {

             flow1 = flow {
                emit(Network.sendData(text, source, target))
            }.debounce(1500)

        return flow1
        }


    }
