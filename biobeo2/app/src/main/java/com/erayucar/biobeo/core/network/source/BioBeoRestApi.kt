package com.erayucar.biobeo.core.network.source

import com.erayucar.biobeo.core.network.dto.POIResponse
import com.erayucar.biobeo.core.network.dto.RouteResponse
import com.erayucar.biobeo.core.network.dto.RouteDto
import com.erayucar.biobeo.core.network.dto.StatisticResponse
import com.erayucar.biobeo.core.network.dto.AuthResponse
import com.erayucar.biobeo.core.network.dto.LeaderBoardResponse
import com.erayucar.biobeo.core.network.dto.SignUpBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BioBeoRestApi {

    @POST("auth")
    suspend fun getAuth(@Body body: SignUpBody): Response<AuthResponse>

    @GET("poi")
    suspend fun getPOI(): Response<POIResponse>

    @GET("route")
    suspend fun getAllRoutes(): Response<RouteResponse>

    @GET("leaderboard")
    suspend fun getLeaderBoard(): Response<LeaderBoardResponse>

    @GET("route/{id}")
    suspend fun getSingleRoute(@Path("id") routeId: String): Response<RouteDto>


    @GET("statistics/allstatistics")
    suspend fun getAllStatistics(): Response<StatisticResponse>
}