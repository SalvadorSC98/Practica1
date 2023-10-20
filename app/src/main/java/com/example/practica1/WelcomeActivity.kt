package com.example.practica1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val nameText = findViewById<TextView>(R.id.userTextView)
        val emailText = findViewById<TextView>(R.id.emailTextView)
        val button2 = findViewById<Button>(R.id.logoutButton)
        val imageView = findViewById<ImageView>(R.id.userImage)
        val intent = intent

        val extras = Extras

        if (intent.hasExtra(extras.extra_user)) {
            val user = intent.getSerializableExtra(extras.extra_user) as User

            val name = user.name
            val email = user.email
            val password = user.password
            val imageResource = user.image

            nameText.text = name
            emailText.text = email
            imageView.setImageResource(imageResource)

            val checker = intent.getBooleanExtra(extras.extra_checker, true)


            button2.setOnClickListener {
                val intent2 = Intent(this, MainActivity::class.java)
                if (checker) {
                    intent2.putExtra(extras.extra_email, email)
                    intent2.putExtra(extras.extra_pass, password)
                    startActivity(intent2)
                } else {
                    startActivity(intent2)
                }
                finish()
            }
        }
    }
}