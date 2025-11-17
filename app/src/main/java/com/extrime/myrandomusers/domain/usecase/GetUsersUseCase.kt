package com.extrime.myrandomusers.domain.usecase

import com.extrime.myrandomusers.domain.model.User
import com.extrime.myrandomusers.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute(
        count: Int = 20,
        gender: String? = null,
        nationality: String? = null
    ): Result<List<User>> {
        return try {
            val users = userRepository.getRandomUsers(count, gender, nationality)
            userRepository.saveUsers(users)
            Result.success(users)
        } catch (e: Exception) {
            // При ошибке сети пытаемся получить кешированные данные
            try {
                val cachedUsers = userRepository.getCachedUsers(gender, nationality)
                if (cachedUsers.isNotEmpty()) {
                    Result.success(cachedUsers)
                } else {
                    Result.failure(e)
                }
            } catch (cacheException: Exception) {
                Result.failure(cacheException)
            }
        }
    }
}