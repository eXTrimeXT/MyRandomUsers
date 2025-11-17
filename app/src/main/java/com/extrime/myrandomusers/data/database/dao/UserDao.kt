package com.extrime.myrandomusers.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.extrime.myrandomusers.data.database.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY createdAt DESC")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE gender = :gender ORDER BY createdAt DESC")
    suspend fun getUsersByGender(gender: String): List<UserEntity>

    @Query("SELECT * FROM users WHERE nationality = :nationality ORDER BY createdAt DESC")
    suspend fun getUsersByNationality(nationality: String): List<UserEntity>

    @Query("SELECT * FROM users WHERE gender = :gender AND nationality = :nationality ORDER BY createdAt DESC")
    suspend fun getUsersByGenderAndNationality(gender: String, nationality: String): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("DELETE FROM users")
    suspend fun clearAllUsers()
}