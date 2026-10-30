package com.example.cleanarchitectureapp.domain.repository

import com.example.cleanarchitectureapp.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}