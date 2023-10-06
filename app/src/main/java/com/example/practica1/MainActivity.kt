package com.example.practica1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)

        imageView.setOnClickListener {
            finish()
        }

        val texto1 = findViewById<TextInputEditText>(R.id.texto1)
        val texto2 = findViewById<TextInputEditText>(R.id.texto2)

        val button = findViewById<Button>(R.id.button)
        button.setBackgroundColor(Color.LTGRAY)

        texto1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                actualizarColorBoton(texto1, texto2, button)
            }
        })


        texto2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                actualizarColorBoton(texto1, texto2, button)
            }
        })
        

        
    }

    private fun actualizarColorBoton(texto1: TextInputEditText, texto2: TextInputEditText, button: Button) {
        if (texto1.text.toString().isNotEmpty() && texto2.text.toString().isNotEmpty()) {
            button.isEnabled = true
            button.setBackgroundColor(0xFF5722)
        } else {
            button.isEnabled = false
            button.setBackgroundColor(Color.LTGRAY)
        }
    }

}