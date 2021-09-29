package com.thechance.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.thechance.dictionaryapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        collectData()

    }

    // collect the data from flow
    @FlowPreview
    fun collectData() {
            binding.inputWord.doAfterTextChanged { text ->
                lifecycleScope.launch{
                    Flow.flowData(text.toString(), detectLanguage("English"), detectLanguage("Arabic")).collect {
                       // delay(1000)
                        stateCondition(it)

                    }
                }
            }


    }

    //check the State condition
    @FlowPreview
    fun stateCondition(response:State<Response>) {
        when(response){
            is State.Error->{}
            is State.Success ->{
                bindData(response.data)
            }
            is State.Loading -> {
                Toast.makeText(this@MainActivity ,  "Loading ..." ,Toast.LENGTH_SHORT).show()
            }
        }

    }

    //display the data in the ui
    private fun bindData(data: Response) {
           binding.apply {
               textView.text = data.translatedWord
           }

    }
    private fun detectLanguage(language:String):String{
        val languages = mapOf("English" to "en",
            "Arabic" to "ar",
            "Chinese" to "zh",
            "French" to "fr",
            "German" to "de",
            "Hindi" to "hi",
            "Indonesian" to "id",
            "Irish" to "ga",
            "Italian" to "it",
            "Japanese" to "ja",
            "Korean" to "ko",
            "Polish" to "pl",
            "Portuguese" to "pt",
            "Russian" to "ru",
            "Spanish" to "es",
            "Turkish" to "tr",
            "Vietnamese" to "vi",
        )
        // println(languages[language].toString())
        return languages[language].toString()
    }

    }

