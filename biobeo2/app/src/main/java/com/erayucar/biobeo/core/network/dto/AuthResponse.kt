package com.erayucar.biobeo.core.network.dto

data class AuthResponse(
    val message: String,
    val token: String,
    val userId: Int
)