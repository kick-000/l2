package com.example.cleanarchitectureapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cleanarchitectureapp.data.repository.UserRepositoryImpl
import com.example.cleanarchitectureapp.databinding.ActivityMainBinding
import com.example.cleanarchitectureapp.domain.usecase.GetUsersUseCase
import com.example.cleanarchitectureapp.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Собираем зависимости вручную (Dependency Injection)
    private val viewModel: MainViewModel by viewModels {
        val repository = UserRepositoryImpl()
        val useCase = GetUsersUseCase(repository)
        MainViewModel(useCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect { isLoading ->
                    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                }
            }
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collect { users ->
                    val text = users.joinToString("\n\n") {
                        "Имя: ${it.fullName}\nEmail: ${it.email}"
                    }
                    binding.tvUsers.text = text
                }
            }
        }

        viewModel.loadUsers()
    }
}