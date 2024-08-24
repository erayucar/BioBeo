package com.erayucar.biobeo.core.network.source

import com.erayucar.biobeo.core.network.dto.POIResponse
import com.erayucar.biobeo.core.network.dto.RouteResponse
import com.erayucar.biobeo.core.network.dto.RouteDto
import com.erayucar.biobeo.core.network.dto.StatisticResponse
import com.erayucar.biobeo.core.network.dto.AuthResponse
import com.erayucar.biobeo.core.network.dto.LeaderBoardResponse
import com.erayucar.biobeo.core.network.dto.SignUpBody
import retrofit2.Response

interface RestDataSource {

    suspend fun getAuth(body: SignUpBody): Response<AuthResponse>

    suspend fun getPOI(): Response<POIResponse>

    suspend fun getAllRoutes(): Response<RouteResponse>

    suspend fun getLeaderBoard(): Response<LeaderBoardResponse>

    suspend fun getSingleRoute(id: String): Response<RouteDto>

    suspend fun getAllStatistics(): Response<StatisticResponse>
}