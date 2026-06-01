package com.example.angatkinmirea.data.repository

import com.example.angatkinmirea.data.remote.NobelApi
import com.example.angatkinmirea.domain.model.Laureate
import com.example.angatkinmirea.domain.repository.NobelRepository

class NobelRepositoryImpl(
    private val api: NobelApi
) : NobelRepository {

    override suspend fun getLaureates(
        year: String?,
        category: String?
    ): List<Laureate> {

        val prizes = api.getPrizes()

        return prizes
            .filter {
                (year == null || it.year == year) &&
                        (category == null || it.category == category)
            }
            .flatMap { prize ->
                prize.laureates.map { laureate ->
                    Laureate(
                        id = laureate.id,
                        fullName = laureate.fullName,
                        year = prize.year,
                        category = prize.category,
                        motivation = laureate.motivation,
                        country = laureate.country,
                        imageUrl = null
                    )
                }
            }
    }
}