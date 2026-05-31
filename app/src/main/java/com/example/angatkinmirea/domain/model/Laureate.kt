package com.example.angatkinmirea.domain.model

data class Laureate(
    val id: String,
    val fullName: String,
    val year: String,
    val category: String,
    val motivation: String,
    val country: String,
    val imageUrl: String?
)