package com.example.cleanarchitectureapp.data.mapper

import com.example.cleanarchitectureapp.data.dto.UserDto
import com.example.cleanarchitectureapp.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        id = this.id,
        fullName = this.first_name,
        email = this.user_email
    )
}