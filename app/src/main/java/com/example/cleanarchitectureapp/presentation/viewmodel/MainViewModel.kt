package com.example.cleanarchitectureapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureapp.domain.model.User
import com.example.cleanarchitectureapp.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = getUsersUseCase()
                _users.value = result
            } catch (e: Exception) {
            } finally {
                _isLoading.value = false
            }
        }
    }
}