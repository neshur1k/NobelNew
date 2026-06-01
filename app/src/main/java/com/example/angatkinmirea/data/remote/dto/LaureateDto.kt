package com.example.angatkinmirea.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class LaureateDto(
    val id: String,
    val fullName: String,
    val motivation: String,
    val country: String
)