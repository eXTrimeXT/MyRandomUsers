package com.extrime.myrandomusers.data.repository

import com.extrime.myrandomusers.data.api.UserApiService
import com.extrime.myrandomusers.data.database.dao.UserDao
import com.extrime.myrandomusers.data.mapper.UserMapper
import com.extrime.myrandomusers.domain.model.User
import com.extrime.myrandomusers.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApiService: UserApiService,
    private val userDao: UserDao,
    private val userMapper: UserMapper
) : UserRepository {

    override suspend fun getRandomUsers(
        count: Int,
        gender: String?,
        nationality: String?
    ): List<User> {
        val response = userApiService.getRandomUsers(count, gender, nationality)
        return userMapper.toUserList(response.results)
    }

    override suspend fun saveUsers(users: List<User>) {
        val entities = userMapper.toEntityList(users)
        userDao.insertUsers(entities)
    }

    override suspend fun getCachedUsers(
        gender: String?,
        nationality: String?
    ): List<User> {
        val entities = when {
            gender != null && nationality != null ->
                userDao.getUsersByGenderAndNationality(gender, nationality)
            gender != null ->
                userDao.getUsersByGender(gender)
            nationality != null ->
                userDao.getUsersByNationality(nationality)
            else ->
                userDao.getAllUsers()
        }
        return userMapper.fromEntityList(entities)
    }

    override suspend fun clearCache() {
        userDao.clearAllUsers()
    }
}