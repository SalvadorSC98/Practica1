package com.example.practica1

import java.io.Serializable

class User(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var image: Int = 0
) : Serializable
