package com.erayucar.biobeo.core.network.source

import com.erayucar.biobeo.core.network.dto.POIResponse
import com.erayucar.biobeo.core.network.dto.RouteResponse
import com.erayucar.biobeo.core.network.dto.RouteDto
import com.erayucar.biobeo.core.network.dto.StatisticResponse
import com.erayucar.biobeo.core.network.dto.AuthResponse
import com.erayucar.biobeo.core.network.dto.LeaderBoardResponse
import com.erayucar.biobeo.core.network.dto.SignUpBody
import retrofit2.Response
import javax.inject.Inject

class RestDataSourceImpl @Inject constructor(
    private val api: BioBeoRestApi
) : RestDataSource{
    override suspend fun getAuth(body: SignUpBody): Response<AuthResponse> {
       return api.getAuth(body)
    }

    override suspend fun getPOI(): Response<POIResponse> {
       return api.getPOI()
    }

    override suspend fun getAllRoutes(): Response<RouteResponse> {
        return api.getAllRoutes()
    }

    override suspend fun getLeaderBoard(): Response<LeaderBoardResponse> {
        return api.getLeaderBoard()
    }

    override suspend fun getSingleRoute(id: String): Response<RouteDto> {
        return api.getSingleRoute(id)
    }

    override suspend fun getAllStatistics(): Response<StatisticResponse> {
        return api.getAllStatistics()
    }
}