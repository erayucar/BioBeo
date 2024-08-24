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
import com.erayucar.biobeo.core.network.dto.SignUpBody
import com.erayucar.biobeo.core.network.source.RestDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RouteRepositoryImpl @Inject constructor(private val restDataSource: RestDataSource) : RouteRepository{
    override suspend fun getAuth(body: SignUpBody): Flow<ResponseState<AuthResponse>> = flow{
        emit(ResponseState.Loading)
            val response = restDataSource.getAuth(body = body)
            if (response.isSuccessful){
                emit(ResponseState.Success(response.body()!!))
            }else{
                emit(ResponseState.Error(response.message()))
            }

        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
    }


    override suspend fun getPOI(): Flow<ResponseState<List<POI>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getPOI()
            if (response.isSuccessful){
                emit(ResponseState.Success(response.mapTo { it.toPOIList()}))
            }else{
                emit(ResponseState.Error(response.message()))
            }
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getAllRoutes():  Flow<ResponseState<List<Route>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllRoutes()
            emit(ResponseState.Success(response.mapTo { it.toRoutesList() }))

        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getSingleRoute(id: String): Flow<ResponseState<Route>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getSingleRoute(id)
            emit(ResponseState.Success(response.mapTo { it.toRoute()}))

        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getLeaderBoard(): Flow<ResponseState<List<LeaderBoardItem>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getLeaderBoard()
            if (response.isSuccessful){
                emit(ResponseState.Success(response.mapTo { it.toLeaderBoard() }))
            }else{
                emit(ResponseState.Error(response.message()))
            }
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getAllStatistics(): Flow<ResponseState<List<Statistic>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getAllStatistics()
            if (response.isSuccessful){
                emit(ResponseState.Success(response.mapTo { it.toStatisticList() }))
            }else{
                emit(ResponseState.Error(response.message()))
            }
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }
}