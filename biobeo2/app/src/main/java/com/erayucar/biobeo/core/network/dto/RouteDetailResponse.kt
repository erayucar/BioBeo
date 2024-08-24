package com.erayucar.biobeo.core.network.dto

data class RouteDetailResponse(
    val is_adult: Int,
    val pois: List<PoiDto>,
    val routeCity: String,
    val routeDescription: String,
    val routeId: Int,
    val routeName: String
)