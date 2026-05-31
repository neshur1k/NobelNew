package com.example.angatkinmirea.domain.usecase

import com.example.angatkinmirea.domain.model.Laureate
import com.example.angatkinmirea.domain.repository.NobelRepository

class GetLaureatesUseCase(
    private val repository: NobelRepository
) {

    suspend operator fun invoke(
        year: String?,
        category: String?
    ): List<Laureate> {

        return repository.getLaureates(
            year,
            category
        )
    }
}