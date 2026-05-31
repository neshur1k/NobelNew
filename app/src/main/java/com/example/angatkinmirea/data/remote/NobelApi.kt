package com.example.angatkinmirea.data.remote

import com.example.angatkinmirea.data.remote.dto.NobelResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NobelApi {

    private val client = HttpClient(Android) {

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun getPrizes(
        year: String?,
        category: String?
    ): NobelResponseDto {

        return client.get(
            "https://api.nobelprize.org/2.1/nobelPrizes"
        ) {
            parameter("limit", 25)
            parameter("offset", 0)

            year?.let {
                parameter("nobelPrizeYear", it)
            }

            category?.let {
                parameter("nobelPrizeCategory", it)
            }
        }.body()
    }
}