package com.extrime.myrandomusers.data.repository

import com.extrime.myrandomusers.data.api.UserApiService
import com.extrime.myrandomusers.data.mapper.UserMapper
import com.extrime.myrandomusers.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApiService: UserApiService,
    private val userMapper: UserMapper
) {

    suspend fun getRandomUsers(
        count: Int = 20,
        gender: String? = null,
        nationality: String? = null
    ): List<User> {
        val response = userApiService.getRandomUsers(count, gender, nationality)
        return userMapper.toUserList(response.results)
    }
}