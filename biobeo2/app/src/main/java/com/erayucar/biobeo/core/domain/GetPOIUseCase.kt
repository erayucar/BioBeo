package com.erayucar.biobeo.core.domain

import com.erayucar.biobeo.core.data.RouteRepository
import javax.inject.Inject

class GetPOIUseCase @Inject constructor(
    private val routeRepository: RouteRepository
){

    suspend operator fun invoke() = routeRepository.getPOI()
}