package com.extrime.myrandomusers.data.di

import com.extrime.myrandomusers.data.api.UserApiService
import com.extrime.myrandomusers.data.database.dao.UserDao
import com.extrime.myrandomusers.data.mapper.UserMapper
import com.extrime.myrandomusers.data.repository.UserRepositoryImpl
import com.extrime.myrandomusers.domain.repository.UserRepository
import com.extrime.myrandomusers.domain.usecase.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideUserMapper(): UserMapper = UserMapper()

    @Provides
    @Singleton
    fun provideUserRepository(
        userApiService: UserApiService,
        userDao: UserDao,
        userMapper: UserMapper
    ): UserRepository {
        return UserRepositoryImpl(userApiService, userDao, userMapper)
    }

    @Provides
    @Singleton
    fun provideGetUsersUseCase(
        userRepository: UserRepository
    ): GetUsersUseCase {
        return GetUsersUseCase(userRepository)
    }
}