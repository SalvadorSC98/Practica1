package com.example.practica1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private var correoRecordado: String? = null
    private var contrasenaRecordada: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)

        imageView.setOnClickListener {
            finish()
        }

        val texto1 = findViewById<TextInputEditText>(R.id.texto1)
        val texto2 = findViewById<TextInputEditText>(R.id.texto2)
        val switchRecordar = findViewById<Switch>(R.id.switch1)

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


        // Crear dos objetos Persona
        val usuario1 = Usuario("Usuario1", "usuario1@example.com", "contrasena1", R.drawable.imagen1)
        val usuario2 = Usuario("Usuario2", "usuario2@example.com", "contrasena2", R.drawable.imagen2)

        val listaUsuarios = mutableListOf(
            usuario1,
            usuario2
        )


        button.setOnClickListener {
            val email = texto1.text.toString()
            val contrasena = texto2.text.toString()

            switchRecordar.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    correoRecordado = texto1.text.toString()
                    contrasenaRecordada = texto2.text.toString()
                } else {
                    correoRecordado = null
                    contrasenaRecordada = null
                }
            }

            if (validarCredenciales(email, contrasena, listaUsuarios)) {
                // Credenciales correctas, mostrar mensaje de éxito
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            } else {
                // Credenciales incorrectas, mostrar mensaje de error
                Toast.makeText(this, "Acceso denegado", Toast.LENGTH_SHORT).show()
            }
        }




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


    fun validarCredenciales(email: String, contrasena: String, listaUsuarios: List<Usuario>): Boolean {
        for (usuario in listaUsuarios) {
            if (usuario.email == email && usuario.contrasena == contrasena) {
                return true // Credenciales correctas
            }
        }
        return false // Credenciales incorrectas
    }


}