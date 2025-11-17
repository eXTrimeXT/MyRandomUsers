package com.extrime.myrandomusers.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val nationality: String,
    val age: Int,
    val birthDate: String,
    val phone: String,
    val email: String,
    val photoUrl: String,
    val createdAt: Long = System.currentTimeMillis()
)