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

        val response = api.getPrizes(
            year,
            category
        )

        return response.nobelPrizes.flatMap { prize ->

            prize.laureates.orEmpty().map { laureate ->

                Laureate(
                    id = laureate.fullName?.en ?: "",
                    fullName = laureate.fullName?.en ?: "",
                    year = prize.awardYear,
                    category = prize.category.en,
                    motivation = laureate.motivation?.en ?: "",
                    country = laureate.birth
                        ?.place
                        ?.country
                        ?.en ?: "Unknown",
                    imageUrl = laureate.portraitUrl
                )
            }
        }
    }
}