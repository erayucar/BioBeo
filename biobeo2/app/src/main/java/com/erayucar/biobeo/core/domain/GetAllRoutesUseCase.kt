package com.erayucar.biobeo.core.domain

import com.erayucar.biobeo.core.common.ResponseState
import com.erayucar.biobeo.core.data.RouteRepository
import com.erayucar.biobeo.core.data.model.Route
import com.erayucar.biobeo.core.network.dto.RouteDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllRoutesUseCase @Inject constructor(private val routeRepository: RouteRepository){


    suspend operator fun invoke() : Flow<ResponseState<List<Route>>> {
        return routeRepository.getAllRoutes()
    }
}