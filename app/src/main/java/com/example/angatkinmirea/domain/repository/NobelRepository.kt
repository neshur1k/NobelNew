package com.example.angatkinmirea.domain.repository

import com.example.angatkinmirea.domain.model.Laureate

interface NobelRepository {

    suspend fun getLaureates(
        year: String?,
        category: String?
    ): List<Laureate>
}