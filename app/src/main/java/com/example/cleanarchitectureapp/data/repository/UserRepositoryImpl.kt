package com.example.cleanarchitectureapp.data.repository

import com.example.cleanarchitectureapp.data.dto.UserDto
import com.example.cleanarchitectureapp.data.mapper.toDomain
import com.example.cleanarchitectureapp.domain.model.User
import com.example.cleanarchitectureapp.domain.repository.UserRepository
import kotlinx.coroutines.delay

class UserRepositoryImpl : UserRepository {

    private val fakeNetworkData = listOf(
        UserDto(1, "Иван Иванов", "ivan@mail.ru"),
        UserDto(2, "Петр Петров", "petr@mail.ru"),
        UserDto(3, "Анна Смирнова", "anna@mail.ru")
    )

    override suspend fun getUsers(): List<User> {
        delay(1000)
        return fakeNetworkData.map { it.toDomain() }
    }
}