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

    private var emailRemembered: String? = null
    private var passRemembered: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val closeButton = findViewById<ImageView>(R.id.imageView)

        closeButton.setOnClickListener {
            finish()
        }

        val emailText = findViewById<TextInputEditText>(R.id.texto1)
        val passText = findViewById<TextInputEditText>(R.id.texto2)
        val switchRemember = findViewById<Switch>(R.id.switch1)

        val button = findViewById<Button>(R.id.button)
        button.setBackgroundColor(Color.LTGRAY)

        emailText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                changeButtonColor(emailText, passText, button)
            }
        })


        passText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                changeButtonColor(emailText, passText, button)
            }
        })

        
        val user1 = User("Usuario1", "usuario1@example.com", "Contra1", R.drawable.imagen1)
        val user2 = User("Usuario2", "usuario2@example.com", "contrasena2", R.drawable.imagen2)

        val userList = mutableListOf(
            user1,
            user2
        )


        button.setOnClickListener {
            val email = emailText.text.toString()
            val password = passText.text.toString()

            switchRemember.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    emailRemembered = emailText.text.toString()
                    passRemembered = passText.text.toString()
                } else {
                    emailRemembered = null
                    passRemembered = null
                }
            }

            if (validate(email, password, userList)) {
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Acceso denegado", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun changeButtonColor(texto1: TextInputEditText, texto2: TextInputEditText, button: Button) {
        if (texto1.text.toString().isNotEmpty() && texto2.text.toString().isNotEmpty()) {
            button.isEnabled = true
            button.setBackgroundColor(0xFF5722)
        } else {
            button.isEnabled = false
            button.setBackgroundColor(Color.LTGRAY)
        }
    }


    fun validate(email: String, password: String, userList: List<User>): Boolean {
        for (user in userList) {
            if (user.email == email) {
                if (user.password == password && passwordRules(password)) {
                    return true
                }
            }
        }
        return false
    }

    fun passwordRules(password: String): Boolean {
        if (password.length < 6 || password.length > 8) {
            return false
        }

        val haveCaps = password.any { it.isUpperCase() }
        val haveLowers = password.any { it.isLowerCase() }
        val haveNumbers = password.any { it.isDigit() }

        return haveCaps && haveLowers && haveNumbers
    }



}