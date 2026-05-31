package com.example.angatkinmirea.presentation.list

import com.example.angatkinmirea.domain.model.Laureate

sealed interface LaureatesState {

    data object Loading : LaureatesState

    data class Success(
        val items: List<Laureate>
    ) : LaureatesState

    data class Error(
        val message: String
    ) : LaureatesState
}