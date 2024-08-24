package com.erayucar.biobeo.core.data.model

data class RouteDetail(
    val is_adult: Int,
    val pois: List<Poi>,
    val routeId: Int,

    )