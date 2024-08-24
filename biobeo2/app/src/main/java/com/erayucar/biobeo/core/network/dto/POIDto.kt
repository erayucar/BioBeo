package com.erayucar.biobeo.core.network.dto

data class POIDto(
    val city: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val photo: String,
    val poiId: Int,
    val routeId: Int
)