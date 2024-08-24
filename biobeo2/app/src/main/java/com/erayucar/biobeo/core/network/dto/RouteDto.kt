package com.erayucar.biobeo.core.network.dto

import com.google.gson.annotations.SerializedName

data class RouteDto(
    val city: String,
    val description: String,
    val is_adult: Int,
    val name: String,
    val routeId: Int
)