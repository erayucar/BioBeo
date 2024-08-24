package com.erayucar.biobeo.core.data

import com.erayucar.biobeo.core.common.ResponseState
import com.erayucar.biobeo.core.data.model.LeaderBoardItem
import com.erayucar.biobeo.core.data.model.POI
import com.erayucar.biobeo.core.data.model.Route
import com.erayucar.biobeo.core.data.model.Statistic
import com.erayucar.biobeo.core.network.dto.POIResponse
import com.erayucar.biobeo.core.network.dto.RouteResponse
import com.erayucar.biobeo.core.network.dto.RouteDto
import com.erayucar.biobeo.core.network.dto.StatisticResponse
import com.erayucar.biobeo.core.network.dto.AuthResponse
import com.erayucar.biobeo.core.network.dto.LeaderBoardResponse
import com.erayucar.biobeo.core.network.dto.SignUpBody
import kotlinx.coroutines.flow.Flow

interface RouteRepository {

    suspend fun getAuth(body: SignUpBody): Flow<ResponseState<AuthResponse>>

    suspend fun getPOI(): Flow<ResponseState<List<POI>>>

    suspend fun getAllRoutes(): Flow<ResponseState<List<Route>>>

    suspend fun getLeaderBoard(): Flow<ResponseState<List<LeaderBoardItem>>>

    suspend fun getSingleRoute(id: String): Flow<ResponseState<Route>>

    suspend fun getAllStatistics(): Flow<ResponseState<List<Statistic>>>
}