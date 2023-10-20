package com.example.practica1

import com.example.practica1.R
import com.example.practica1.User

class UsersList {
    companion object {
        val users = listOf(
            User("Usuario1", "usuario1@example.com", "Contra1", R.drawable.imagen1),
            User("Usuario2", "usuario2@example.com", "Contra2", R.drawable.imagen2)
        )
    }
}
