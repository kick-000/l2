package com.example.cleanarchitectureapp.domain.usecase

import com.example.cleanarchitectureapp.domain.model.User
import com.example.cleanarchitectureapp.domain.repository.UserRepository

class GetUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}