package com.example.practica1

import java.io.Serializable

data class User(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val image: Int = 0
) : Serializable
