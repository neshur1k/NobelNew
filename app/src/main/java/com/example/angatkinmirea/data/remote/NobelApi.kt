package com.example.angatkinmirea.data.remote

import com.example.angatkinmirea.data.remote.dto.LoginRequest
import com.example.angatkinmirea.data.remote.dto.LoginResponse
import com.example.angatkinmirea.data.remote.dto.PrizeDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NobelApi {

    private var token: String? = null

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    private suspend fun login() {
        val response: LoginResponse =
            client.post("http://10.0.2.2:8080/auth/login") {
                contentType(ContentType.Application.Json)

                setBody(
                    LoginRequest(
                        login = "admin",
                        password = "1234"
                    )
                )
            }.body()

        token = response.token
    }

    suspend fun getPrizes(): List<PrizeDto> {

        if (token == null) {
            login()
        }

        return client.get(
            "http://10.0.2.2:8080/prizes"
        ) {
            bearerAuth(token!!)
        }.body()
    }
}