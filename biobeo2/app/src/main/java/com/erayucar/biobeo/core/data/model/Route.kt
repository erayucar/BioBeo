package com.erayucar.biobeo.core.data.model

data class Route(
    val city: String? = null,
    val description: String,
    val is_adult: Int,
    val name: String,
    val routeId: Int
)