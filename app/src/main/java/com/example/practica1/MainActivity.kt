package com.example.practica1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val closeButton = findViewById<ImageView>(R.id.closeButton)

        closeButton.setOnClickListener {
            finish()
        }

        val emailText = findViewById<TextInputEditText>(R.id.emailText)
        val passText = findViewById<TextInputEditText>(R.id.passText)
        val switchRemember = findViewById<SwitchCompat>(R.id.rememberSwitch)


        val button = findViewById<Button>(R.id.loginButton)
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


        val userList = UsersList.users
        val extras = Extras


        button.setOnClickListener {
            val email = emailText.text.toString()
            val password = passText.text.toString()

            if (validate(email, password, userList)) {
                val user = findUserByEmailAndPassword(email, password, userList)
                val intent = Intent(this, WelcomeActivity::class.java)
                if (switchRemember.isChecked) {
                    intent.putExtra(extras.extra_user, user)
                    intent.putExtra(extras.extra_checker, switchRemember.isChecked)
                    startActivity(intent)
                } else {
                    intent.putExtra(extras.extra_user, user)
                    intent.putExtra(extras.extra_checker, switchRemember.isChecked)
                    startActivity(intent)
                }

            } else {
                Toast.makeText(this, getString(R.string.acceso_denegado), Toast.LENGTH_SHORT).show()
            }
            finish()
        }

        val intent2 = intent
        if (intent2.hasExtra(extras.extra_email) && intent2.hasExtra(extras.extra_pass)) {
            val emailIntent = intent2.getStringExtra(extras.extra_email)
            val passIntent = intent2.getStringExtra(extras.extra_pass)
            emailText.setText(emailIntent)
            passText.setText(passIntent)
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

    private fun validate(email: String, password: String, userList: List<User>): Boolean {
        val userWithEmail = userList.firstOrNull { it.email == email && it.password == password}
        return if (userWithEmail != null ) {
            passwordRules(password)
        } else {
            false
        }
    }

    private fun passwordRules(password: String): Boolean {
        if (password.length < 6 || password.length > 8) {
            return false
        }

        val haveCaps = password.any { it.isUpperCase() }
        val haveLowers = password.any { it.isLowerCase() }
        val haveNumbers = password.any { it.isDigit() }

        return haveCaps && haveLowers && haveNumbers
    }


    private fun findUserByEmailAndPassword(email: String, password: String, userList: List<User>): User? {
        for (user in userList) {
            if (user.email == email && user.password == password) {
                return user
            }
        }
        return null
    }


}