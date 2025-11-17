package com.extrime.myrandomusers.data.mapper

import com.extrime.myrandomusers.data.api.response.ApiUser
import com.extrime.myrandomusers.data.database.entity.UserEntity
import com.extrime.myrandomusers.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() {

    // API to Domain
    fun toUser(apiUser: ApiUser): User {
        return User(
            id = apiUser.login.uuid,
            firstName = apiUser.name.first,
            lastName = apiUser.name.last,
            gender = apiUser.gender,
            nationality = apiUser.nat,
            age = apiUser.dob.age,
            birthDate = apiUser.dob.date,
            phone = apiUser.phone,
            email = apiUser.email,
            photoUrl = apiUser.picture.large
        )
    }

    fun toUserList(apiUsers: List<ApiUser>): List<User> {
        return apiUsers.map { toUser(it) }
    }

    // Domain to Entity
    fun toEntity(user: User): UserEntity {
        return UserEntity(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            gender = user.gender,
            nationality = user.nationality,
            age = user.age,
            birthDate = user.birthDate,
            phone = user.phone,
            email = user.email,
            photoUrl = user.photoUrl
        )
    }

    fun toEntityList(users: List<User>): List<UserEntity> {
        return users.map { toEntity(it) }
    }

    // Entity to Domain
    fun fromEntity(entity: UserEntity): User {
        return User(
            id = entity.id,
            firstName = entity.firstName,
            lastName = entity.lastName,
            gender = entity.gender,
            nationality = entity.nationality,
            age = entity.age,
            birthDate = entity.birthDate,
            phone = entity.phone,
            email = entity.email,
            photoUrl = entity.photoUrl
        )
    }

    fun fromEntityList(entities: List<UserEntity>): List<User> {
        return entities.map { fromEntity(it) }
    }
}