package com.extrime.myrandomusers.domain.repository

import com.extrime.myrandomusers.domain.model.User

interface UserRepository {
    suspend fun getRandomUsers(
        count: Int,
        gender: String?,
        nationality: String?
    ): List<User>

    suspend fun saveUsers(users: List<User>)
    suspend fun getCachedUsers(
        gender: String?,
        nationality: String?
    ): List<User>

    suspend fun clearCache()
}