package com.thechance.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.thechance.dictionaryapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val  flow: Flow?=null


    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        collectData()

    }
    @FlowPreview
    fun foo(response:State<Response>) {
        when(response){
            is State.Error->{}
            is State.Success ->{
                collectData()
                bindData(response.data)
            }


        }

    }

    private fun bindData(data: Response) {
       lifecycleScope.launch(Dispatchers.Main) {
           binding.apply {
               textView.text = data.translatedWord
           }
       }
    }

    @FlowPreview
    fun collectData() {

        binding.inputWord.doOnTextChanged { text, start, before, count ->
            lifecycleScope.launch(Dispatchers.IO) {
                Flow.flowData(text.toString(), "en", "es").collect {
                    foo(it)
                }
            }
        }
    }
    }

