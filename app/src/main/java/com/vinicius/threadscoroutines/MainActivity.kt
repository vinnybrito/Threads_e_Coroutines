package com.vinicius.threadscoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vinicius.threadscoroutines.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnAbrir.setOnClickListener {
            startActivity(
                Intent(this, SegundaActivity::class.java)
            )
        }
        binding.btnIniciar.setOnClickListener {
            MinhaThread().start()
            /*
            repeat(30) { indice ->
                Log.i("info_thread", "Executando $indice T: ${currentThread().name}")
                sleep(1000) // ms 1000 -> 1 segundo
            }
             */
        }
    }

    // Abaixo está a Thread que estou criando
    inner class MinhaThread : Thread() {
        override fun run() {
            super.run()
            repeat(30) { indice ->
                Log.i("info_thread", "Minha Thread $indice T: ${currentThread().name}")
                runOnUiThread {
                    binding.btnIniciar.text = "Executando $indice T: ${currentThread().name}"
                    binding.btnIniciar.isEnabled = false
                    if (indice == 29) {
                        binding.btnAbrir.text = "Reiniciar Execução"
                        binding.btnAbrir.isEnabled = true
                    }
                }
                sleep(1000) // ms 1000 -> 1 segundo
            }
        }
    }
}