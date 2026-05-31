package com.example.angatkinmirea.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LaureateDto(
    val fullName: NameDto? = null,
    val motivation: MotivationDto? = null,
    val birth: BirthDto? = null,
    val wikipedia: String? = null,
    val portraitUrl: String? = null
)

@Serializable
data class NameDto(
    val en: String
)

@Serializable
data class MotivationDto(
    val en: String
)

@Serializable
data class BirthDto(
    val place: PlaceDto? = null
)

@Serializable
data class PlaceDto(
    val country: CountryDto? = null
)

@Serializable
data class CountryDto(
    val en: String
)