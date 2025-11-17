package com.extrime.myrandomusers.domain.model

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val nationality: String,
    val age: Int,
    val birthDate: String,
    val phone: String,
    val email: String,
    val photoUrl: String
)