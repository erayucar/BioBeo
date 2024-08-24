package com.erayucar.biobeo.core.data

import com.erayucar.biobeo.core.data.model.LeaderBoardItem
import com.erayucar.biobeo.core.data.model.POI
import com.erayucar.biobeo.core.data.model.Route
import com.erayucar.biobeo.core.data.model.Statistic
import com.erayucar.biobeo.core.network.dto.LeaderBoardResponse
import com.erayucar.biobeo.core.network.dto.POIResponse
import com.erayucar.biobeo.core.network.dto.RouteDto
import com.erayucar.biobeo.core.network.dto.RouteResponse
import com.erayucar.biobeo.core.network.dto.StatisticResponse
import retrofit2.Response

typealias RestRoutesResponse = Response<RouteResponse>
typealias RestPOIResponse = Response<POIResponse>
typealias RestStatisticResponse = Response<StatisticResponse>
typealias RestSingleRouteResponse = Response<RouteDto>
typealias RestLeaderBoardResponse = Response<LeaderBoardResponse>


fun RestRoutesResponse.toRoutesList(): List<Route> {
    return body()!!.map {
        Route(
            city = it.city,
            description = it.description,
            is_adult = it.is_adult,
            name = it.name,
            routeId = it.routeId
        )
    }
}

fun RestLeaderBoardResponse.toLeaderBoard(): List<LeaderBoardItem>{
    return body()!!.map {
        LeaderBoardItem(
            score = it.score,
            username = it.username
        )
    }

}

fun RestSingleRouteResponse.toRoute(): Route {
    return body()!!.run {
        Route(
            city = city,
            description = description,
            is_adult = is_adult,
            name = name,
            routeId = routeId
        )
    }
}

fun RestPOIResponse.toPOIList(): List<POI> {
    return body()!!.map {
        POI(
            city = it.city,
            description = it.description,
            latitude = it.latitude,
            longitude = it.longitude,
            name = it.name,
            photo = it.photo,
            poiId = it.poiId,
            routeId = it.routeId
            )
    }
}

fun RestStatisticResponse.toStatisticList(): List<Statistic> {
    return body()!!.map {
        Statistic(
            it.completedRouteCount,
            it.userId
        )
    }
}