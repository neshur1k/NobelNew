package com.example.angatkinmirea.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.angatkinmirea.domain.usecase.GetLaureatesUseCase

class LaureatesViewModelFactory(
    private val useCase: GetLaureatesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(LaureatesViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return LaureatesViewModel(useCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}