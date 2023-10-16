package com.example.practica1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val nameText = findViewById<TextView>(R.id.textView4)
        val emailText = findViewById<TextView>(R.id.textView6)
        val button2 = findViewById<Button>(R.id.button2)
        val imageView = findViewById<ImageView>(R.id.imageView2)
        val intent = intent

        if (intent.hasExtra("user")) {
            val user = intent.getSerializableExtra("user") as User

            val name = user.name
            val email = user.email
            val password = user.password
            val imageResource = user.image

            nameText.setText(name)
            emailText.setText(email)
            imageView.setImageResource(imageResource)

            val checker = intent.getBooleanExtra("checker", true)


            button2.setOnClickListener {
                val intent2 = Intent(this, MainActivity::class.java)
                if (checker) {
                    intent2.putExtra("email", email)
                    intent2.putExtra("pass", password)
                    startActivity(intent2)
                } else {
                    startActivity(intent2)
                }

            }
        }
    }
}