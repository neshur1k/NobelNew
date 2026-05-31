package com.example.angatkinmirea.presentation.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.angatkinmirea.domain.model.Laureate
import com.example.angatkinmirea.domain.usecase.GetLaureatesUseCase
import kotlinx.coroutines.launch

class LaureatesViewModel(
    private val getLaureatesUseCase: GetLaureatesUseCase
) : ViewModel() {

    var state by mutableStateOf<LaureatesState>(
        LaureatesState.Loading
    )
        private set

    var selectedLaureate by mutableStateOf<Laureate?>(null)

    init {
        Log.d("NOBEL", "ViewModel init")
        loadData(null, null)
    }

    fun loadData(
        year: String?,
        category: String?
    ) {

        viewModelScope.launch {

            state = LaureatesState.Loading

            try {
                Log.d("NOBEL", "loadData started")
                val data = getLaureatesUseCase(
                    year,
                    category
                )
                Log.d("NOBEL", "success: ${data.size}")
                state = LaureatesState.Success(data)

            } catch (e: Exception) {
                Log.e("NOBEL", "error", e)
                state = LaureatesState.Error(
                    e.message ?: "Unknown error"
                )
            }
        }
    }
}