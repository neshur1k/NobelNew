package com.example.angatkinmirea.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PrizeDto(
    val year: String,
    val category: String,
    val laureates: List<LaureateDto>
)