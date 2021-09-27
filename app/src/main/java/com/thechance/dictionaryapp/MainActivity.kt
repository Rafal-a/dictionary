package com.thechance.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.thechance.dictionaryapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      val word = binding.inputWord.text
        flowData()

    }

    @FlowPreview
    private fun flowData() {

        binding.inputWord.doOnTextChanged { text, start, before, count ->
            val flow1 = flow {
                emit(Network.sendData(text.toString(), "en", "ar").toString())
                println(text.toString())
            }.debounce(1500)


            lifecycleScope.launch(Dispatchers.Main){
                flow1.collect {
                    Log.i("HEHEH", it)
                    binding.textView.text = it

                }
            }

        }

    }


}